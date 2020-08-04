package com.iceihehe.cm.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Producer {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("#{sendSmsQueue.name}")
    private String sendSmsRoutingKey;

    public void sendSmsProducer(String message) {

        logger.info("sendSmsProducer: <" + message + ">");

        rabbitTemplate.convertAndSend(sendSmsRoutingKey, message);
    }
}
