package telegrambot.parser.xmlimpl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import telegrambot.converter.CHGKConverter;
import telegrambot.exception.GetBodyFromStringXmlException;
import telegrambot.exception.ResponseWebClientNullException;
import telegrambot.parser.XmlParser;
import telegrambot.service.WebClientService;

import java.util.Map;

@Service
@Slf4j
@Data
public class CHGKXmlParser implements XmlParser {

  public static final String ERROR_XML_MESSAGE = "Error parsing xml";
  public static final String ERROR_WEB_CLIENT_MESSAGE_IS_NULL = "WebClient return null message";

  private final String questionUrl;
  private final WebClientService<String> webClientService;
  private final CHGKConverter converter;

  public CHGKXmlParser(
      @Value("${custom.telegrambot.questionUrl}") String questionUrl,
      WebClientService<String> webClientService,
      CHGKConverter converter) {
    this.questionUrl = questionUrl;
    this.webClientService = webClientService;
    this.converter = converter;
  }

  public Map<String, Object> processQuestionButton(Long chatId) {
    ResponseEntity<String> stringQuestionXml =
        webClientService.getResponseEntity(questionUrl, chatId);
    String responseXml;

    if (stringQuestionXml != null) {
      responseXml = stringQuestionXml.getBody();

      if (responseXml == null || responseXml.isEmpty()) {
        log.error(ERROR_XML_MESSAGE);
        throw new GetBodyFromStringXmlException(ERROR_XML_MESSAGE, chatId);
      }

    } else {
      throw new ResponseWebClientNullException(ERROR_WEB_CLIENT_MESSAGE_IS_NULL, chatId);
    }
    return converter.convertXmlToCHGKObject(getWithoutNewLine(responseXml), chatId);
  }

  private String getWithoutNewLine(String responseXml) {
    return responseXml.replace("\n", " ");
  }
}
