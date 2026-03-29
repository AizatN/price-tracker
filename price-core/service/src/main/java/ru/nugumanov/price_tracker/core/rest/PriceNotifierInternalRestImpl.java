package ru.nugumanov.price_tracker.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nugumanov.price_common.model.PriceReportModel;
import ru.nugumanov.price_common.model.TelegramUserModel;
import ru.nugumanov.price_tracker.core.service.PriceReportService;
import ru.nugumanov.price_tracker.core.service.TelegramUserService;

import java.util.List;

/**
 * Реализация реста для получения отчета о ценах
 */
@RestController
@RequestMapping("/internal/notifier")
@RequiredArgsConstructor
public class PriceNotifierInternalRestImpl implements PriceNotifierInternalRest {

    private final PriceReportService priceReportService;
    private final TelegramUserService telegramUserService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PriceReportModel> getPriceReport() {
        return priceReportService.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TelegramUserModel> getActive() {
        return telegramUserService.getActive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TelegramUserModel model) {
        telegramUserService.save(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String chatId) {
        telegramUserService.delete(chatId);
    }
}
