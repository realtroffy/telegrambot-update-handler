package telegrambot.service;

import org.springframework.http.ResponseEntity;

public interface WebClientService <T>{

  ResponseEntity<T> getResponseEntity(String url, Long chatId);
}
