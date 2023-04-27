package telegrambot.executor.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;
import telegrambot.executor.MessageExecutor;
import telegrambot.executor.utils.ExecutorBurgerMessage;
import telegrambot.executor.utils.ExecutorCHGKMessage;
import telegrambot.executor.utils.ExecutorDefaultMessageUtil;
import telegrambot.executor.utils.ExecutorKfcMessage;
import telegrambot.executor.utils.ExecutorMinskMogilevMessageUtil;
import telegrambot.executor.utils.ExecutorMogilevMinskMessageUtil;
import telegrambot.executor.utils.ExecutorStartMessageUtil;
import telegrambot.executor.utils.ExecutorTextButtonNull;
import telegrambot.executor.utils.ExecutorWeatherMinskMessage;
import telegrambot.executor.utils.ExecutorWeatherMogilevMessage;
import telegrambot.keybord.ReplyKeyboardMaker;
import telegrambot.parser.htmlimpl.GoCarHtmlParser;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageExecutorImpl implements MessageExecutor {

  public static final String MINSK_MOGILEV_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98";
  public static final String MOGILEV_MINSK_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98";
  public static final String QUESTION_BUTTON_MESSAGE = "Вопросы Что,где когда?";
  public static final String KFC_BUTTON_MESSAGE = "KFC купоны";
  public static final String BURGER_KING_BUTTON_MESSAGE = "Burger-King купоны";
  public static final String WEATHER_MINSK_BUTTON_MESSAGE = "Минск погода";
  public static final String WEATHER_MOGILEV_BUTTON_MESSAGE = "Могилев погода";

  ReplyKeyboardMaker replyKeyboardMaker;
  GoCarHtmlParser goCarHtmlParser;
  ExecutorCHGKMessage executorCHGKMessage;
  ExecutorKfcMessage executorKfcMessage;
  ExecutorBurgerMessage executorBurgerMessage;
  ExecutorWeatherMinskMessage executorWeatherMinskMessage;
  ExecutorWeatherMogilevMessage executorWeatherMogilevMessage;
  TelegramBot telegramBot;

  @Override
  public void executeMessage(Message message) {

    Long chatId = message.getChatId();
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);

    String inputMessage = message.getText();
    sendMessage.disableWebPagePreview();
    sendMessage.enableHtml(true);
    sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());

    if (inputMessage == null) {
      ExecutorTextButtonNull.executeTextButtonNull(sendMessage, telegramBot);
    } else {
      switch (inputMessage) {
        case "/start":
          ExecutorStartMessageUtil.executeStartMessage(message, sendMessage, telegramBot);
          break;
        case MOGILEV_MINSK_BUTTON_MESSAGE:
          ExecutorMogilevMinskMessageUtil.executeMogilevMinskMessage(
              sendMessage, goCarHtmlParser, telegramBot);
          break;
        case MINSK_MOGILEV_BUTTON_MESSAGE:
          ExecutorMinskMogilevMessageUtil.executeMinskMogilevMessage(
              sendMessage, goCarHtmlParser, telegramBot);
          break;
        case QUESTION_BUTTON_MESSAGE:
          executorCHGKMessage.execute(chatId, sendMessage, telegramBot);
          break;
        case KFC_BUTTON_MESSAGE:
          executorKfcMessage.execute(chatId, telegramBot);
          break;
        case BURGER_KING_BUTTON_MESSAGE:
          executorBurgerMessage.execute(sendMessage, chatId, telegramBot);
          break;
        case WEATHER_MINSK_BUTTON_MESSAGE:
          executorWeatherMinskMessage.execute(sendMessage, telegramBot);
          break;
        case WEATHER_MOGILEV_BUTTON_MESSAGE:
          executorWeatherMogilevMessage.execute(sendMessage, telegramBot);
          break;
        default:
          ExecutorDefaultMessageUtil.executeDefaultMessage(sendMessage, telegramBot);
      }
    }
  }
}
