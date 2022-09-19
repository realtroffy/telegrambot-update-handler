package telegrambot.handlers.utils;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;

@Slf4j
public final class ExecutorStartMessageUtil {

  private ExecutorStartMessageUtil() {}

  public static void executeStartMessage(
      Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    sendMessage.setText("Hellova, " + message.getFrom().getFirstName());
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute /start in MessageExecutorImpl", e);
    }
  }
}
