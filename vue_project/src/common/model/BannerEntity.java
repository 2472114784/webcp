package com.game.cp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "banner")
public class BannerEntity {
    public static final int BANNER_TYPE_URL=0;//http跳转
    public static final int BANNER_TYPE_ACTION=1;//应用内action

    private long id;
    private String bannerTitle;
    private String bannerContent;
    private String bannerUrl;
    private int bannerType;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "banner_title")
    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }
    @Basic
    @Column(name = "banner_content")
    public String getBannerContent() {
        return bannerContent;
    }

    public void setBannerContent(String bannerContent) {
        this.bannerContent = bannerContent;
    }
    @Basic
    @Column(name = "banner_url")
    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
    @Basic
    @Column(name = "banner_type", nullable = false)
    public int getBannerType() {
        return bannerType;
    }

    public void setBannerType(int bannerType) {
        this.bannerType = bannerType;
    }
}
