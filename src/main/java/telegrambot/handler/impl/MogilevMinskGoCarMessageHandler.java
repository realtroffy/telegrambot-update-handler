package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.parser.htmlimpl.GoCarHtmlParser;

import java.io.IOException;

@Slf4j
@Data
@Service
public class MogilevMinskGoCarMessageHandler implements MessageHandler {

  public static final String MOGILEV_MINSK_URL =
      "https://www.gocar.by/Trip/SearchResult?fromName=%D0%9C%D0%BE%D0%B3%D0%B8%D0%BB%D0%B5%D0%B2%20&fromLocation=53.9%7C30.3333333&fromCountry=BY&toName=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA&toLocation=53.9%7C27.5666667&toCountry=BY";

  private final GoCarHtmlParser goCarHtmlParser;

  @Override
  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    try {
      sendMessage.setText(goCarHtmlParser.getMessageFromDocument(MOGILEV_MINSK_URL));
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException | IOException e) {
      log.error("Exception during execute MOGILEV_MINSK_BUTTON_MESSAGE", e);
    }
  }
}
