package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "report_user_day")
public class ReportUserDayEntity {

    private long id;
    private double dayMoney;
    private double dayOrderMoney;
    private double dayOrderNum;
    private long userId;
    private double dayRechargeMoney;
    private int dayRechargeNum;
    private double dayWithdrawMoney;
    private int dayWithdrawNum;
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Timestamp time;

    private boolean isToDay;//是否是今天
    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "day_money",nullable = false)
    public double getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(double dayMoney) {
        this.dayMoney = dayMoney;
    }
    @Basic
    @Column(name = "day_order_money",nullable = false)
    public double getDayOrderMoney() {
        return dayOrderMoney;
    }

    public void setDayOrderMoney(double dayOrderMoney) {
        this.dayOrderMoney = dayOrderMoney;
    }
    @Basic
    @Column(name = "day_order_num",nullable = false)
    public double getDayOrderNum() {
        return dayOrderNum;
    }

    public void setDayOrderNum(double dayOrderNum) {
        this.dayOrderNum = dayOrderNum;
    }
    @Basic
    @Column(name = "user_id",nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "time",nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    @Basic
    @Column(name = "day_recharge_money",nullable = false)
    public double getDayRechargeMoney() {
        return dayRechargeMoney;
    }

    public void setDayRechargeMoney(double dayRechargeMoney) {
        this.dayRechargeMoney = dayRechargeMoney;
    }
    @Basic
    @Column(name = "day_recharge_num",nullable = false)
    public int getDayRechargeNum() {
        return dayRechargeNum;
    }

    public void setDayRechargeNum(int dayRechargeNum) {
        this.dayRechargeNum = dayRechargeNum;
    }
    @Basic
    @Column(name = "day_withdraw_money",nullable = false)
    public double getDayWithdrawMoney() {
        return dayWithdrawMoney;
    }

    public void setDayWithdrawMoney(double dayWithdrawMoney) {
        this.dayWithdrawMoney = dayWithdrawMoney;
    }
    @Basic
    @Column(name = "day_withdraw_num",nullable = false)
    public int getDayWithdrawNum() {
        return dayWithdrawNum;
    }

    public void setDayWithdrawNum(int dayWithdrawNum) {
        this.dayWithdrawNum = dayWithdrawNum;
    }
    @Transient
    public boolean isToDay() {
        return isToDay;
    }

    public void setToDay(boolean toDay) {
        isToDay = toDay;
    }

    @Override
    public String toString() {
        return "ReportUserDayEntity{" +
                "id=" + id +
                ", dayMoney=" + dayMoney +
                ", dayOrderMoney=" + dayOrderMoney +
                ", dayOrderNum=" + dayOrderNum +
                ", userId=" + userId +
                ", dayRechargeMoney=" + dayRechargeMoney +
                ", dayRechargeNum=" + dayRechargeNum +
                ", dayWithdrawMoney=" + dayWithdrawMoney +
                ", dayWithdrawNum=" + dayWithdrawNum +
                ", time=" + time +
                ", isToDay=" + isToDay +
                '}';
    }
}
