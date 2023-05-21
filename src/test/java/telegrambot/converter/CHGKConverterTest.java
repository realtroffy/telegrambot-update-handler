package telegrambot.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import telegrambot.converter.extractor.ExtractorPicture;
import telegrambot.exception.XMLConvertingException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CHGKConverterTest {

  public static final String WITHOUT_NEW_LINE =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <search> <question><tourFileName>smcat00.2</tourFileName> <tournamentFileName>smcat00.txt</tournamentFileName> <QuestionId>894416</QuestionId> <ParentId>53043</ParentId> <Number>13</Number> <Type>Ч</Type> <TypeNum>1</TypeNum> <TextId>smcat00.2-13</TextId> <Question>Он бы был сыном Нефтиды, братом Баты и отцом Кебхуты. В руках этого шакала была жизнь. На западе он был первым, но &quot;правил&quot; он везде. Никто не мог его увидеть, но видели все. Назовите хотя бы одно, из его известных имён.</Question> <Answer>Это Бог - покровитель всех умерших, хранитель лекарств и ядов. Известен под именами: Анубис (греч.) и Инпу (егип.). Достаточно было вспомнить, кто изображался в виде шакала.</Answer> <PassCriteria/> <Authors>Artiom V. Vasiliev</Authors> <Sources/> <Comments/> <Rating/> <RatingNumber/> <Complexity/> <Topic/> <ProcessedBySearch/> <parent_text_id>smcat00.2</parent_text_id> <ParentTextId>smcat00.2</ParentTextId> <tourId>53043</tourId> <tournamentId>3404</tournamentId> <tourTitle>Февраль 2000(462-493)</tourTitle> <tournamentTitle>Вопросы Ученого Кота, 2000 г.</tournamentTitle> <tourType>Т</tourType> <tournamentType>Ч</tournamentType> <tourPlayedAt>2000-02-01</tourPlayedAt> <tournamentPlayedAt>2000-01-01</tournamentPlayedAt> <tourPlayedAt2>2000-02-01</tourPlayedAt2> <tournamentPlayedAt2>2000-01-01</tournamentPlayedAt2> <Notices> </Notices> </question> </search>";
  public static final String EXPECTED_STRING_IN_MAP =
      "Он бы был сыном Нефтиды, братом Баты и отцом Кебхуты. В руках этого шакала была жизнь. На западе он был первым, но \"правил\" он везде. Никто не мог его увидеть, но видели все. Назовите хотя бы одно, из его известных имён.\n"
          + "Ответ: <tg-spoiler><strong>Это Бог - покровитель всех умерших, хранитель лекарств и ядов. Известен под именами: Анубис (греч.) и Инпу (егип.). Достаточно было вспомнить, кто изображался в виде шакала.</strong>\n"
          + "<em></em></tg-spoiler>";
  public static final String KEY_QUESTION_IN_MAP = "completeQuestion";
  public static final long TEST_CHAT_ID = 1L;

  @Mock ExtractorPicture extractorPicture;

  @InjectMocks CHGKConverter chgkConverter;

  @Test
  void throwXMLConvertingExceptionWhenBodyInconvertible() {
    String inconvertible = "1234";

    assertThrows(
        XMLConvertingException.class,
        () -> chgkConverter.convertXmlToCHGKObject(inconvertible, TEST_CHAT_ID));
  }

  @Test
  void convertXmlToCHGKObjectWhenBodyIsConvertible() {
    Map<String, Object> expectedMap = new HashMap<>();
    expectedMap.put(KEY_QUESTION_IN_MAP, EXPECTED_STRING_IN_MAP);

    when(extractorPicture.getPictureUrlIfPresent(anyString())).thenReturn(null);

    Map<String, Object> actualMap = chgkConverter.convertXmlToCHGKObject(WITHOUT_NEW_LINE, TEST_CHAT_ID);

    assertEquals(expectedMap.get(KEY_QUESTION_IN_MAP), actualMap.get(KEY_QUESTION_IN_MAP));
  }
}
