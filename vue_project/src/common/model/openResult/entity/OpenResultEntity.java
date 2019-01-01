package com.game.cp.model.entity.openResult.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;

public class OpenResultEntity {
    private long openNo;
    private String openDate;
    private String openResult;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp updateTime;
    private long lotteryId;//所属于那个菜种
    private int lotteryResultType;//显示样式类型


    @Id
    @Column(name = "open_no")
    public long getOpenNo() {
        return openNo;
    }

    public void setOpenNo(long openNo) {
        this.openNo = openNo;
    }

    @Basic
    @Column(name = "open_date", nullable = true)
    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    @Basic
    @Column(name = "open_result")
    public String getOpenResult() {
        return openResult;
    }

    public void setOpenResult(String openResult) {
        this.openResult = openResult;
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
    @Column(name = "lottery_id")
    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }
    @Transient
    public int getLotteryResultType() {
        return lotteryResultType;
    }

    public void setLotteryResultType(int lotteryResultType) {
        this.lotteryResultType = lotteryResultType;
    }

    @Override
    public String toString() {
        return "OpenResultEntity{" +
                "openNo=" + openNo +
                ", openDate='" + openDate + '\'' +
                ", openResult='" + openResult + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lotteryId=" + lotteryId +
                '}';
    }
}
