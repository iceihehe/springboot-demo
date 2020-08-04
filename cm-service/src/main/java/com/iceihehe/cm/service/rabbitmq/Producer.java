package com.iceihehe.cm.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Producer {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("#{sendSmsQueue.name}")
    private String sendSmsRoutingKey;

    private MessageProperties properties;


    public Producer() {
        MessagePropertiesBuilder messagePropertiesBuilder = MessagePropertiesBuilder.newInstance();
        messagePropertiesBuilder.setContentType("application-json");
        properties = messagePropertiesBuilder.build();
    }

    public void sendSmsProducer(SendSmsPojo sendSmsPojo) {
        logger.info("----------sendSmsProducer: " + sendSmsPojo.getTargetPhone() + " - " + sendSmsPojo.getContent() + "----------");
        properties.setHeader("id", "1");
        try {
            String s = new ObjectMapper().writeValueAsString(sendSmsPojo);
            Message message = new Message(s.getBytes(), properties);
            rabbitTemplate.send(sendSmsRoutingKey, message);
        } catch (Exception ignored) {
        }

    }
}
