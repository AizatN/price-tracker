package ru.nugumanov.price_tracker.notifier.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nugumanov.price_tracker.notifier.service.NotifierService;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifierScheduler {

    private final NotifierService notifierService;

    @Scheduled(cron = "${price-notifier.scheduler.cron}")
    public void scheduleNotification() {
        log.info("Starting scheduled price notification");
        notifierService.sendPriceReport();
        log.info("Scheduled price notification completed");
    }
}
