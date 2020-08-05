package com.iceihehe.cm.service.rabbitmq;

import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import com.iceihehe.cm.service.rabbitmq.pojo.TaskTimeoutPojo;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class Producer {
    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @Value("#{sendSmsQueue.name}")
    private String sendSmsRoutingKey;

    @Value("#{pingQueue.name}")
    private String pingRoutingKey;

    @Value("#{taskTimeoutQueue.name}")
    private String taskTimeoutRoutingKey;

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

    @Scheduled(fixedDelay = 10000)
    public void pingProducer() {
        logger.info("----------pingProducer----------");
        rabbitTemplate.convertAndSend(pingRoutingKey, "ping");
    }

    public void taskTimeoutProducer(TaskTimeoutPojo taskTimeoutPojo) {
        logger.info("----------taskTimeoutProducer: " + taskTimeoutPojo.getId() + "----------");
        rabbitTemplate.convertAndSend("delay-exchange", taskTimeoutRoutingKey, taskTimeoutPojo, message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setDelay(10000);
            return message;
        });
    }
}
