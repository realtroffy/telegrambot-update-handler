package telegrambot.parser.htmlimpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import telegrambot.service.DocumentHtmlParserService;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoCarHtmlParserTest {

  @Mock private DocumentHtmlParserService documentHtmlParserService;

  @InjectMocks private GoCarHtmlParser goCarHtmlParser;

  @Test
  void getMessageFromDocumentOkTest() throws IOException {
    String expected =
        "<strong>Цена:</strong> <ins></ins>, <strong>Водитель:</strong> <ins></ins>, <strong>Дата поездки:</strong> <ins></ins>, <strong>Свободных мест:</strong> <ins></ins>, <strong>Ссылка:</strong> https://www.gocar.by\n";

    when(documentHtmlParserService.getDocumentFromUrl(anyString()))
        .thenReturn(mock(Document.class));

    when(documentHtmlParserService.getElementsFromDocument(any(), anyString()))
        .thenReturn(new Elements(List.of(new Element("1"))));

    when(documentHtmlParserService.getElementsFromDocument(any(), anyString()))
            .thenReturn(new Elements(List.of(new Element("1"))));

    when(documentHtmlParserService.getElementsFromDocument(any(), anyString()))
            .thenReturn(new Elements(List.of(new Element("1"))));

    when(documentHtmlParserService.getElementsFromDocument(any(), anyString()))
            .thenReturn(new Elements(List.of(new Element("1"))));

    when(documentHtmlParserService.getElementsFromDocument(any(), anyString()))
            .thenReturn(new Elements(List.of(new Element("1"))));

    String actual = goCarHtmlParser.getMessageFromDocument(anyString());


    Assertions.assertEquals(actual, expected);
    verify(documentHtmlParserService).getDocumentFromUrl(anyString());
    verify(documentHtmlParserService, times(5)).getElementsFromDocument(any(), anyString());
  }
}
