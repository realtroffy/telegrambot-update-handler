package telegrambot.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import telegrambot.service.DocumentHtmlParserService;

import java.io.IOException;

@Service
public class DocumentHtmlParserServiceImpl implements DocumentHtmlParserService {

  @Override
  public Document getDocumentFromUrl(String connectionUrl) throws IOException {
      return Jsoup.connect(connectionUrl).get();
  }

  @Override
  public Elements getElementsFromDocument(Document document, String cssQuery) {
    return document.select(cssQuery);
  }
}
