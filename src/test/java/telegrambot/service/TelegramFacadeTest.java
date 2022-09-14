package telegrambot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegrambot.handlers.MessageHandler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelegramFacadeTest {

  @Mock private MessageHandler messageHandler;
  @Mock private Update update;
  @Mock private Message message;

  @InjectMocks private TelegramFacade telegramFacade;

  @Test
  void handleUpdateTest() {
    when(update.getMessage()).thenReturn(message);
    when(messageHandler.process(message)).thenReturn(message);

    telegramFacade.handleUpdate(update);

    verify(update).getMessage();
    verify(messageHandler).process(message);
  }
}
