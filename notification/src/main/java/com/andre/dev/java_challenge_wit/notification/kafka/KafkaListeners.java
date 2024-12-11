package com.andre.dev.java_challenge_wit.notification.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * The type Kafka listeners.
 */
@Component
@Slf4j
public class KafkaListeners {

    /**
     * Listener.
     *
     * @param data the data
     */
    @KafkaListener(topics = "calculations", groupId = "groudId")
    void listener(String data) {

        String[] dados = data.split(":");
        log.info("Calculator register another operation: {} with result = {}", dados[0], dados[1]);

    }
}
