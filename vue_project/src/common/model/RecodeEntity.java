package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "recode")
public class RecodeEntity {
    private long id;
    private int recodeType;
    private String desc;
    private String title;
    private String value;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp time;
    private long userId;
    private Integer status;
    private long okeyId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "recode_type", nullable = false)
    public int getRecodeType() {
        return recodeType;
    }

    public void setRecodeType(int recodeType) {
        this.recodeType = recodeType;
    }

    @Basic
    @Column(name = "recode_desc", nullable = true, length = 255)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 255)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Basic
    @Column(name = "okey_id", nullable = true)
    public long getOkeyId() {
        return okeyId;
    }

    public void setOkeyId(long okeyId) {
        this.okeyId = okeyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecodeEntity that = (RecodeEntity) o;
        return id == that.id &&
                recodeType == that.recodeType &&
                userId == that.userId &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(title, that.title) &&
                Objects.equals(value, that.value) &&
                Objects.equals(time, that.time) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, recodeType, desc, title, value, time, userId, status);
    }
}
