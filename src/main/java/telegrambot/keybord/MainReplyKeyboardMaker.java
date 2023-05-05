package telegrambot.keybord;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainReplyKeyboardMaker {

  public static final String QUESTION_BUTTON_MESSAGE = "Вопросы Что,где когда?";
  public static final String COUPONS_BUTTON_MESSAGE = "Купоны, акции, скидки";
  public static final String WEATHER_BUTTON_MESSAGE = "Погода";
  public static final String WEATHER_CURRENT_GPS = "Погода по текущим GPS координатам";
  public static final String FELLOW_GOCAR_MESSAGE = "Найти попутку";

  public ReplyKeyboardMarkup getMainMenuKeyboard() {
    KeyboardRow row1 = new KeyboardRow();
    row1.add(FELLOW_GOCAR_MESSAGE);

    KeyboardRow row2 = new KeyboardRow();
    row2.add(QUESTION_BUTTON_MESSAGE);

    KeyboardRow row3 = new KeyboardRow();
    row3.add(COUPONS_BUTTON_MESSAGE);

    KeyboardRow row4 = new KeyboardRow();
    row4.add(WEATHER_BUTTON_MESSAGE);

    KeyboardButton keyboardCurrentGPSWeather = new KeyboardButton(WEATHER_CURRENT_GPS);
    keyboardCurrentGPSWeather.setRequestLocation(true);
    keyboardCurrentGPSWeather.setText(WEATHER_CURRENT_GPS);
    row4.add(keyboardCurrentGPSWeather);

    List<KeyboardRow> keyboard = new ArrayList<>();
    keyboard.add(row1);
    keyboard.add(row2);
    keyboard.add(row3);
    keyboard.add(row4);

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setKeyboard(keyboard);
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);

    return replyKeyboardMarkup;
  }
}
