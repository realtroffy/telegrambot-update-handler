package telegrambot.executor.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;

@Slf4j
@UtilityClass
public class ExecutorTextButtonNull {

  public static void executeTextButtonNull(SendMessage sendMessage, TelegramBot telegramBot) {
    sendMessage.setText("Ooops. Some problems, I can't see text on button");
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception button text is null in MessageExecutorImpl", e);
    }
  }
}
