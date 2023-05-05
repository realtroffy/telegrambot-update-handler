package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.keybord.GoCarInlineKeyboardMaker;

@Slf4j
@Service
@Data
public class GoCarMessageHandler implements MessageHandler {

  private final GoCarInlineKeyboardMaker goCarInlineKeyboardMaker;

  @Override
  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    sendMessage.setReplyMarkup(goCarInlineKeyboardMaker.getGoCarInlineKeyboard());
    sendMessage.setText("Выберите направление:");
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute GO_CAR_BUTTON", e);
    }
  }
}
