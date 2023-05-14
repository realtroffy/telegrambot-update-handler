package telegrambot.service.impl;

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

    emailService.sendEmail("", "", "");

    Mockito.verify(javaMailSender).send(Mockito.any(SimpleMailMessage.class));
  }
}
