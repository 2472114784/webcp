package com.game.cp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "config_app_update")
public class ApkUpdateEntity  {
    private long id;
        /**
         * 最小非强制版本
         */
        private int minVersion;
        /**
         * 最小强制版本
         */
        private int minForceVersion;
        /**
         * 升级文案
         */
        private String updateInfo;
        /**
         * 用于显示的app版本
         */
        private String appVersion;
        /**
         * 下载地址
         */
        private String downloadUrl;
    /**
     * ios 2 android 1
     */
    private int deviceType;
    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "min_version", nullable = false)
    public int getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(int minVersion) {
        this.minVersion = minVersion;
    }
    @Basic
    @Column(name = "min_force_version", nullable = false)
    public int getMinForceVersion() {
        return minForceVersion;
    }

    public void setMinForceVersion(int minForceVersion) {
        this.minForceVersion = minForceVersion;
    }
    @Basic
    @Column(name = "update_info")
    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }
    @Basic
    @Column(name = "app_version")
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    @Basic
    @Column(name = "download_url", nullable = false)
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    @Basic
    @Column(name = "device_type", nullable = false)
    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }
}
