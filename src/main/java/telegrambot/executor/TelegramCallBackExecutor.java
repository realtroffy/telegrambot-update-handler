package telegrambot.executor;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.handler.impl.DefaultMessageHandler;
import telegrambot.keybord.MainReplyKeyboardMaker;

@Service
@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TelegramCallBackExecutor {

  MainReplyKeyboardMaker mainReplyKeyboardMaker;
  TelegramBot telegramBot;
  MessageHandlerHelper messageHandlerHelper;
  DefaultMessageHandler defaultMessageHandler;

  public void executeCallBack(CallbackQuery callbackQuery) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(callbackQuery.getMessage().getChatId());
    sendMessage.disableWebPagePreview();
    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(mainReplyKeyboardMaker.getMainMenuKeyboard());

    MessageHandler handler =
        messageHandlerHelper
            .getHandlerMap()
            .getOrDefault(callbackQuery.getData(), defaultMessageHandler);
    handler.execute(callbackQuery.getMessage(), sendMessage, telegramBot);
  }
}
