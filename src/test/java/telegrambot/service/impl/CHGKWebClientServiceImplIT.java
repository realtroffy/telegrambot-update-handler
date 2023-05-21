package telegrambot.service.impl;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import telegrambot.exception.BadRequestException;
import telegrambot.exception.ServerUnavailableException;

import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

class CHGKWebClientServiceImplIT {

  public static MockWebServer mockBackEnd;
  public static final Long TEST_CHAT_ID = 1L;

  private CHGKWebClientServiceImpl chgkWebClientServiceImpl;

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
    chgkWebClientServiceImpl =
        new CHGKWebClientServiceImpl(WebClient.create(mockBackEnd.url("/").toString()));
  }

  @Test
  void getResponseEntityWhenHasNoException() {
    String entityAnswer = "Some string";
    mockBackEnd.enqueue(new MockResponse().setBody(entityAnswer));

    ResponseEntity<String> responseEntity =
        chgkWebClientServiceImpl.getResponseEntity(EMPTY, TEST_CHAT_ID);

    assertEquals("Some string", responseEntity.getBody());
  }

  @Test
  void throwBadRequestExceptionWhenStatusCode4xx() {
    mockBackEnd.enqueue(new MockResponse().setResponseCode(BAD_REQUEST.value()));

    assertThrows(
        BadRequestException.class,
        () -> chgkWebClientServiceImpl.getResponseEntity(EMPTY, TEST_CHAT_ID));
  }

  @Test
  void throwServerUnavailableExceptionWhenStatusCode5xx() {

    mockBackEnd.enqueue(new MockResponse().setResponseCode(INTERNAL_SERVER_ERROR.value()));

    assertThrows(
        ServerUnavailableException.class,
        () -> chgkWebClientServiceImpl.getResponseEntity(EMPTY, TEST_CHAT_ID));
  }
}
