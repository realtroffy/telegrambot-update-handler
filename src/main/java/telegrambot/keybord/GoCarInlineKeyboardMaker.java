package telegrambot.keybord;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoCarInlineKeyboardMaker {

  public ReplyKeyboard getGoCarInlineKeyboard() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonMinskMogilev = new InlineKeyboardButton();
    buttonMinskMogilev.setText(
        "\uD83E\uDD42Минск_Могилев\uD83D\uDC68\u200D\u2764\uFE0F\u200D\uD83D\uDC8B\u200D\uD83D\uDC68");
    InlineKeyboardButton buttonMogilevMinsk = new InlineKeyboardButton();
    buttonMogilevMinsk.setText("\uD83C\uDFEDМогилев_Минск\uD83D\uDC77");

    buttonMinskMogilev.setCallbackData("minsk_mogilev");
    buttonMogilevMinsk.setCallbackData("mogilev_minsk");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

    keyboardButtonsRow1.add(buttonMinskMogilev);
    keyboardButtonsRow1.add(buttonMogilevMinsk);


    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }
}
