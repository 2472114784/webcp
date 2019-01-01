package com.game.cp.model.entity;

/**
 * 支付渠道
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.cp.config.MoneyConfig;
import com.game.cp.utils.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "money_channel")
public class MoneyChannelEntity {

    /**
     * 渠道id
     */
    private long id;
    /**
     * 渠道icon
     */
    private String icon;

    /**
     * 渠道支付类型  银行卡  支付宝等
     */
    private int channelPayType;
    /**
     * 渠道类型
     */
    private int channelType;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道内容
     */
    private String channelContent;
    /**
     * 渠道用户名称
     */
    private String channelUserName;
    /**
     * 渠道的remark
     */
    private String channelRemark;
    /**
     * 备注
     */
    private String remark;
    /**
     * 最小mongy
     */
    private double minMoney;
    /**
     * 最大money
     */
    private double maxMoney;
    /**
     * 支付渠道拥有者id
     */
    private long userId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
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
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "channel_pay_type", nullable = false)
    public int getChannelPayType() {
        return channelPayType;
    }

    public void setChannelPayType(int channelPayType) {
        this.channelPayType = channelPayType;
    }

    @Basic
    @Column(name = "channel_type", nullable = false)
    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    @Basic
    @Column(name = "channel_name")
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Basic
    @Column(name = "channel_content")
    public String getChannelContent() {
        return channelContent;
    }

    public void setChannelContent(String channelContent) {
        this.channelContent = channelContent;
    }

    @Basic
    @Column(name = "channel_username")
    public String getChannelUserName() {
        return channelUserName;
    }

    public void setChannelUserName(String channelUserName) {
        this.channelUserName = channelUserName;
    }

    @Basic
    @Column(name = "channel_remark")
    public String getChannelRemark() {
        return channelRemark;
    }

    public void setChannelRemark(String channelRemark) {
        this.channelRemark = channelRemark;
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
    @Column(name = "min_money")
    public double getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(double minMoney) {
        this.minMoney = minMoney;
    }

    @Basic
    @Column(name = "max_money")
    public double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
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
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MoneyChannelEntity{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", channelPayType=" + channelPayType +
                ", channelType=" + channelType +
                ", channelName='" + channelName + '\'' +
                ", channelContent='" + channelContent + '\'' +
                ", channelUserName='" + channelUserName + '\'' +
                ", channelRemark='" + channelRemark + '\'' +
                ", remark='" + remark + '\'' +
                ", minMoney=" + minMoney +
                ", maxMoney=" + maxMoney +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 检测数据 是否符合要求
     *
     * @return
     */
    @Transient
    public boolean checkData() {
        switch (getChannelType()) {
            case MoneyConfig.CHANNEL_TYPE_RECHARGE:
                if(StringUtils.isEmpty(channelName,channelContent,channelUserName)){
                    return false;
                }
                if(getChannelPayType()==0||getId()==0){
                    return false;
                }
                break;
            case MoneyConfig.CHANNEL_TYPE_WITHDRAW:
                if(StringUtils.isEmpty(channelName,channelContent,channelUserName)){
                    return false;
                }
                if(getChannelPayType()==0||getId()==0){
                    return false;
                }
                break;
            default://不能出现此情况
                return false;
        }
        return true;
    }
}
