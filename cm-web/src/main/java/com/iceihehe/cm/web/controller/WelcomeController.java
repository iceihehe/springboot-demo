package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.service.rabbitmq.Producer;
import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import com.iceihehe.cm.service.rabbitmq.pojo.TaskTimeoutPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {

    @Autowired
    Producer producer;

    @RequestMapping(path = "/welcome")
    public String welcome() {

//        SendSmsPojo sendSmsPojo = new SendSmsPojo();
//        sendSmsPojo.setContent("Welcome ya!");
//        sendSmsPojo.setTargetPhone("110");
//        producer.sendSmsProducer(sendSmsPojo);
        TaskTimeoutPojo taskTimeoutPojo = new TaskTimeoutPojo();
        taskTimeoutPojo.setId(110);
        producer.taskTimeoutProducer(taskTimeoutPojo);
        return "Welcome!";
    }

}
