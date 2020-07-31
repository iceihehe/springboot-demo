package com.iceihehe.cm.web.pojo;

import com.iceihehe.cm.web.dto.PageReqData;

public class TaskListReqData extends PageReqData {

    private int mobileAccountId;

    public int getMobileAccountId() {
        return mobileAccountId;
    }

    public void setMobileAccountId(int mobileAccountId) {
        this.mobileAccountId = mobileAccountId;
    }
}
