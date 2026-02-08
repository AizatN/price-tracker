package ru.nugumanov.price_tracker.core.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic selectorModelCoreCollectorTopic() {
        return TopicBuilder.name("selector-model-core-collector")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
