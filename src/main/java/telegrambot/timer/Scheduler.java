package telegrambot.timer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import telegrambot.parser.xmlimpl.CHGKXmlParser;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class Scheduler {

  public static final Queue<Map<String, Object>> chgkQueue = new ArrayDeque<>();
  private final CHGKXmlParser parser;

  @Scheduled(cron = "@daily")
  public void addCHGKQuestionToCashQueue() throws JsonProcessingException {
    if (chgkQueue.size() < 30) {
      chgkQueue.add(parser.processQuestionButton());
    }
  }
}
