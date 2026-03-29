package ru.nugumanov.price_tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_common.model.TelegramUserModel;
import ru.nugumanov.price_tracker.core.mapper.TelegramUserMapper;
import ru.nugumanov.price_tracker.core.repository.TelegramUserRepository;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Реализация сервиса для работы с пользователями телеграм бота
 */
@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TelegramUserModel> getActive() {
        return TelegramUserMapper.getModels(repository.getActive());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TelegramUserModel model) {
        if (isNull(model)) {
            return;
        }
        repository.save(TelegramUserMapper.get(model));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String chatId) {
        if (StringUtils.isBlank(chatId)) {
            return;
        }
        var userOptional = repository.get(chatId);
        if (userOptional.isEmpty()) {
            return;
        }
        repository.delete(chatId);
    }
}
