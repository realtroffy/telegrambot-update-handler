package telegrambot.executor;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.component.TelegramBot;

@UtilityClass
public class ExecutorSendPhotoUtil {

  public static void executePhoto(Long chatId, String pictureUrl, TelegramBot telegramBot)
      throws TelegramApiException {
    SendPhoto sendPhoto = new SendPhoto();
    sendPhoto.setChatId(chatId);
    sendPhoto.setPhoto(new InputFile(pictureUrl));
    telegramBot.execute(sendPhoto);
  }
}
