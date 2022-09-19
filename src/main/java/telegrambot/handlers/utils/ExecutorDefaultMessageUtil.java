package telegrambot.handlers.utils;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;

@Slf4j
public final class ExecutorDefaultMessageUtil {

  public static final String DEFAULT_BOT_MESSAGE =
      "Пожалуйста, воспользуйтесь кнопками для ввода команд";

  private ExecutorDefaultMessageUtil() {}

  public static void executeDefaultMessage(SendMessage sendMessage, TelegramBot telegramBot) {
    sendMessage.setText(DEFAULT_BOT_MESSAGE);
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute DEFAULT_BOT_MESSAGE in MessageExecutorImpl", e);
    }
  }
}
