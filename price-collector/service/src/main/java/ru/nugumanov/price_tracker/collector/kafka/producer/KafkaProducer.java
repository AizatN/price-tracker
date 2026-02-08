package ru.nugumanov.price_tracker.collector.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_common.model.ParseResultModel;

import static java.util.Objects.isNull;

/**
 * Сервис для отправки сообщений в топики кафки
 */
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, ParseResultModel> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, ParseResultModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ParseResultModel message) {
        if (isNull(message)) {
            return;
        }
        kafkaTemplate.send("selector-model-core-collector", message);
    }
}
