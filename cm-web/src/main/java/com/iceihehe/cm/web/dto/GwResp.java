package com.iceihehe.cm.web.dto;

import com.iceihehe.cm.utils.common.RespCode;

import java.io.Serializable;
import java.util.List;

public class GwResp<T extends RespData> implements Serializable {

    private int code = RespCode.SUCCESS.getCode();
    private List<T> data;
    private int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
