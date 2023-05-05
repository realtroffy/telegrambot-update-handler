package telegrambot.exception;

public class GetBodyFromStringXmlException extends GeneralAppException {

  public GetBodyFromStringXmlException(String messageException, Long chatId) {
    super(messageException, chatId);
  }
}
