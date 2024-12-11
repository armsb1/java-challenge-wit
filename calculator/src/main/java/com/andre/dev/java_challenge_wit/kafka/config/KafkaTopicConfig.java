package com.andre.dev.java_challenge_wit.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * The type Kafka topic config.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Calculations topic new topic.
     *
     * @return the new topic
     */
    @Bean
    public NewTopic calculationsTopic() {
        return TopicBuilder.name("calculations")
                .build();
    }
}
