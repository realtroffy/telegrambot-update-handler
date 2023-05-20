package telegrambot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import telegrambot.exception.BadRequestException;
import telegrambot.exception.ServerUnavailableException;
import telegrambot.model.weather.Weather;

import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherClientServiceImplIT {

  public static MockWebServer mockBackEnd;

  private WeatherClientServiceImpl weatherClientService;

  @BeforeAll
  public static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  public static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @BeforeEach
  public void initialize() {
    weatherClientService = new WeatherClientServiceImpl(EMPTY, mockBackEnd.url("/").toString());
  }

  @Test
  void getResponseEntityTest_Ok() throws InterruptedException, JsonProcessingException {
    Weather entityAnswer = new Weather();
    entityAnswer.setNowDt("test");
    mockBackEnd.enqueue(
        new MockResponse()
            .setBody(new ObjectMapper().writeValueAsString(entityAnswer))
            .setHeader("Content-Type", "application/json")
            .setResponseCode(200));

    ResponseEntity<Weather> responseEntity = weatherClientService.getResponseEntity("/", 1L);

    RecordedRequest recordedRequest = mockBackEnd.takeRequest();
    assertEquals("/", recordedRequest.getPath());
    assertEquals("GET", recordedRequest.getMethod());
    assertEquals(entityAnswer, responseEntity.getBody());
  }

  @Test
  void throwBadRequestExceptionWhenStatusCode4xx() {
    mockBackEnd.enqueue(new MockResponse().setResponseCode(400));

    assertThrows(
        BadRequestException.class, () -> weatherClientService.getResponseEntity(EMPTY, 1L));
  }

  @Test
  void throwServerUnavailableExceptionWhenStatusCode5xx() {
    mockBackEnd.enqueue(new MockResponse().setResponseCode(500));

    assertThrows(
        ServerUnavailableException.class, () -> weatherClientService.getResponseEntity(EMPTY, 1L));
  }
}
