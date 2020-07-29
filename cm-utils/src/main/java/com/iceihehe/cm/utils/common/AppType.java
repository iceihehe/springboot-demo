package com.iceihehe.cm.utils.common;

import java.util.HashMap;

public enum AppType {
    DIDI(40003, "didi"),
    TELEGRAM(50001, "telegram");

    private int code;
    private String name;
    private static HashMap<Integer, AppType> map = new HashMap<>();
    static {
        for (AppType appType : AppType.values()) {
            map.put(appType.getCode(), appType);
        }
    }

    AppType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static AppType findByCode(int code) {
        return map.get(code);
    }
}