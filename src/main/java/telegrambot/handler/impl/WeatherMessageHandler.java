package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.keybord.WeatherInlineKeyboardMaker;

@Slf4j
@Service
@Data
public class WeatherMessageHandler implements MessageHandler {

  private final WeatherInlineKeyboardMaker weatherInlineKeyboardMaker;

  @Override
  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    sendMessage.setReplyMarkup(weatherInlineKeyboardMaker.getWeatherInlineKeyboard());
    sendMessage.setText("Выберите город или текущее местоположение по GPS:");
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute WEATHER_MESSAGE_BUTTON", e);
    }
  }
}
