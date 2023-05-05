package telegrambot.exception.handler;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.exception.BadRequestException;
import telegrambot.exception.GeneralAppException;
import telegrambot.exception.GetBodyFromStringXmlException;
import telegrambot.exception.ServerUnavailableException;

@ControllerAdvice
@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GlobalExceptionHandler {

  TelegramBot telegramBot;

  @ExceptionHandler({
    ServerUnavailableException.class,
    BadRequestException.class,
    GetBodyFromStringXmlException.class
  })
  public void handleServerNotResponseException(GeneralAppException ex) throws TelegramApiException {
    SendMessage exceptionMessage = new SendMessage();
    exceptionMessage.setChatId(ex.getChatId());
    exceptionMessage.setText(ex.getMessageException());
    telegramBot.execute(exceptionMessage);
  }
}
