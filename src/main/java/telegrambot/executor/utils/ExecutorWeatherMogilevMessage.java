package telegrambot.executor.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.parser.json.WeatherJSONParser;
import telegrambot.timer.Scheduler;

@Slf4j
@Data
@Service
public class ExecutorWeatherMogilevMessage {

  @Value("${custom.telegrambot.yandex_weather_mogilev}")
  private String weatherURL;

  private final WeatherJSONParser weatherJSONParser;

  public void execute(SendMessage sendMessage, TelegramBot telegramBot) {
    if (Scheduler.queueWeatherMogilev.isEmpty()) {
      sendMessage.setText(weatherJSONParser.getWeatherResponse(weatherURL));
    } else {
      sendMessage.setText(Scheduler.queueWeatherMogilev.peek());
    }
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute weather mogilev button");
    }
  }
}
