package telegrambot.executor;

import lombok.Data;
import org.springframework.stereotype.Service;
import telegrambot.handler.MessageHandler;
import telegrambot.handler.impl.BurgerMessageHandler;
import telegrambot.handler.impl.CHGKMessageHandler;
import telegrambot.handler.impl.CouponsMessageHandler;
import telegrambot.handler.impl.DefaultMessageHandler;
import telegrambot.handler.impl.GoCarMessageHandler;
import telegrambot.handler.impl.KFCMessageHandler;
import telegrambot.handler.impl.MinskMogilevGocarMessageHandler;
import telegrambot.handler.impl.MogilevMinskGoCarMessageHandler;
import telegrambot.handler.impl.NullMessageHandler;
import telegrambot.handler.impl.StartMessageHandler;
import telegrambot.handler.impl.WeatherGPSMessageHandler;
import telegrambot.handler.impl.WeatherMessageHandler;
import telegrambot.handler.impl.WeatherMinskMessageHandler;
import telegrambot.handler.impl.WeatherMogilevMessageHandler;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class MessageHandlerHelper {

  public static final String MINSK_MOGILEV_BUTTON_MESSAGE = "minsk_mogilev";
  public static final String MOGILEV_MINSK_BUTTON_MESSAGE = "mogilev_minsk";
  public static final String START_MESSAGE = "/start";
  public static final String QUESTION_BUTTON_MESSAGE = "Вопросы Что,где когда?";
  public static final String KFC_BUTTON_MESSAGE = "kfc";
  public static final String BURGER_KING_BUTTON_MESSAGE = "burger_king";
  public static final String WEATHER_MINSK_BUTTON_MESSAGE = "minsk_weather";
  public static final String WEATHER_MOGILEV_BUTTON_MESSAGE = "mogilev_weather";
  public static final String FELLOW_GOCAR_MESSAGE = "Найти попутку";
  public static final String COUPONS_BUTTON_MESSAGE = "Купоны, акции, скидки";
  public static final String WEATHER_BUTTON_MESSAGE = "Погода";
  public static final String WEATHER_CURRENT_GPS = "Погода по текущим GPS координатам";

  private final MogilevMinskGoCarMessageHandler mogilevMinskGoCarMessageHandler;
  private final MinskMogilevGocarMessageHandler minskMogilevGocarMessageHandler;
  private final StartMessageHandler startMessageHandler;
  private final GoCarMessageHandler goCarMessageHandler;
  private final CHGKMessageHandler chgkMessageHandler;
  private final KFCMessageHandler kfcMessageHandler;
  private final BurgerMessageHandler burgerMessageHandler;
  private final WeatherMinskMessageHandler weatherMinskMessageHandler;
  private final WeatherMogilevMessageHandler weatherMogilevMessageHandler;
  private final NullMessageHandler nullMessageHandler;
  private final CouponsMessageHandler couponsMessageHandler;
  private final WeatherMessageHandler weatherMessageHandler;
  private final WeatherGPSMessageHandler weatherGPSMessageHandler;
  private final DefaultMessageHandler defaultMessageHandler;

  private final Map<String, MessageHandler> handlerMap = new HashMap<>();

  @PostConstruct
  private void fillHandlerMap() {
    handlerMap.put(START_MESSAGE, startMessageHandler);
    handlerMap.put(MOGILEV_MINSK_BUTTON_MESSAGE, mogilevMinskGoCarMessageHandler);
    handlerMap.put(MINSK_MOGILEV_BUTTON_MESSAGE, minskMogilevGocarMessageHandler);
    handlerMap.put(FELLOW_GOCAR_MESSAGE, goCarMessageHandler);
    handlerMap.put(QUESTION_BUTTON_MESSAGE, chgkMessageHandler);
    handlerMap.put(KFC_BUTTON_MESSAGE, kfcMessageHandler);
    handlerMap.put(BURGER_KING_BUTTON_MESSAGE, burgerMessageHandler);
    handlerMap.put(WEATHER_MINSK_BUTTON_MESSAGE, weatherMinskMessageHandler);
    handlerMap.put(WEATHER_MOGILEV_BUTTON_MESSAGE, weatherMogilevMessageHandler);
    handlerMap.put(COUPONS_BUTTON_MESSAGE, couponsMessageHandler);
    handlerMap.put(WEATHER_BUTTON_MESSAGE, weatherMessageHandler);
    handlerMap.put(WEATHER_CURRENT_GPS, weatherGPSMessageHandler);
    handlerMap.put(null, nullMessageHandler);
  }
}
