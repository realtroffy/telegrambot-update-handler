package telegrambot.executor;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.handler.impl.DefaultMessageHandler;
import telegrambot.keybord.MainReplyKeyboardMaker;

@Service
@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TelegramMessageExecutor {

  DefaultMessageHandler defaultMessageHandler;
  TelegramBot telegramBot;
  MainReplyKeyboardMaker mainReplyKeyboardMaker;
  MessageHandlerHelper messageHandlerHelper;

  public void executeMessage(Message message) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId());
    sendMessage.disableWebPagePreview();
    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(mainReplyKeyboardMaker.getMainMenuKeyboard());

    MessageHandler handler =
        messageHandlerHelper.getHandlerMap().getOrDefault(message.getText(), defaultMessageHandler);
    handler.execute(message, sendMessage, telegramBot);
  }
}
