package telegrambot.exception;

public class ResponseWebClientNullException extends GeneralAppException {

  public ResponseWebClientNullException(String messageException, Long chatId) {
    super(messageException, chatId);
  }
}
