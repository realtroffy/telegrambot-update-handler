package telegrambot.handlers.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegrambot.component.TelegramBot;
import telegrambot.handlers.MessageExecutor;
import telegrambot.handlers.utils.ExecutorCHGKMessageUtil;
import telegrambot.handlers.utils.ExecutorDefaultMessageUtil;
import telegrambot.handlers.utils.ExecutorKfcMessageUtil;
import telegrambot.handlers.utils.ExecutorMinskMogilevMessageUtil;
import telegrambot.handlers.utils.ExecutorMogilevMinskMessageUtil;
import telegrambot.handlers.utils.ExecutorStartMessageUtil;
import telegrambot.keybord.ReplyKeyboardMaker;
import telegrambot.parser.htmlimpl.GoCarHtmlParser;
import telegrambot.parser.htmlimpl.KFCHtmlParser;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

@Service
@AllArgsConstructor
@Slf4j
public class MessageExecutorImpl implements MessageExecutor {

  public static final String MINSK_MOGILEV_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Минск_Могилев\uD83D\uDE98";
  public static final String MOGILEV_MINSK_BUTTON_MESSAGE =
      "\uD83D\uDE98Попутка_Могилев_Минск\uD83D\uDE98";
  public static final String QUESTION_BUTTON_MESSAGE = "❓Гробы_чгк⚰️. Нужно подождать 3-5 секунд";
  public static final String KFC_BUTTON_MESSAGE = "KFC купоны";

  private final ReplyKeyboardMaker replyKeyboardMaker;
  private final GoCarHtmlParser goCarHtmlParser;
  private final CHGKXmlParser chgkXmlParser;
  private final KFCHtmlParser kfcHtmlParser;
  private final TelegramBot telegramBot;

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
