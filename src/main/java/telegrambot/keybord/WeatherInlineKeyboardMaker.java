package telegrambot.keybord;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherInlineKeyboardMaker {

    public ReplyKeyboard getWeatherInlineKeyboard() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonMinskWeather = new InlineKeyboardButton();
    buttonMinskWeather.setText("Погода Минск");
    InlineKeyboardButton buttonMogilevWeather = new InlineKeyboardButton();
    buttonMogilevWeather.setText("Погода Могилев");

    buttonMinskWeather.setCallbackData("minsk_weather");
    buttonMogilevWeather.setCallbackData("mogilev_weather");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

    keyboardButtonsRow1.add(buttonMinskWeather);
    keyboardButtonsRow1.add(buttonMogilevWeather);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }
}
