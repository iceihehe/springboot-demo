package com.iceihehe.cm.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Producer {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSms(String message) {

        logger.info("send sms: <" + message + ">");
        Queue sendSmsQueue =  (Queue) applicationContext.getBean("sendSmsQueue");

        rabbitTemplate.convertAndSend(sendSmsQueue.getName(), message);
    }
}
