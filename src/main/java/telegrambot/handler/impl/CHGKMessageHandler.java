package telegrambot.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.executor.ExecutorSendPhotoUtil;
import telegrambot.handler.MessageHandler;
import telegrambot.parser.xmlimpl.CHGKXmlParser;
import telegrambot.timer.Scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
@Service
public class CHGKMessageHandler implements MessageHandler {

  public static final String KEY_QUESTION_COMPLETE = "completeQuestion";
  public static final String KEY_PICTURE_QUESTION_URLS = "pictureUrls";
  public static final String ERROR_MESSAGE_CHGK_EXECUTOR =
      "Exception during execute QUESTION_BUTTON_MESSAGE";
  private final CHGKXmlParser chgkXmlParser;

  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {

    Map<String, Object> questionMap = new HashMap<>();

    if (Scheduler.chgkQueue.isEmpty()) {
      try {
        questionMap = chgkXmlParser.processQuestionButton();
      } catch (JsonProcessingException e) {
        log.error(ERROR_MESSAGE_CHGK_EXECUTOR, e);
      }

    } else {
      questionMap = Scheduler.chgkQueue.poll();
    }

    String messageFromXml = (String) questionMap.get(KEY_QUESTION_COMPLETE);
    List<String> chgkPictureUrls = (List<String>) questionMap.get(KEY_PICTURE_QUESTION_URLS);
    sendMessage.setText(messageFromXml);

    try {
      telegramBot.execute(sendMessage);
      for (String pictureUrl : chgkPictureUrls) {
        ExecutorSendPhotoUtil.executePhoto(message.getChatId(), pictureUrl, telegramBot);
      }
    } catch (TelegramApiException e) {
      log.error(ERROR_MESSAGE_CHGK_EXECUTOR, e);
    }
  }
}
