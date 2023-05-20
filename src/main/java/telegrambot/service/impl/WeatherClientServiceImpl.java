package telegrambot.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import telegrambot.exception.BadRequestException;
import telegrambot.exception.ServerUnavailableException;
import telegrambot.model.weather.Weather;
import telegrambot.service.WebClientService;

import java.time.Duration;

import static reactor.core.publisher.Mono.error;

@Service
public class WeatherClientServiceImpl implements WebClientService<Weather> {

  @Value("${YANDEX_WEATHER_TOKEN}")
  private final String yandexWeatherToken;
  private final WebClient webClient;

  public WeatherClientServiceImpl(
      @Value("${YANDEX_WEATHER_TOKEN}") String yandexWeatherToken, WebClient webClient) {
    this.yandexWeatherToken = yandexWeatherToken;
    this.webClient = webClient;
  }

  @Override
  public ResponseEntity<Weather> getResponseEntity(String url, Long chatId) {
    return webClient
        .get()
        .uri(url)
        .header("X-Yandex-API-Key", yandexWeatherToken)
        .retrieve()
        .onStatus(
            HttpStatus::is4xxClientError,
            error -> error(new BadRequestException("Bad request for Weather url", chatId)))
        .onStatus(
            HttpStatus::is5xxServerError,
            error ->
                error(new ServerUnavailableException("Server Weather is not responding", chatId)))
        .toEntity(Weather.class)
        .timeout(Duration.ofMinutes(1))
        .block();
  }
}
