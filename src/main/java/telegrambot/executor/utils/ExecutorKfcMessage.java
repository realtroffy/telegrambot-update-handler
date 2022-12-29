package telegrambot.executor.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import telegrambot.component.TelegramBot;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.timer.Scheduler;

import java.util.List;

@Slf4j
@Data
@Service
public class ExecutorKfcMessage {

  @Value("${custom.telegrambot.kfc_promo_url}")
  private String kfcPromoUrl;

  private KFCHtmlParser kfcHtmlParser;

  public void execute(Long chatId, TelegramBot telegramBot) {
    List<String> kfcPictureUrls;
    try {
      if (Scheduler.queueKFCPictures.isEmpty()) {
        kfcPictureUrls = kfcHtmlParser.getMessageFromDocument(kfcPromoUrl);
      } else {
        kfcPictureUrls = Scheduler.queueKFCPictures.poll();
      }
      for (String pictureUrl : kfcPictureUrls) {
        ExecutorSendPhoto.executePhoto(chatId, pictureUrl, telegramBot);
      }
    } catch (Exception e) {
      log.error("Exception during execute KFC button");
    }
  }
}
