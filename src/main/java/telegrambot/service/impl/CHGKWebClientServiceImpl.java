package telegrambot.service.impl;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import telegrambot.exception.BadRequestException;
import telegrambot.exception.ServerUnavailableException;
import telegrambot.service.WebClientService;

import java.time.Duration;

import static reactor.core.publisher.Mono.error;

@Service
@Data
public class CHGKWebClientServiceImpl implements WebClientService<String> {

  private final WebClient webClient;

  @Override
  public ResponseEntity<String> getResponseEntity(String url, Long chatId) {
    return webClient
        .get()
        .uri(url)
        .retrieve()
        .onStatus(
            HttpStatus::is4xxClientError,
            error -> error(new BadRequestException("Bad request for CHGK url", chatId)))
        .onStatus(
            HttpStatus::is5xxServerError,
            error -> error(new ServerUnavailableException("Server CHGK is not responding", chatId)))
        .toEntity(String.class)
        .timeout(Duration.ofMinutes(1))
        .block();
  }
}
