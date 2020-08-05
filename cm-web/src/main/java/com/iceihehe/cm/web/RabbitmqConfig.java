package com.iceihehe.cm.web;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;


@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitmqConfig {

    @Value("${delay-exchange}")
    private String delayExchange;

    @Value("${topic-exchange}")
    private String topicExchangeName;

    @Value("${send-sms-queue-name}")
    private String sendSmsQueueName;

    @Value("${ping-queue-name}")
    private String pingQueueName;

    @Value("${task-timeout-queue-name}")
    private String taskTimeoutQueueName;

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(delayExchange, "x-delayed-message", true, false, args);
    }

    @Bean
    Queue sendSmsQueue() {
        return new Queue(sendSmsQueueName);
    }


    @Bean
    Binding sendSmsBinding(TopicExchange exchange) {
        return BindingBuilder.bind(sendSmsQueue()).to(exchange).with(sendSmsQueueName);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory jsonFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    Queue pingQueue() {
        return new Queue(pingQueueName, false);
    }

    @Bean
    Binding pingBinding(TopicExchange exchange) {
        return BindingBuilder.bind(pingQueue()).to(exchange).with(pingQueueName);
    }
    @Bean
    Queue taskTimeoutQueue() {
        return new Queue(taskTimeoutQueueName);
    }

    @Bean
    Binding taskTimeoutBinding(CustomExchange customExchange) {
        return BindingBuilder.bind(taskTimeoutQueue()).to(customExchange).with(taskTimeoutQueueName).noargs();
    }
}
