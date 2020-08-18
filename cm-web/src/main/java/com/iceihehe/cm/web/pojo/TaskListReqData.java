package com.iceihehe.cm.web.pojo;

import com.iceihehe.cm.web.dto.PageReqData;

import javax.validation.constraints.Positive;

public class TaskListReqData extends PageReqData {

    @Positive
    private int mobileAccountId;

    public int getMobileAccountId() {
        return mobileAccountId;
    }

    public void setMobileAccountId(int mobileAccountId) {
        this.mobileAccountId = mobileAccountId;
    }
}
