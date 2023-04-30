package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
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

  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    Double latitude = message.getLocation().getLatitude();
    Double longitude = message.getLocation().getLongitude();
    sendMessage.setChatId(message.getChatId());
    sendMessage.setText("Latitude: " + latitude + ", longitude: " + longitude);
    try {
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error("Exception during execute weather minsk button");
    }
  }
}
