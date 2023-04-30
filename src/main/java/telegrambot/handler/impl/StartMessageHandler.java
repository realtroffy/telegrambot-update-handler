package telegrambot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;

@Slf4j
@Service
public class StartMessageHandler implements MessageHandler {

  @Override
  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    sendMessage.setText("Hellova, " + message.getFrom().getFirstName());
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute /start", e);
    }
  }
}
