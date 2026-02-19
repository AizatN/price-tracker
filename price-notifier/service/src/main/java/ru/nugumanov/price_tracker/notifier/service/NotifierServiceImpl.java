package ru.nugumanov.price_tracker.notifier.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_tracker.notifier.feign.PriceReportRestFeign;
import ru.nugumanov.price_tracker.notifier.formatter.PriceMessageFormatter;
import ru.nugumanov.price_tracker.notifier.telegram.TelegramBotClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotifierServiceImpl implements NotifierService {

    private final PriceReportRestFeign priceReportRestFeign;
    private final PriceMessageFormatter priceMessageFormatter;
    private final TelegramBotClient telegramBotClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPriceReport() {
        var reports = priceReportRestFeign.get();
        if (reports.isEmpty()) {
            log.info("No price reports to send");
            return;
        }
        var message = priceMessageFormatter.format(reports);
        telegramBotClient.sendMessage(message);
    }
}
