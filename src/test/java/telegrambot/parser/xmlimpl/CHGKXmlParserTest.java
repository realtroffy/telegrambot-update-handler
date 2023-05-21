package telegrambot.parser.xmlimpl;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import telegrambot.converter.CHGKConverter;
import telegrambot.exception.GetBodyFromStringXmlException;
import telegrambot.exception.ResponseWebClientNullException;
import telegrambot.service.WebClientService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CHGKXmlParserTest {

  public static final long TEST_CHAT_ID = 1L;

  @Mock private WebClientService<String> webClientService;
  @Mock private CHGKConverter converter;
  private CHGKXmlParser chgkXmlParser;

  private static ResponseEntity<String> nullResponse;
  private static ResponseEntity<String> bodyWithNewLine;

  @BeforeAll
  static void setUp() {
    nullResponse = new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    bodyWithNewLine = new ResponseEntity<>(" ", HttpStatus.ACCEPTED);
  }

  @BeforeEach
  void initMocks() {
    chgkXmlParser = new CHGKXmlParser(Strings.EMPTY, webClientService, converter);
  }

  @Test
  void processQuestionButtonWhenBodyIsNullTest() {
    when(webClientService.getResponseEntity(anyString(), anyLong())).thenReturn(nullResponse);

    assertThrows(
        GetBodyFromStringXmlException.class,
        () -> chgkXmlParser.processQuestionButton(TEST_CHAT_ID));

    verifyNoMoreInteractions(webClientService);
  }

  @Test
  void processQuestionButtonWhenGetResponseEntityIsNullTest() {
    when(webClientService.getResponseEntity(anyString(), anyLong())).thenReturn(null);

    assertThrows(
        ResponseWebClientNullException.class,
        () -> chgkXmlParser.processQuestionButton(TEST_CHAT_ID));

    verifyNoMoreInteractions(webClientService);
  }

  @Test
  void processQuestionButtonWhenBodyHasNewLine() {
    Map<String, Object> expected = new HashMap<>();
    when(webClientService.getResponseEntity(anyString(), anyLong())).thenReturn(bodyWithNewLine);
    when(converter.convertXmlToCHGKObject(anyString(), anyLong())).thenReturn(new HashMap<>());

    Map<String, Object> actual = chgkXmlParser.processQuestionButton(TEST_CHAT_ID);

    assertEquals(expected, actual);
  }
}
