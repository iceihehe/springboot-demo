package com.iceihehe.cm.service.rabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}