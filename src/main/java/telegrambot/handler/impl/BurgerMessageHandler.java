package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.executor.ExecutorSendPhotoUtil;
import telegrambot.handler.MessageHandler;
import telegrambot.parser.htmlimpl.BurgerKingHtmlParser;
import telegrambot.timer.Scheduler;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Data
@Service
public class BurgerMessageHandler implements MessageHandler {

  @Value("${custom.telegrambot.burger_king_promo_url}")
  private String burgerKingPromoUrl;

  private final BurgerKingHtmlParser burgerKingHtmlParser;

  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    Map<String, String> burgerKingPictureUrls;
    try {
      if (Scheduler.queueBurgerKing.isEmpty()) {
        burgerKingPictureUrls = burgerKingHtmlParser.getMessageFromDocument(burgerKingPromoUrl);
      } else {
        burgerKingPictureUrls = Scheduler.queueBurgerKing.peek();
      }
      for (Map.Entry<String, String> entry : burgerKingPictureUrls.entrySet()) {
        ExecutorSendPhotoUtil.executePhoto(message.getChatId(), entry.getKey(), telegramBot);
        sendMessage.setText(entry.getValue());
        telegramBot.execute(sendMessage);
      }
    } catch (TelegramApiException | IOException e) {
      log.error("Exception during execute BURGER_KING_BUTTON_MESSAGE", e);
    }
  }
}
