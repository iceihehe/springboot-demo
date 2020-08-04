package com.iceihehe.cm.service.rabbitmq;

import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Receiver {

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @RabbitListener(queues = "#{sendSmsQueue.name}")
    public void sendSmsReceiver(SendSmsPojo message) {
        logger.info("----------sendSmsReceiver: " + message + "----------");
        System.out.println(message.getContent());

    }


}