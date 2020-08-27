package com.iceihehe.cm.utils.common;

public enum  RespCode {
    SUCCESS(10000, "成功"),
    PARAMETER_ERROR(20001, "参数错误"),
    LOGIN_ERROR(20002, "登录失败"),
    SYSTEM_ERROR(50000, "系统错误");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
