package ru.nugumanov.price_tracker.core.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.jooq.JSONB;
import ru.nugumanov.price_common.model.ParserConfigModel;

@UtilityClass
public class ParserConfigMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Получение модели конфигурации парсинга из json
     *
     * @param parserConfig JSONB строка с данными конфигурации парсинга
     * @return Модель конфигурации парсинга
     * @throws JsonProcessingException Ошибка при обработке json
     */
    public ParserConfigModel get(JSONB parserConfig) throws JsonProcessingException {
        return mapper.readValue(parserConfig.data(), ParserConfigModel.class);
    }
}
