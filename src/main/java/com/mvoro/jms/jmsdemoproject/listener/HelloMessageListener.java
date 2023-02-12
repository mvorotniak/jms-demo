package com.mvoro.jms.jmsdemoproject.listener;

import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mvoro.jms.jmsdemoproject.config.JmsConfig;
import com.mvoro.jms.jmsdemoproject.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.QUEUE_NAME)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders messageHeaders,
        Message message) {

        log.info("Received JMS message [{}]", helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.SEND_AND_RECEIVE_QUEUE)
    public void listenAndRespond(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders messageHeaders,
        Message message, org.springframework.messaging.Message springMessage) throws JMSException {

        log.info("Received JMS message [{}]", helloWorldMessage);

        HelloWorldMessage worldMessage = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("World!")
            .build();

        jmsTemplate.convertAndSend((Destination) springMessage.getHeaders().get("jms_replyTo"), "obtained message!");

        //jmsTemplate.convertAndSend(message.getJMSReplyTo(), worldMessage);
    }

}
