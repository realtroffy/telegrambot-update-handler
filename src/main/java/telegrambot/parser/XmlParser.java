package telegrambot.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface XmlParser {

    Map<String, Object> processQuestionButton(Long chatId) throws JsonProcessingException;
}
