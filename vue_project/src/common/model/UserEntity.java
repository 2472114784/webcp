package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.cp.manager.exp.EXPManager;
import com.game.cp.utils.AppUtils;
import com.game.cp.utils.SeedUtil;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserEntity {
    private long id;
    private String account;
    @JsonIgnore//：使用在某个属性上，这样在序列化和反序列化的时候都会忽略这个属性，最直接的效果就是返回的JSON属性是没有这个属性的，一般作用于密码这系列的属性。
    private String password;
    private String username;
    private String portrait;
    private String token;
    private String rongToken;
    private String qiniuToken;
    private String room_id;
    private int roomRule;
    private int userType;
    private double money;
    private int lv;
    private int exp;
    private long inviteUserId;
    private String inviteCode;
    @JsonIgnore//：使用在某个属性上，这样在序列化和反序列化的时候都会忽略这个属性，最直接的效果就是返回的JSON属性是没有这个属性的，一般作用于密码这系列的属性。
    private String withdrawPassword;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp updateTime;
    private boolean isBan;
    private boolean isSetWithdrawPassword;
    @JsonIgnore
    private String gagRoomId;
    /**
     * 每日统计
     */
    private double dayMoney;
    private double dayOrderMoney;
    private int dayOrderNum;
    private double dayRechargeMoney;
    private int dayRechargeNum;
    private double dayWithdrawMoney;
    private int dayWithdrawNum;


    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account", nullable = false, length = 100)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "portrait", nullable = true, length = 100)
    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 255)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "rong_token", nullable = true, length = 100)
    public String getRongToken() {
        return rongToken;
    }

    public void setRongToken(String rongToken) {
        this.rongToken = rongToken;
    }

    @Basic
    @Column(name = "room_id", nullable = true, length = 100)
    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    @Basic
    @Column(name = "user_type", nullable = false, length = 11)
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "money", nullable = true, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "day_money", nullable = true, precision = 0)
    public double getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(double dayMoney) {
        this.dayMoney = dayMoney;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    @Basic
    @Column(name = "withdraw_password", nullable = true)
    public String getWithdrawPassword() {
        return withdrawPassword;
    }

    public void setWithdrawPassword(String withdrawPassword) {
        this.withdrawPassword = withdrawPassword;
    }
    @Basic
    @Column(name = "is_set_withdraw_password")
    public boolean isSetWithdrawPassword() {
        return isSetWithdrawPassword;
    }

    public void setSetWithdrawPassword(boolean setWithdrawPassword) {
        isSetWithdrawPassword = setWithdrawPassword;
    }
    @Basic
    @Column(name = "room_rule")
    public int getRoomRule() {
        return roomRule;
    }

    public void setRoomRule(int roomRule) {
        this.roomRule = roomRule;
    }

    @Basic
    @Column(name = "is_ban")
    public boolean isBan() {
        return isBan;
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }
    @Basic
    @Column(name = "lv",nullable = false)
    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
    @Basic
    @Column(name = "exp",nullable = false)
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setGagRoomId(String gagRoomId) {
        this.gagRoomId = gagRoomId;
    }
    @Basic
    @Column(name = "gag_room_id")
    public String getGagRoomId() {
        return gagRoomId;
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
    public int getDayOrderNum() {
        return dayOrderNum;
    }

    public void setDayOrderNum(int dayOrderNum) {
        this.dayOrderNum = dayOrderNum;
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
    @Basic
    @Column(name = "qiniu_token")
    public String getQiniuToken() {
        return qiniuToken;
    }

    public void setQiniuToken(String qiniuToken) {
        this.qiniuToken = qiniuToken;
    }
    @Basic
    @Column(name = "invite_user_id")
    public long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }
    @Basic
    @Column(name = "invite_code",nullable =false)
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * 自动检测是否升级
     */
    @Transient
    public boolean checkAutoUpdateLv(){
        if(EXPManager.isCanUpdateLv(lv,exp)){
            //升级
            setLv(lv+1);
            return true;
        }
        return false;
    }

    /**
     * 添加经验(lotteryorder相关)
     * @param totalMoney
     */
    @Transient
    public boolean addLotteryOrderExp(double totalMoney){
        int computeExp = EXPManager.getComputeExp(lv, totalMoney);
        exp+=computeExp;
        return checkAutoUpdateLv();
    }

    /**
     * 减少经验(lotteryorder相关)
     * @param totalMoney
     */
    public void removeLotteryOrderExp(double totalMoney){
        int computeExp = EXPManager.getComputeExp(lv, totalMoney);
        exp-=computeExp;
        if(exp<0){
            exp=0;
        }
    }

    /**
     * 添加money
     * @param addMoney
     */
    public void addMoney(Double addMoney) {
        money+=addMoney;
    }

    /**
     * 减少money
     * @param removeMoney
     */
    public void removeMoney(double removeMoney){
        money-=removeMoney;
    }

    public void addDayMoney(double money) {
        this.dayMoney+=money;
    }

    public void removeDayMoney(double money){
        this.dayMoney-=money;
    }

    /**
     * 获取日报表
     */
    @Transient
    @JsonIgnore
    public ReportUserDayEntity getReportUserDay() {
        if (!AppUtils.isSameDay(getUpdateTime())) {
            //不是同一天  可以生成报表
            ReportUserDayEntity reportUserDayEntity = new ReportUserDayEntity();
            reportUserDayEntity.setDayMoney(getDayMoney());
            reportUserDayEntity.setDayOrderMoney(getDayOrderMoney());
            reportUserDayEntity.setDayOrderNum(getDayOrderNum());
            reportUserDayEntity.setUserId(getId());
            reportUserDayEntity.setTime(getUpdateTime());
            reportUserDayEntity.setId(SeedUtil.getId());
            reportUserDayEntity.setDayRechargeMoney(getDayRechargeMoney());
            reportUserDayEntity.setDayRechargeNum(getDayRechargeNum());
            reportUserDayEntity.setDayWithdrawMoney(getDayWithdrawMoney());
            reportUserDayEntity.setDayWithdrawNum(getDayWithdrawNum());
            return reportUserDayEntity;
        }

        return null;
    }

    /**
     * 生成日报表成功后  还原日报表数据
     */
    public void resetReportUserDay() {
        setDayMoney(0);
        setDayOrderMoney(0);
        setDayOrderNum(0);
        setDayWithdrawMoney(0);
        setDayWithdrawNum(0);
        setDayRechargeMoney(0);
        setDayRechargeNum(0);
    }

    /**
     * 增加订单数量
     * @param num
     */
    public void addDayOrderNum(int num) {
        setDayOrderNum(getDayOrderNum()+num);
    }

    /**
     * 增加订单money
     * @param orderMoney
     */
    public void addDayOrderMoney(double orderMoney) {
        setDayOrderMoney(getDayOrderMoney()+orderMoney);
    }

    /**
     * 减少订单数
     * @param num
     */
    public void removeDayOrderNum(int num) {
        setDayOrderNum(getDayOrderNum()-num);
    }

    /**
     * 减少订单money
     * @param orderMoney
     */
    public void removeDayOrderMoney(double orderMoney) {
        setDayOrderMoney(getDayOrderMoney()-orderMoney);

    }

    /**
     * 添加提现成功money
     * @param money
     */
    public void addDayWithdrawMoney(double money) {
        setDayWithdrawMoney(getDayWithdrawMoney()+money);
    }

    /**
     * 添加提现成功记录次数
     * @param num
     */
    public void addDayWithdrawNum(int num) {
        setDayWithdrawNum(getDayWithdrawNum()+num);
    }

    /**
     * 添加充值成功money记录
     * @param money
     */
    public void addDayRechargeMoney(double money) {
        setDayRechargeMoney(getDayRechargeMoney()+money);
    }

    /**
     * 添加充值成功记数
     * @param num
     */
    public void addDayRechargeNum(int num) {
        setDayRechargeNum(getDayRechargeNum()+num);
    }

    /**
     * 获取当天临时报表
     */
    @Transient
    @JsonIgnore
    public ReportUserDayEntity getTempDayReport() {
        ReportUserDayEntity reportUserDayEntity = new ReportUserDayEntity();
        reportUserDayEntity.setUserId(getId());
        reportUserDayEntity.setTime(getUpdateTime());
        reportUserDayEntity.setToDay(true);
        reportUserDayEntity.setDayMoney(getDayMoney());
        reportUserDayEntity.setDayOrderMoney(getDayOrderMoney());
        reportUserDayEntity.setDayOrderNum(getDayOrderNum());
        reportUserDayEntity.setDayWithdrawMoney(getDayWithdrawMoney());
        reportUserDayEntity.setDayWithdrawNum(getDayWithdrawNum());
        reportUserDayEntity.setDayRechargeMoney(getDayRechargeMoney());
        reportUserDayEntity.setDayRechargeNum(getDayRechargeNum());
        return reportUserDayEntity;
    }
}
