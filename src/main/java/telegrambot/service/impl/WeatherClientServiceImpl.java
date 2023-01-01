package telegrambot.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import telegrambot.model.weather.Weather;
import telegrambot.service.WebClientService;

import java.time.Duration;

@Service
public class WeatherClientServiceImpl implements WebClientService<Weather> {

    @Value("${YANDEX_WEATHER_TOKEN}")
    private String yandexWeatherToken;
    private final WebClient webClient;

    public WeatherClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ResponseEntity<Weather> getResponseEntity(String url) {
        return webClient
                .get()
                .uri(url)
                .header("X-Yandex-API-Key", yandexWeatherToken)
                .retrieve()
                .toEntity(Weather.class)
                .timeout(Duration.ofMinutes(1))
                .block();
    }
}
