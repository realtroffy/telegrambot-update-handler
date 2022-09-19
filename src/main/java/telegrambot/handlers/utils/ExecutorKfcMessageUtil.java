package telegrambot.handlers.utils;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.parser.htmlimpl.KFCHtmlParser;

import java.io.IOException;
import java.util.List;

@Slf4j
public final class ExecutorKfcMessageUtil {

  public static final String KFC_URL_PROMO = "https://www.kfc.by/promo/182";

  private ExecutorKfcMessageUtil() {}

  public static void executeKfcMessage(
      Long chatId, KFCHtmlParser kfcHtmlParser, TelegramBot telegramBot) {
    List<String> kfcPictureUrls;
    try {
      kfcPictureUrls = kfcHtmlParser.getMessageFromDocument(KFC_URL_PROMO);
      for (String pictureUrl : kfcPictureUrls) {
        ExecutorSendPhoto.executePhoto(chatId, pictureUrl, telegramBot);
      }
    } catch (TelegramApiException | IOException e) {
      log.error("Exception during execute KFC_BUTTON_MESSAGE in MessageExecutorImpl", e);
    }
  }
}
