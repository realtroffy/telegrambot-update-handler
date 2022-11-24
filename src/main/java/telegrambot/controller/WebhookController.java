package telegrambot.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.executor.MessageExecutor;
import telegrambot.model.UserWriteBot;
import telegrambot.service.WebHookService;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class WebhookController {

  WebHookService webHookService;
  MessageExecutor messageExecutor;

  @PostMapping("/update")
  public void onUpdateReceived(@RequestBody Update update) {
    UserWriteBot userWriteBot = webHookService.getUserFromUpdate(update);
    webHookService.saveToDataBase(userWriteBot);
    messageExecutor.executeMessage(update.getMessage());
  }
}
