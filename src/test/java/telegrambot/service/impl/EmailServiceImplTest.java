package telegrambot.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

  @Mock private JavaMailSender javaMailSender;

  @InjectMocks private EmailServiceImpl emailService;

  @Test
  void sendMailTest() {
    doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

    emailService.sendEmail(EMPTY, EMPTY, EMPTY);

    verify(javaMailSender).send(any(SimpleMailMessage.class));
  }
}
