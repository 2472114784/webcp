package com.game.cp.model.entity.openResult.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "open_result_for_tjssc")
public class OpenResultEntityForTJSSC extends OpenResultEntity{
    @Id
    @Column(name = "open_no")
    @Override
    public long getOpenNo() {
        return super.getOpenNo();
    }
    @Basic
    @Column(name = "open_date", nullable = true)
    @Override
    public String getOpenDate() {
        return super.getOpenDate();
    }
    @Basic
    @Column(name = "open_result")
    @Override
    public String getOpenResult() {
        return super.getOpenResult();
    }
    @Basic
    @Column(name = "lottery_id")
    public long getLotteryId() {
        return super.getLotteryId();
    }
    @Basic
    @Column(name = "create_time")
    @Override
    public Timestamp getCreateTime() {
        return super.getCreateTime();
    }
    @Basic
    @Column(name = "update_time")
    @Override
    public Timestamp getUpdateTime() {
        return super.getUpdateTime();
    }
    @Transient
    @Override
    public int getLotteryResultType() {
        return super.getLotteryResultType();
    }
}
