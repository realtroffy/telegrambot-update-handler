package telegrambot.parser.htmlimpl;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import telegrambot.service.DocumentHtmlParserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BurgerKingHtmlParser {

  public static final String COUPON_NAME_QUERY = "div.coupons-page__product-img>img[src]";
  public static final String REMOVED_OLD_PRICE_QUERY = "div.coupons-page__product-price-old";
  public static final String ACTUAL_PRICE_QUERY = "div.coupons-page__product-price";
  public static final String COUPON_NUMBER_QUERY = "div.coupons-page__product-number";
  public static final String PRODUCT_LIST_QUERY = "div.coupons-page__product-desc";
  public static final String FIRST_PART_URL_BURGER = "https://burger-king.by";


  private final DocumentHtmlParserService documentHtmlParserService;

  public Map<String, String> getMessageFromDocument(String urlForParse) throws IOException {
    Document document = documentHtmlParserService.getDocumentFromUrl(urlForParse);

    Elements imgElements =
        documentHtmlParserService.getElementsFromDocument(document, COUPON_NAME_QUERY);

    documentHtmlParserService
        .getElementsFromDocument(document, REMOVED_OLD_PRICE_QUERY)
        .remove();

    List<String> priceList =
        documentHtmlParserService
            .getElementsFromDocument(document, ACTUAL_PRICE_QUERY)
            .eachText();

    List<String> productList =
        documentHtmlParserService
            .getElementsFromDocument(document, PRODUCT_LIST_QUERY)
            .eachText();

    List<String> couponNumberList =
        documentHtmlParserService
            .getElementsFromDocument(document, COUPON_NUMBER_QUERY)
            .eachText();

    List<String> resultBurgerInfoList = new ArrayList<>();

    for (int i = 0; i < priceList.size(); i++) {
      resultBurgerInfoList.add(
          productList.get(i) + "\n Цена: " + priceList.get(i) + "\n Номер купона: " + couponNumberList.get(i));
    }

    Map<String, String> burgerMap = new HashMap<>();

    List<String> imgList =
        imgElements.stream()
            .map(e -> e.attr("src"))
            .map(e -> FIRST_PART_URL_BURGER + e)
            .collect(Collectors.toList());

    for (int i = 0; i < imgList.size(); i++) {
      burgerMap.put(imgList.get(i), resultBurgerInfoList.get(i));
    }
    return burgerMap;
  }
}
