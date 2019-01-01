package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "room")
public class ChatRoomEntity {

    private long id;
    private String roomId;
    private String roomName;
    private int roomType;
    private int roomOnlineNum;
    private int lvMinLimit;
    private int lvMaxLimit;
    @JsonIgnore//：使用在某个属性上，这样在序列化和反序列化的时候都会忽略这个属性，最直接的效果就是返回的JSON属性是没有这个属性的，一般作用于密码这系列的属性。
    private String roomPassword;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String roomUrl;
    private long lotteryId;
    private String lotteryName;
    private int roomChannelType;
    private String roomChannel;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "room_id", nullable = false)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "room_name", nullable = false, length = 100)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name = "room_online_num", nullable = true)
    public int getRoomOnlineNum() {
        return roomOnlineNum;
    }

    public void setRoomOnlineNum(int roomOnlineNum) {
        this.roomOnlineNum = roomOnlineNum;
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
    @Column(name = "room_url", nullable = true, length = 255)
    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    @Basic
    @Column(name = "lottery_id", nullable = true)
    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }

    @Basic
    @Column(name = "lottery_name", nullable = true, length = 255)
    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    @Basic
    @Column(name = "room_type", nullable = false)
    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }
    @Basic
    @Column(name = "lv_min_limit")
    public int getLvMinLimit() {
        return lvMinLimit;
    }

    public void setLvMinLimit(int lvMinLimit) {
        this.lvMinLimit = lvMinLimit;
    }
    @Basic
    @Column(name = "lv_max_limit")
    public int getLvMaxLimit() {
        return lvMaxLimit;
    }

    public void setLvMaxLimit(int lvMaxLimit) {
        this.lvMaxLimit = lvMaxLimit;
    }

    @Basic
    @Column(name = "room_password")
    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }
    @Basic
    @Column(name = "room_channel_type")
    public int getRoomChannelType() {
        return roomChannelType;
    }

    public void setRoomChannelType(int roomChannelType) {
        this.roomChannelType = roomChannelType;
    }
    @Basic
    @Column(name = "room_channel")
    public String getRoomChannel() {
        return roomChannel;
    }

    public void setRoomChannel(String roomChannel) {
        this.roomChannel = roomChannel;
    }
}

