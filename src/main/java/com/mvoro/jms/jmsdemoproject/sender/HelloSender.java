package com.mvoro.jms.jmsdemoproject.sender;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mvoro.jms.jmsdemoproject.config.JmsConfig;
import com.mvoro.jms.jmsdemoproject.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("Sending Hello-World message...");

        final HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("Hello World!")
            .build();

        jmsTemplate.convertAndSend(JmsConfig.QUEUE_NAME, helloWorldMessage);

        log.info("Message sent!");
    }

}
