package ru.nugumanov.price_tracker.core.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.nugumanov.price_common.model.ParseResultModel;
import ru.nugumanov.price_tracker.core.service.ProductOffersService;

/**
 * Считыватель сообщений из топиков кафки
 */
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ProductOffersService productOffersService;

    @KafkaListener(topics = "selector-model-core-collector", groupId = "selector-model-core-collector-group")
    public void listen(ParseResultModel message) {
        productOffersService.processParseResult(message);
    }
}
