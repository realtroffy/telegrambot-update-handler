package telegrambot.exception;

public class ServerUnavailableException extends GeneralAppException {

  public ServerUnavailableException(String messageException, Long chatId) {
    super(messageException, chatId);
  }
}
