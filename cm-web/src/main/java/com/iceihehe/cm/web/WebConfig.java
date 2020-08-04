package com.iceihehe.cm.web;

import com.iceihehe.cm.service.rabbitmq.Receiver;
import com.iceihehe.cm.web.interceptor.LoginInterceptor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.rabbitmq.template.exchange}")
    private String topicExchangeName;
    public static final String defaultListenerMethod = "receiveMessage";

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

//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, defaultListenerMethod);
//    }


}
