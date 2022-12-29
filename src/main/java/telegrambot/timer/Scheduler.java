package telegrambot.timer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import telegrambot.executor.utils.ExecutorKfcMessage;
import telegrambot.parser.htmlimpl.BurgerKingHtmlParser;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Component
@Data
public class Scheduler {

  public static final Queue<Map<String, Object>> chgkQueue = new ArrayDeque<>();
  public static final Queue<List<String>> queueKFCPictures = new ArrayDeque<>();
  public static final Queue<Map<String, String>> queueBurgerKing = new ArrayDeque<>();
  private final CHGKXmlParser chgkXmlParser;
  private final KFCHtmlParser kfcHtmlParser;
  private final BurgerKingHtmlParser burgerKingHtmlParser;

  @Value("${custom.telegrambot.kfc_promo_url}")
  private String kfcPromoUrl;
  @Value("${custom.telegrambot.burger_king_promo_url}")
  private String burgerKingPromoUrl;

  @Scheduled(cron = "@daily")
  public void addCHGKQuestionToCashQueue() throws IOException {
    if (chgkQueue.size() < 30) {
      chgkQueue.add(chgkXmlParser.processQuestionButton());
    }
    if (!queueKFCPictures.isEmpty()) {
      queueKFCPictures.clear();
    }
    queueKFCPictures.add(kfcHtmlParser.getMessageFromDocument(kfcPromoUrl));
    if(!queueBurgerKing.isEmpty()){
      queueBurgerKing.clear();
    }
    queueBurgerKing.add(burgerKingHtmlParser.getMessageFromDocument(burgerKingPromoUrl));
  }
}
