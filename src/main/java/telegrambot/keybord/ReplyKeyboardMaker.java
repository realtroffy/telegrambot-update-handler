package telegrambot.keybord;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplyKeyboardMaker {

  public static final String MINSK_MOGILEV_BUTTON_MESSAGE =
          "\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98";
  public static final String MOGILEV_MINSK_BUTTON_MESSAGE =
          "\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98";
  public static final String QUESTION_BUTTON_MESSAGE = "Вопросы Что,где когда?";
  public static final String KFC_BUTTON_MESSAGE = "KFC купоны";
  public static final String BURGER_KING_BUTTON_MESSAGE = "Burger-King купоны";
  public static final String WEATHER_MINSK_BUTTON_MESSAGE = "Минск погода";
  public static final String WEATHER_MOGILEV_BUTTON_MESSAGE = "Могилев погода";

  public ReplyKeyboardMarkup getMainMenuKeyboard() {
    KeyboardRow row1 = new KeyboardRow();
    row1.add(MINSK_MOGILEV_BUTTON_MESSAGE);
    row1.add(MOGILEV_MINSK_BUTTON_MESSAGE);

    KeyboardRow row2 = new KeyboardRow();
    row2.add(QUESTION_BUTTON_MESSAGE);

    KeyboardRow row3 = new KeyboardRow();
    row3.add(BURGER_KING_BUTTON_MESSAGE);
    row3.add(KFC_BUTTON_MESSAGE);

    KeyboardRow row4 = new KeyboardRow();
    row4.add(WEATHER_MINSK_BUTTON_MESSAGE);
    row4.add(WEATHER_MOGILEV_BUTTON_MESSAGE);

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
