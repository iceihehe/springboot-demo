package com.iceihehe.cm.web.pojo;

import com.iceihehe.cm.web.dto.ReqData;

public class TaskListReqData extends ReqData {

    private int mobileAccountId;

    public int getMobileAccountId() {
        return mobileAccountId;
    }

    public void setMobileAccountId(int mobileAccountId) {
        this.mobileAccountId = mobileAccountId;
    }
}
