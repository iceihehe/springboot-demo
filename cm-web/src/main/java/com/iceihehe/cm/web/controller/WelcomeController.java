package com.iceihehe.cm.web.controller;

import com.iceihehe.cm.service.rabbitmq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {

    @Autowired
    Producer producer;

    @RequestMapping(path = "/welcome")
    public String welcome() {

        producer.sendSmsProducer("Welcome ya!");
        return "Welcome!";
    }
}
