package telegrambot.keybord;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CouponsInlineKeyboardMaker {

  public ReplyKeyboard getCouponsInlineKeyboard() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton buttonBurgerKing = new InlineKeyboardButton();
    buttonBurgerKing.setText("\uD83C\uDF54Купоны Бургер-Кинг\uD83D\uDC37");
    InlineKeyboardButton buttonKFC = new InlineKeyboardButton();
    buttonKFC.setText("\uD83C\uDF57Купоны KFC\uD83D\uDC14");

    buttonBurgerKing.setCallbackData("burger_king");
    buttonKFC.setCallbackData("kfc");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

    keyboardButtonsRow1.add(buttonBurgerKing);
    keyboardButtonsRow1.add(buttonKFC);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }
}
