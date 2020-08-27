package com.iceihehe.cm.web.pojo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LoginReqData {

    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}