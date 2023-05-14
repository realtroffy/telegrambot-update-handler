package telegrambot.service.impl;

import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

  @Mock private JavaMailSender javaMailSender;

  @InjectMocks private EmailServiceImpl emailService;

  @Test
  void sendMailTest() {
    Mockito.doNothing().when(javaMailSender).send(Mockito.any(SimpleMailMessage.class));

    emailService.sendEmail(Strings.EMPTY, Strings.EMPTY, Strings.EMPTY);

    Mockito.verify(javaMailSender).send(Mockito.any(SimpleMailMessage.class));
  }
}
