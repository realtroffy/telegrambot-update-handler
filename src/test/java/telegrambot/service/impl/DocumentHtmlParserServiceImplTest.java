package telegrambot.service.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jsoup.Jsoup.connect;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentHtmlParserServiceImplTest {

  @Mock private Document document;
  @Mock private Connection connection;
  @Mock private Elements elements;

  @InjectMocks private DocumentHtmlParserServiceImpl documentHtmlParserService;

  @Test
  void getDocumentFromUrlTest() throws IOException {
    try (MockedStatic<Jsoup> utilities = mockStatic(Jsoup.class)) {
      utilities.when(() -> connect(anyString())).thenReturn(connection);
      utilities.when(connection::get).thenReturn(document);
      assertThat(connect(anyString()).get()).isEqualTo(document);
    }
  }

  @Test
  void getElementsFromDocumentTest() {
    when(document.select(anyString())).thenReturn(elements);

    documentHtmlParserService.getElementsFromDocument(document, anyString());

    verify(document).select(anyString());
  }
}
