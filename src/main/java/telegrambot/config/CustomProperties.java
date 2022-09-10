package telegrambot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom.telegrambot")
@Getter
@Setter
public class CustomProperties {

  /** The name of telegrambot, which execute message. Yoy can find it in telegram */
  String userName;

  /** The url, which provide random CHGK question in xml format */
  String questionUrl;

  /** Function doesn't work yet. */
  String inlineKeyboard;

  /** The url for telegrambot webhook. */
  String webHookPath;
}
