package ru.nugumanov.price_tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_common.model.ProductOffersModel;
import ru.nugumanov.price_tracker.core.mapper.ProductOffersMapper;
import ru.nugumanov.price_tracker.core.repository.ProductOffersRepository;

import java.util.List;

/**
 * Реализация сервиса для работы с вариантами продажи продукта
 */
@Service
@RequiredArgsConstructor
public class ProductOffersServiceImpl implements ProductOffersService {

    private final ProductOffersRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductOffersModel> get() {
        return ProductOffersMapper.get(repository.get());
    }
}
