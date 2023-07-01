package telegrambot.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import telegrambot.model.UserWriteBot;
import telegrambot.repository.UserVisitRepository;
import telegrambot.service.WebHookService;

import java.time.LocalDateTime;

@SpringBootTest
class WebHookServiceImplIT {

  @Autowired private WebHookService webHookService;
  @MockBean private UserVisitRepository userVisitRepository;
  private UserWriteBot expected;

  @BeforeEach
  public void setUp() {
    expected =
        new UserWriteBot(1L, "firstName", "lastName", LocalDateTime.of(1999, 1, 1, 1, 1), "button");

    Mockito.when(userVisitRepository.save(expected)).thenReturn(expected);
  }

  @Test
  void whenSaveUserWriteBotThenReturnUserWriteBot() {
    UserWriteBot actual = webHookService.saveToDataBase(expected);

    Assertions.assertEquals(actual, expected);
  }
}
