package telegrambot.executor.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.parser.htmlimpl.BurgerKingHtmlParser;

import java.io.IOException;
import java.util.Map;

@Slf4j
@UtilityClass
public class ExecutorBurgerMessageUtil {

  public static final String BURGER_URL_PROMO = "https://burger-king.by/coupons/";

  public static void executeBurgerMessage(SendMessage sendMessage,
      Long chatId, BurgerKingHtmlParser burgerKingHtmlParser, TelegramBot telegramBot) {
    Map<String, String> burgerKingPictureUrls;
    try {
      burgerKingPictureUrls = burgerKingHtmlParser.getMessageFromDocument(BURGER_URL_PROMO);
      for (Map.Entry<String, String> entry : burgerKingPictureUrls.entrySet()) {
        ExecutorSendPhoto.executePhoto(chatId, entry.getKey(), telegramBot);
        sendMessage.setText(entry.getValue());
        telegramBot.execute(sendMessage);
      }
    } catch (TelegramApiException | IOException e) {
      log.error("Exception during execute BURGER_KING_BUTTON_MESSAGE in MessageExecutorImpl", e);
    }
  }
}
