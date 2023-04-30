package telegrambot.executor;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.handler.impl.DefaultMessageHandler;
import telegrambot.keybord.ReplyKeyboardMaker;

@Service
@Data
public class TelegramCallBackExecutor {

  private final ReplyKeyboardMaker replyKeyboardMaker;
  private final TelegramBot telegramBot;
  private final MessageHandlerHelper messageHandlerHelper;
  private final DefaultMessageHandler defaultMessageHandler;

  public void executeCallBack(CallbackQuery callbackQuery) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(callbackQuery.getMessage().getChatId());
    sendMessage.disableWebPagePreview();
    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());

    MessageHandler handler =
        messageHandlerHelper
            .getHandlerMap()
            .getOrDefault(callbackQuery.getData(), defaultMessageHandler);
    handler.execute(callbackQuery.getMessage(), sendMessage, telegramBot);
  }
}
