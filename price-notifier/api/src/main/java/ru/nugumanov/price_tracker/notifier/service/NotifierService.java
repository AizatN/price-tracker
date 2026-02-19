package ru.nugumanov.price_tracker.notifier.service;

/**
 * Сервис для отправки уведомлений о ценах
 */
public interface NotifierService {

    /**
     * Отправка отчета о текущих ценах
     */
    void sendPriceReport();
}
