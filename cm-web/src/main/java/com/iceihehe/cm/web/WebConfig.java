package com.iceihehe.cm.web;

import com.iceihehe.cm.web.interceptor.LoginInterceptor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String topicExchangeName = "cm-exchange";
    public static final String delayExchange = "delay-exchange";

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/*");
    }

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
        return new Queue("queue_send_sms");
    }

    @Bean
    Queue receiveFeedbackQueue() {
        return new Queue("queue_feedback", false);
    }

    @Bean
    Binding sendSmsBinding(TopicExchange exchange) {
        return BindingBuilder.bind(sendSmsQueue()).to(exchange).with(sendSmsQueue().getName());
    }

    @Bean
    Binding receiveFeedbackBinding(TopicExchange exchange) {
        return BindingBuilder.bind(receiveFeedbackQueue()).to(exchange).with(receiveFeedbackQueue().getName());
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
        return new Queue("queue_ping", false);
    }

    @Bean
    Binding pingBinding(TopicExchange exchange) {
        return BindingBuilder.bind(pingQueue()).to(exchange).with(pingQueue().getName());
    }
    @Bean
    Queue taskTimeoutQueue() {
        return new Queue("queue_task_timeout", false);
    }

    @Bean
    Binding taskTimeoutBinding(CustomExchange customExchange) {
        return BindingBuilder.bind(taskTimeoutQueue()).to(customExchange).with(taskTimeoutQueue().getName()).noargs();
    }

}
