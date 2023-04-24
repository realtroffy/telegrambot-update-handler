package telegrambot.parser.htmlimpl;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import telegrambot.service.DocumentHtmlParserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KFCHtmlParser {

  public static final String COUPON_NAME_QUERY = "div>img[alt=\"Скидки и акции в KFC!\"]";
  private final DocumentHtmlParserService documentHtmlParserService;

  public List<String> getMessageFromDocument(String urlForParse) throws IOException {
    Document document = documentHtmlParserService.getDocumentFromUrl(urlForParse);

    Elements coupon =
        documentHtmlParserService.getElementsFromDocument(document, COUPON_NAME_QUERY);
    return coupon.stream().map(e -> e.attr("data-original")).collect(Collectors.toList());
  }
}
