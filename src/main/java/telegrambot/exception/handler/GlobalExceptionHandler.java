package telegrambot.exception.handler;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;
import telegrambot.exception.BadRequestException;
import telegrambot.exception.GeneralAppException;
import telegrambot.exception.GetBodyFromStringXmlException;
import telegrambot.exception.ResponseWebClientNullException;
import telegrambot.exception.ServerUnavailableException;
import telegrambot.exception.XMLConvertingException;
import telegrambot.service.EmailService;

import java.time.LocalDateTime;

import static java.time.ZoneId.of;

@ControllerAdvice
@Data
public class GlobalExceptionHandler {

  private static final String EMAIL_SUBJECT = "NullPointer was occurred";
  private static final String EMAIL_TEXT =
      "NullPointer was occurred in your telegram bot application";

  @Value("${email.receiver}")
  private String emailReceiver;

  private final TelegramBot telegramBot;
  private final EmailService emailService;

  @ExceptionHandler({
    ServerUnavailableException.class,
    BadRequestException.class,
    GetBodyFromStringXmlException.class,
    XMLConvertingException.class,
    ResponseWebClientNullException.class
  })
  public void handleServerNotResponseException(GeneralAppException ex) throws TelegramApiException {
    SendMessage exceptionMessage = new SendMessage();
    exceptionMessage.setChatId(ex.getChatId());
    exceptionMessage.setText(ex.getMessageException());
    telegramBot.execute(exceptionMessage);
  }

  @ExceptionHandler(NullPointerException.class)
  public void handleNullPointerException(NullPointerException ex) {
    StackTraceElement[] stackTraceElements = ex.getStackTrace();
    String methodNullPointer = null;
    if (stackTraceElements.length > 0) {
      methodNullPointer = stackTraceElements[0].getMethodName();
    }
    emailService.sendEmail(
        emailReceiver,
        EMAIL_SUBJECT,
        EMAIL_TEXT
            + "on time: "
            + LocalDateTime.now(of("Europe/Minsk"))
            + " in method: "
            + methodNullPointer);
  }
}
