package telegrambot.executor;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageExecutor {

  void executeMessage(Message message);
}
