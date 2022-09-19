package telegrambot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.handlers.MessageExecutor;
import telegrambot.model.UserWriteBot;
import telegrambot.service.WebHookService;

@RestController
@AllArgsConstructor
public class WebhookController {

  private final WebHookService webHookService;
  private final MessageExecutor messageExecutor;

  @PostMapping("/update")
  public void onUpdateReceived(@RequestBody Update update) {
    UserWriteBot userWriteBot = webHookService.getUserFromUpdate(update);
    webHookService.saveToDataBase(userWriteBot);
    messageExecutor.executeMessage(update.getMessage());
  }
}
