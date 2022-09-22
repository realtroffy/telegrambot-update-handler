package telegrambot.component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Component
public class TelegramBot extends SpringWebhookBot {

  @Value("${custom.telegrambot.webHookPath}")
  String botPath;

  @Value("${custom.telegrambot.userName}")
  String botUsername;

  @Value("${TELEGRAMBOT_TOKEN}")
  String botToken;

  public TelegramBot(SetWebhook setWebhook) {
    super(setWebhook);
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return null;
  }
}
