package telegrambot.executor.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;
import telegrambot.executor.MessageExecutor;
import telegrambot.executor.utils.ExecutorCHGKMessageUtil;
import telegrambot.executor.utils.ExecutorDefaultMessageUtil;
import telegrambot.executor.utils.ExecutorKfcMessageUtil;
import telegrambot.executor.utils.ExecutorMinskMogilevMessageUtil;
import telegrambot.executor.utils.ExecutorMogilevMinskMessageUtil;
import telegrambot.executor.utils.ExecutorStartMessageUtil;
import telegrambot.keybord.ReplyKeyboardMaker;
import telegrambot.parser.htmlimpl.GoCarHtmlParser;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class MessageExecutorImpl implements MessageExecutor {

  public static final String MINSK_MOGILEV_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98";
  public static final String MOGILEV_MINSK_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98";
  public static final String QUESTION_BUTTON_MESSAGE = "❓Гробы_чгк⚰️. Нужно подождать 3-5 секунд";
  public static final String KFC_BUTTON_MESSAGE = "KFC купоны";

  ReplyKeyboardMaker replyKeyboardMaker;
  GoCarHtmlParser goCarHtmlParser;
  CHGKXmlParser chgkXmlParser;
  KFCHtmlParser kfcHtmlParser;
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
        ExecutorCHGKMessageUtil.executeCHGKMessage(chatId, sendMessage, chgkXmlParser, telegramBot);
        break;
      case KFC_BUTTON_MESSAGE:
        ExecutorKfcMessageUtil.executeKfcMessage(chatId, kfcHtmlParser, telegramBot);
        break;
      default:
        ExecutorDefaultMessageUtil.executeDefaultMessage(sendMessage, telegramBot);
    }
  }
}
