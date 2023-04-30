package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.parser.json.WeatherJSONParser;

@Slf4j
@Service
@Data
public class WeatherGPSMessageHandler implements MessageHandler {

  private final WeatherJSONParser weatherJSONParser;

  @Override
  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {

    sendMessage.setChatId(message.getChatId());

    String weatherURL =
        "https://api.weather.yandex.ru/v2/informers?lat="
            + message.getLocation().getLatitude()
            + "&lon="
            + message.getLocation().getLongitude();

    sendMessage.setText(weatherJSONParser.getWeatherResponse(weatherURL));
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute WEATHER_GPS_MESSAGE_BUTTON", e);
    }
  }
}
