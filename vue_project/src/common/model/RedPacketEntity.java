package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.cp.config.ChatConfig;
import com.game.cp.manager.redPacket.RedPacketConfig;
import com.game.cp.utils.SeedUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "red_packet")
public class RedPacketEntity {

    private long id;
    private double money;
    private int num;
    private int type;
    private String remark;
    private double receiveMoney;
    private String portrait;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp updateTime;
    @Transient
    private List<RedPacketPartEntity> redPacketParts;
    private UserEntity user;
    private int receiveNum;
    private long timeOver=ChatConfig.RED_PACKET_TIMEOVER;//红包超时时间戳

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "money", nullable = false)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    @Basic
    @Column(name = "num", nullable = false)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Basic
    @Column(name = "receive_num")
    public int getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        if(user!=null) {
            setPortrait(user.getPortrait());
            setUserName(user.getUsername());
        }
    }
    @OneToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }
    @Basic
    @Column(name = "receive_money")
    public double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }
    @Basic
    @Column(name = "portrait")
    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
    @Basic
    @Column(name = "username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Basic
    @Column(name = "time_over")
    public long getTimeOver() {
        return timeOver;
    }

    public void setTimeOver(long timeOver) {
        this.timeOver = timeOver;
    }

    @Transient
    public boolean isFinish() {
        return receiveNum>=num;
    }

    public void setRedPacketParts(List<RedPacketPartEntity> redPacketParts) {
        this.redPacketParts = redPacketParts;
    }

    /**
     * 避免操作数据库频繁 不建议关联查询操作
     * @return
     */
    @Transient
    public List<RedPacketPartEntity> getRedPacketParts() {
        return redPacketParts;
    }



    /**
     * 获取发送红包记录
     * @return
     */
    @Transient
    public RedPacketRecodeEntity getRedPacketRecodeEntity() {
        RedPacketRecodeEntity redPacketRecodeEntity = new RedPacketRecodeEntity();
        redPacketRecodeEntity.setId(SeedUtil.getId());
        redPacketRecodeEntity.setMoney(money);
        redPacketRecodeEntity.setRedPacketId(id);
        redPacketRecodeEntity.setRemark(remark);
        redPacketRecodeEntity.setType(RedPacketConfig.ACTION_TYPE_SEND);
        if(user!=null) {
            redPacketRecodeEntity.setUserId(user.getId());
            redPacketRecodeEntity.setUserName(user.getUsername());
            redPacketRecodeEntity.setPortrait(user.getPortrait());
        }
        return redPacketRecodeEntity;
    }

    @Transient
    public void addReceiveNum(int i) {
        receiveNum+=i;
    }
    @Transient
    public void addReceiveMoney(double money){
        receiveMoney+=money;
    }

    /**
     * 该红包是否超时
     * @return
     */
    @Transient
    public boolean isTimeOver() {
        long now = System.currentTimeMillis();
        Timestamp createTime = getCreateTime();
        if(createTime!=null){
            long creatTimeLong = createTime.getTime();
            long timeOver = getTimeOver();
            return now>=(creatTimeLong+timeOver);
        }
        return true;
    }
}
