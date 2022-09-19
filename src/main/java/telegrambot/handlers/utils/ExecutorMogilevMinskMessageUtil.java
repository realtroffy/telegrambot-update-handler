package telegrambot.handlers.utils;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.parser.htmlimpl.GoCarHtmlParser;

import java.io.IOException;

@Slf4j
public final class ExecutorMogilevMinskMessageUtil {

  public static final String MOGILEV_MINSK_URL =
      "https://www.gocar.by/Trip/SearchResult?fromName=%D0%9C%D0%BE%D0%B3%D0%B8%D0%BB%D0%B5%D0%B2%20&fromLocation=53.9%7C30.3333333&fromCountry=BY&toName=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA&toLocation=53.9%7C27.5666667&toCountry=BY";

  private ExecutorMogilevMinskMessageUtil() {}

  public static void executeMogilevMinskMessage(
      SendMessage sendMessage, GoCarHtmlParser goCarHtmlParser, TelegramBot telegramBot) {
    try {
      sendMessage.setText(goCarHtmlParser.getMessageFromDocument(MOGILEV_MINSK_URL));
      telegramBot.execute(sendMessage);
    } catch (TelegramApiException | IOException e) {
      log.error("Exception during execute MOGILEV_MINSK_BUTTON_MESSAGE in MessageExecutorImpl", e);
    }
  }
}
