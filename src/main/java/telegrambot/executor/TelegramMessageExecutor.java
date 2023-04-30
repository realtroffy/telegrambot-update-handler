package telegrambot.executor;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;
import telegrambot.handler.MessageHandler;
import telegrambot.handler.impl.DefaultMessageHandler;
import telegrambot.keybord.ReplyKeyboardMaker;

@Service
@Data
public class TelegramMessageExecutor {

  private final DefaultMessageHandler defaultMessageHandler;
  private final TelegramBot telegramBot;
  private final ReplyKeyboardMaker replyKeyboardMaker;
  private final MessageHandlerHelper messageHandlerHelper;

  public void executeMessage(Message message) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId());
    sendMessage.disableWebPagePreview();
    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());

    MessageHandler handler =
        messageHandlerHelper.getHandlerMap().getOrDefault(message.getText(), defaultMessageHandler);
    handler.execute(message, sendMessage, telegramBot);
  }
}
