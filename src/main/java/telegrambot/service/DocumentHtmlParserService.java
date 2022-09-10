package telegrambot.service;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public interface DocumentHtmlParserService {

  Document getDocumentFromUrl(String connectionUrl) throws IOException;

  Elements getElementsFromDocument(Document document, String cssQuery);
}
