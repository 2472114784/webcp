package com.game.cp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "publicize")
public class PublicizeEntity {
    private int id;
    private String publicizeTitle;
    private String publicizeContent;
    private String publicizeUrl;
    private int publicizeType;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Timestamp updateTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "publicize_title", nullable = true, length = 255)
    public String getPublicizeTitle() {
        return publicizeTitle;
    }

    public void setPublicizeTitle(String publicizeTitle) {
        this.publicizeTitle = publicizeTitle;
    }

    @Basic
    @Column(name = "publicize_content", nullable = true, length = 255)
    public String getPublicizeContent() {
        return publicizeContent;
    }

    public void setPublicizeContent(String publicizeContent) {
        this.publicizeContent = publicizeContent;
    }

    @Basic
    @Column(name = "publicize_url", nullable = true, length = 255)
    public String getPublicizeUrl() {
        return publicizeUrl;
    }

    public void setPublicizeUrl(String publicizeUrl) {
        this.publicizeUrl = publicizeUrl;
    }

    @Basic
    @Column(name = "publicize_type", nullable = false)
    public int getPublicizeType() {
        return publicizeType;
    }

    public void setPublicizeType(int publicizeType) {
        this.publicizeType = publicizeType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicizeEntity that = (PublicizeEntity) o;
        return id == that.id &&
                publicizeType == that.publicizeType &&
                Objects.equals(publicizeTitle, that.publicizeTitle) &&
                Objects.equals(publicizeContent, that.publicizeContent) &&
                Objects.equals(publicizeUrl, that.publicizeUrl) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, publicizeTitle, publicizeContent, publicizeUrl, publicizeType, createTime, updateTime);
    }
}
