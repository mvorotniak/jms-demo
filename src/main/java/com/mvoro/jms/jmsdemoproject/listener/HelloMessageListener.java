package com.mvoro.jms.jmsdemoproject.listener;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mvoro.jms.jmsdemoproject.config.JmsConfig;
import com.mvoro.jms.jmsdemoproject.model.HelloWorldMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.QUEUE_NAME)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders messageHeaders,
        Message message) {

        log.info("Received JMS message [{}]", helloWorldMessage);
    }

}
