package telegrambot.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;
import telegrambot.executor.ExecutorSendPhotoUtil;
import telegrambot.handler.MessageHandler;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.timer.Scheduler;

import java.util.List;

@Slf4j
@Data
@Service
public class KFCMessageHandler implements MessageHandler {

  @Value("${custom.telegrambot.kfc_promo_url}")
  private String kfcPromoUrl;

  private final KFCHtmlParser kfcHtmlParser;

  public void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot) {
    List<String> kfcPictureUrls;
    try {
      if (Scheduler.queueKFCPictures.isEmpty()) {
        kfcPictureUrls = kfcHtmlParser.getMessageFromDocument(kfcPromoUrl);
      } else {
        kfcPictureUrls = Scheduler.queueKFCPictures.peek();
      }
      for (String pictureUrl : kfcPictureUrls) {
        ExecutorSendPhotoUtil.executePhoto(message.getChatId(), pictureUrl, telegramBot);
      }
    } catch (Exception e) {
      log.error("Exception during execute KFC button");
    }
  }
}
