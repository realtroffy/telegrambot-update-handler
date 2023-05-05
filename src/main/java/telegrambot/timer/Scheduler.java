package telegrambot.timer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import telegrambot.parser.htmlimpl.BurgerKingHtmlParser;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.parser.json.WeatherJSONParser;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Component
@Data
@Slf4j
public class Scheduler {

  private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

  public static final Queue<Map<String, Object>> chgkQueue = new ArrayDeque<>();
  public static final Queue<List<String>> queueKFCPictures = new ArrayDeque<>();
  public static final Queue<Map<String, String>> queueBurgerKing = new ArrayDeque<>();
  public static final Queue<String> queueWeatherMinsk = new ArrayDeque<>();
  public static final Queue<String> queueWeatherMogilev = new ArrayDeque<>();
  private final CHGKXmlParser chgkXmlParser;
  private final KFCHtmlParser kfcHtmlParser;
  private final BurgerKingHtmlParser burgerKingHtmlParser;
  private final WeatherJSONParser weatherJSONParser;

  @Value("${custom.telegrambot.kfc_promo_url}")
  private String kfcPromoUrl;

  @Value("${custom.telegrambot.burger_king_promo_url}")
  private String burgerKingPromoUrl;

  @Value("${custom.telegrambot.yandex_weather_minsk}")
  private String weatherUrlMinsk;

  @Value("${custom.telegrambot.yandex_weather_mogilev}")
  private String weatherUrlMogilev;

  @Scheduled(cron = "0 0 */6 * * ?")
  public void addCHGKQuestionToCashQueue() {
    if (chgkQueue.size() < 30) {
      try {
        chgkQueue.add(chgkXmlParser.processQuestionButton(null));
        log.info("Add chgk question info {}", LocalTime.now().format(timeFormat));
      } catch (Exception e) {
        log.error("Error during execute chgk scheduler {}", e.getMessage(), e);
      }
    }
  }

  @Scheduled(cron = "0 0 8 * * ?")
  public void addKfcPicturesToQueue() {
    if (!queueKFCPictures.isEmpty()) {
      queueKFCPictures.clear();
    }
    try {
      queueKFCPictures.add(kfcHtmlParser.getMessageFromDocument(kfcPromoUrl));
      log.info("Add kfc promo pictures {}", LocalTime.now().format(timeFormat));
    } catch (Exception e) {
      log.error("Error during execute kfc scheduler {}", e.getMessage(), e);
    }
  }

  @Scheduled(cron = "0 0 8 * * ?")
  public void addBurgerMessageToQueue() {
    if (!queueBurgerKing.isEmpty()) {
      queueBurgerKing.clear();
    }
    try {
      queueBurgerKing.add(burgerKingHtmlParser.getMessageFromDocument(burgerKingPromoUrl));
      log.info("Add burger-king info {}", LocalTime.now().format(timeFormat));
    } catch (Exception e) {
      log.error("Error during execute burger-king scheduler {}", e.getMessage(), e);
    }
  }

  @Scheduled(cron = "0 0 */3 * * ?")
  public void addWeatherMinskToQueue() {
    if (!queueWeatherMinsk.isEmpty()) {
      queueWeatherMinsk.clear();
    }
    queueWeatherMinsk.add(weatherJSONParser.getWeatherResponse(weatherUrlMinsk, null));
    log.info("Add weather minsk info {}", LocalTime.now().format(timeFormat));
  }

  @Scheduled(cron = "0 0 */3 * * ?")
  public void addWeatherMogilevToQueue() {
    if (!queueWeatherMogilev.isEmpty()) {
      queueWeatherMogilev.clear();
    }
    queueWeatherMogilev.add(weatherJSONParser.getWeatherResponse(weatherUrlMogilev, null));
    log.info("Add weather mogilev info {}", LocalTime.now().format(timeFormat));
  }
}
