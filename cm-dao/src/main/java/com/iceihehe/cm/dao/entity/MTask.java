package com.iceihehe.cm.dao.entity;

import java.sql.Date;

public class MTask {
    private Integer id;
    private Integer appType;
    private Integer status;
    private Date createTime;
    private Date startTime;
    private Date updateTime;

    private SupportedApp supportedApp;

    public SupportedApp getSupportedApp() {
        return supportedApp;
    }

    public void setSupportedApp(SupportedApp supportedApp) {
        this.supportedApp = supportedApp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
