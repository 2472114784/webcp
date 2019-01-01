package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.cp.manager.redPacket.RedPacketConfig;
import com.game.cp.utils.SeedUtil;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "red_packet_part")
public class RedPacketPartEntity {

    private long id;
    private double money;
    private UserEntity receiver;
    private long redPacketId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp updateTime;

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
    @OneToOne()
    @JoinColumn(name="receiver_id")
    @NotFound(action=NotFoundAction.IGNORE)
    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "red_packet_id", nullable = false)
    public long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(long redPacketId) {
        this.redPacketId = redPacketId;
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
    @Transient
    public RedPacketRecodeEntity getRedPacketRecodeEntity() {
        RedPacketRecodeEntity redPacketRecodeEntity = new RedPacketRecodeEntity();
        redPacketRecodeEntity.setId(SeedUtil.getId());
        redPacketRecodeEntity.setMoney(money);
        redPacketRecodeEntity.setRedPacketId(redPacketId);
        redPacketRecodeEntity.setRemark("");
        redPacketRecodeEntity.setType(RedPacketConfig.ACTION_TYPE_RECEIVER);
        if(receiver!=null) {
            redPacketRecodeEntity.setUserId(receiver.getId());
            redPacketRecodeEntity.setUserName(receiver.getUsername());
            redPacketRecodeEntity.setPortrait(receiver.getPortrait());
        }
        return redPacketRecodeEntity;
    }
}
