package telegrambot.exception;

public class BadRequestException extends GeneralAppException {

  public BadRequestException(String messageException, Long chatId) {
    super(messageException, chatId);
  }
}
