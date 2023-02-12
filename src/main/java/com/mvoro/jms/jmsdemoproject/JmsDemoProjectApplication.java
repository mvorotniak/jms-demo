package com.mvoro.jms.jmsdemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsDemoProjectApplication {

    public static void main(String[] args) throws Exception {

//        Using Docker instead from https://github.com/vromero/activemq-artemis-docker#5-running-the-image
//        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//            .setPersistenceEnabled(false)
//            .setJournalDirectory("target/data/journal")
//            .setSecurityEnabled(false)
//            .addAcceptorConfiguration("invm", "vm://0"));
//
//        server.start();

        SpringApplication.run(JmsDemoProjectApplication.class, args);
    }

}
