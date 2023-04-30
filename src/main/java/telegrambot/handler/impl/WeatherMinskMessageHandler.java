package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.parser.json.WeatherJSONParser;
import telegrambot.timer.Scheduler;

@Slf4j
@Service
@Data
public class WeatherMinskMessageHandler implements MessageHandler {

  @Value("${custom.telegrambot.yandex_weather_minsk}")
  private String weatherURL;

  private final WeatherJSONParser weatherJSONParser;

  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    if (Scheduler.queueWeatherMinsk.isEmpty()) {
      sendMessage.setText(weatherJSONParser.getWeatherResponse(weatherURL));
    } else {
      sendMessage.setText(Scheduler.queueWeatherMinsk.peek());
    }
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute weather minsk button");
    }
  }
}
