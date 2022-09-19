package telegrambot.handlers.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

import java.util.List;
import java.util.Map;

@Slf4j
public final class ExecutorCHGKMessageUtil {

  public static final String KEY_QUESTION_COMPLETE = "completeQuestion";
  public static final String KEY_PICTURE_QUESTION_URLS = "pictureUrls";

  private ExecutorCHGKMessageUtil() {}

  @SuppressWarnings("unchecked")
  public static void executeCHGKMessage(
      Long chatId, SendMessage sendMessage, CHGKXmlParser chgkXmlParser, TelegramBot telegramBot) {
    Map<String, Object> questionMap;
    try {
      questionMap = chgkXmlParser.processQuestionButton();
      String messageFromXml = (String) questionMap.get(KEY_QUESTION_COMPLETE);
      List<String> chgkPictureUrls = (List<String>) questionMap.get(KEY_PICTURE_QUESTION_URLS);
      sendMessage.setText(messageFromXml);
      telegramBot.execute(sendMessage);
      for (String pictureUrl : chgkPictureUrls) {
        ExecutorSendPhoto.executePhoto(chatId, pictureUrl, telegramBot);
      }
    } catch (TelegramApiException | JsonProcessingException e) {
      log.error("Exception during execute QUESTION_BUTTON_MESSAGE in MessageExecutorImpl", e);
    }
  }
}
