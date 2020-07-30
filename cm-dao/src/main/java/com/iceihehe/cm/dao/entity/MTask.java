package com.iceihehe.cm.dao.entity;

import java.sql.Date;

public class MTask {
    private int id;
    private int appType;
    private int status;
    private Date startTime;

    private SupportedApp supportedApp;

    public SupportedApp getSupportedApp() {
        return supportedApp;
    }

    public void setSupportedApp(SupportedApp supportedApp) {
        this.supportedApp = supportedApp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
