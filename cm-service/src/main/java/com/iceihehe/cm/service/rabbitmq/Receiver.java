package com.iceihehe.cm.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Receiver {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @RabbitListener(queues = "#{sendSmsQueue.name}")
    public void sendSmsReceiver(String message) {
        logger.info("sendSmsReceiver: <" + message + ">");
    }


}