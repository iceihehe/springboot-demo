package com.iceihehe.cm.service.rabbitmq;

import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Receiver {

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @RabbitListener(queues = "#{sendSmsQueue.name}")
    public void sendSmsReceiver(SendSmsPojo sendSmsPojo) {
        logger.info("----------sendSmsReceiver: " + sendSmsPojo.getTargetPhone() + " - " + sendSmsPojo.getContent() + "----------");
    }


}