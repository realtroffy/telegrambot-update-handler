package telegrambot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;

public interface MessageHandler {

  void execute(Message message, SendMessage sendMessage, TelegramBot telegramBot);
}
