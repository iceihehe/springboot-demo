package com.iceihehe.cm.service.rabbitmq.pojo;

import java.io.Serializable;

public class SendSmsPojo {

    private String targetPhone;
    private String content;


    public String getTargetPhone() {
        return targetPhone;
    }

    public void setTargetPhone(String targetPhone) {
        this.targetPhone = targetPhone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
