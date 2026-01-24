package ru.nugumanov.price_tracker.collector.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nugumanov.price_tracker.collector.service.PriceParserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceParserScheduler {

    private final PriceParserService priceParserService;

    @Scheduled(cron = "${price-collector.scheduler.cron}")
    public void scheduleParsing() {
        log.info("Starting scheduled price parsing");
        priceParserService.parse();
        log.info("Scheduled price parsing completed");
    }
}
