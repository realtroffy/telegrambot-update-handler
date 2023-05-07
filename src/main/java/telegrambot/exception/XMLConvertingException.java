package telegrambot.exception;

public class XMLConvertingException extends GeneralAppException {

  public XMLConvertingException(String messageException, Long chatId) {
    super(messageException, chatId);
  }
}
