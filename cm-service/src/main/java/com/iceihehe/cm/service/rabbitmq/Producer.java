package com.iceihehe.cm.service.rabbitmq;

import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class Producer {
    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @Value("#{sendSmsQueue.name}")
    private String sendSmsRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSmsProducer(SendSmsPojo sendSmsPojo) {
        logger.info("----------sendSmsProducer: " + sendSmsPojo.getTargetPhone() + " - " + sendSmsPojo.getContent() + "----------");
        String messageId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(sendSmsRoutingKey, sendSmsPojo, message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setHeader("id", messageId);
            return message;
        });
    }
}
