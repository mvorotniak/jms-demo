package com.mvoro.jms.jmsdemoproject.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvoro.jms.jmsdemoproject.config.JmsConfig;
import com.mvoro.jms.jmsdemoproject.model.HelloWorldMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    //@Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("Sending Hello-World message...");

        final HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("Hello World!")
            .build();

        jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, helloWorldMessage);

        log.info("Message sent!");
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        log.info("Sending send-and-receive Hello-World message...");

        final HelloWorldMessage helloMessage = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("Hello...")
            .build();

        Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.SEND_AND_RECEIVE_QUEUE, new MessageCreator() {
            @SneakyThrows
            @Override
            public Message createMessage(@NonNull Session session) {
                Message message = session.createTextMessage(objectMapper.writeValueAsString(helloMessage));
                message.setStringProperty("_type", "com.mvoro.jms.jmsdemoproject.model.HelloWorldMessage");

                return message;
            }
        });

        log.info("Message sent! Received response: [{}]", receivedMsg.getBody(String.class));
    }

}
