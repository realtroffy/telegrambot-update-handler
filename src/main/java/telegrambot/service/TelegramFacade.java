package telegrambot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.handlers.impl.MessageExecutorImpl;

@Service
@AllArgsConstructor
public class TelegramFacade {

  private final MessageExecutorImpl messageExecutorImpl;

  public void handleUpdate(Update update) {
    Message message = update.getMessage();
    messageExecutorImpl.executeMessage(message);
  }
}
