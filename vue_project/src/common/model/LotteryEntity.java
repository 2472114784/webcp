package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lottery_type")
public class LotteryEntity {
    private long lotteryId;
    private String lotteryName;
    private String lotteryIcon;
    private long lotteryNo;
    private String lotteryResult;
    private long nextTime;//下一期开奖的时间戳
    private long openIntervalResultTime;//获取开奖结果间隔时间
    private long intervalTime;//每一期的间隔时间
    private long dayStartTime;//一天开始的时间
    private long dayEndTime;//一天结束的时间
    private long stopShoppingIntervalTime;//每一期离开奖的停止下单时间
    private String desc;
    private int lotteryResultType;//开奖结果显示样式
    private String lotteryIntervalDesc;//彩票间隔周期描述
    @Transient
    private List<LotteryChildEntity> lotteryChilds;
    @Transient
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<ChatRoomEntity> lotteryRooms;

    @Id
    @Column(name = "lottery_id", nullable = false)
    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }
    @Basic
    @Column(name = "lottery_name", nullable = false)
    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }
    @Basic
    @Column(name = "lottery_icon")
    public String getLotteryIcon() {
        return lotteryIcon;
    }

    public void setLotteryIcon(String lotteryIcon) {
        this.lotteryIcon = lotteryIcon;
    }
    @Transient
    public long getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(long lotteryNo) {
        this.lotteryNo = lotteryNo;
    }
    @Transient
    public String getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(String lotteryResult) {
        this.lotteryResult = lotteryResult;
    }
    @Transient
    public long getNextTime() {
        return nextTime;
    }

    public void setNextTime(long nextTime) {
        this.nextTime = nextTime;
    }
    @Basic
    @Column(name = "open_interval_result_time")
    public long getOpenIntervalResultTime() {
        return openIntervalResultTime;
    }

    public void setOpenIntervalResultTime(long openIntervalResultTime) {
        this.openIntervalResultTime = openIntervalResultTime;
    }
    @Basic
    @Column(name = "day_start_time")
    public long getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(long dayStartTime) {
        this.dayStartTime = dayStartTime;
    }
    @Basic
    @Column(name = "day_end_time")
    public long getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(long dayEndTime) {
        this.dayEndTime = dayEndTime;
    }
    @Basic
    @Column(name = "lottery_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Basic
    @Column(name = "lottery_result_type",nullable = false)
    public int getLotteryResultType() {
        return lotteryResultType;
    }

    public void setLotteryResultType(int lotteryResultType) {
        this.lotteryResultType = lotteryResultType;
    }

    @Basic
    @Column(name = "lottery_interval_desc")
    public String getLotteryIntervalDesc() {
        return lotteryIntervalDesc;
    }

    public void setLotteryIntervalDesc(String lotteryIntervalDesc) {
        this.lotteryIntervalDesc = lotteryIntervalDesc;
    }

    @Transient
    public List<LotteryChildEntity> getLotteryChilds() {
        return lotteryChilds;
    }

    public void setLotteryChilds(List<LotteryChildEntity> lotteryChilds) {
        this.lotteryChilds = lotteryChilds;
    }
    @Basic
    @Column(name = "interval_time")
    public long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    @Basic
    @Column(name = "stop_shopping_interval_time")
    public long getStopShoppingIntervalTime() {
        return stopShoppingIntervalTime;
    }

    public void setStopShoppingIntervalTime(long stopShoppingIntervalTime) {
        this.stopShoppingIntervalTime = stopShoppingIntervalTime;
    }

    @Transient
    public List<ChatRoomEntity> getLotteryRooms() {
        return lotteryRooms;
    }

    public void setLotteryRooms(List<ChatRoomEntity> lotteryRooms) {
        this.lotteryRooms = lotteryRooms;
    }


    @Transient
    public LotteryChildEntity getLotteryChildById(long lotteryChildId) {
        if(lotteryChilds!=null){
            for(int i=0;i<lotteryChilds.size();i++){
                LotteryChildEntity lotteryChildEntity = lotteryChilds.get(i);
                if(lotteryChildEntity!=null){
                    if(lotteryChildEntity.getLotteryChildId()==lotteryChildId){
                        return lotteryChildEntity;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "LotteryEntity{" +
                "lotteryId=" + lotteryId +
                ", lotteryName='" + lotteryName + '\'' +
                ", lotteryIcon='" + lotteryIcon + '\'' +
                ", lotteryNo=" + lotteryNo +
                ", lotteryResult='" + lotteryResult + '\'' +
                ", nextTime=" + nextTime +
                ", openIntervalResultTime=" + openIntervalResultTime +
                ", intervalTime=" + intervalTime +
                ", dayStartTime=" + dayStartTime +
                ", dayEndTime=" + dayEndTime +
                ", stopShoppingIntervalTime=" + stopShoppingIntervalTime +
                ", desc='" + desc + '\'' +
                ", lotteryResultType=" + lotteryResultType +
                ", lotteryChilds=" + lotteryChilds +
                ", lotteryRooms=" + lotteryRooms +
                '}';
    }
}
