package com.iceihehe.cm.service.rabbitmq;

import com.iceihehe.cm.service.rabbitmq.pojo.SendSmsPojo;
import com.iceihehe.cm.service.rabbitmq.pojo.TaskTimeoutPojo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Receiver {

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @RabbitListener(queues = "#{sendSmsQueue.name}")
    public void sendSmsReceiver(SendSmsPojo sendSmsPojo, Message message) {
        logger.info("----------sendSmsReceiver: " + sendSmsPojo.getTargetPhone() + " - " + sendSmsPojo.getContent() + "----------");
    }
    @RabbitListener(queues = "#{pingQueue.name}")
    public void pingReceiver() {
        logger.info("----------pingReceiver----------");
    }
    @RabbitListener(queues = "#{taskTimeoutQueue.name}")
    public void taskTimeoutReceiver(TaskTimeoutPojo taskTimeoutPojo) {
        logger.info("----------taskTimeoutReceiver: " + taskTimeoutPojo.getId() + "----------");
    }

}