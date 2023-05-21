package telegrambot.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import org.springframework.stereotype.Component;
import telegrambot.converter.extractor.ExtractorPicture;
import telegrambot.exception.XMLConvertingException;
import telegrambot.model.CHGKQuestion;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class CHGKConverter {

  public static final String XML_CONVERTING_EXCEPTION_MESSAGE =
      "Exception while converting xml in CHGKQuestion";
  public static final String KEY_MAP_OF_PICTURES = "pictureUrls";

  private final ExtractorPicture extractorPicture;

  public Map<String, Object> convertXmlToCHGKObject(String withoutNewLine, Long chatId) {
    Map<String, Object> questionInfo = new HashMap<>();
    CHGKQuestion chgkQuestion;

    try {
      chgkQuestion = convertStringXmlToCHGKQuestion(withoutNewLine);
    } catch (JsonProcessingException e) {
      throw new XMLConvertingException(XML_CONVERTING_EXCEPTION_MESSAGE, chatId);
    }

    questionInfo.put(
        KEY_MAP_OF_PICTURES, extractorPicture.getPictureUrlIfPresent(chgkQuestion.getQuestion()));

    String completeQuestion =
        chgkQuestion.getQuestion()
            + "\n"
            + "Ответ: "
            + "<tg-spoiler>"
            + "<strong>"
            + chgkQuestion.getAnswer()
            + "</strong>"
            + "\n"
            + "<em>"
            + chgkQuestion.getComment()
            + "</em>"
            + "</tg-spoiler>";

    questionInfo.put("completeQuestion", completeQuestion);

    return questionInfo;
  }

  private CHGKQuestion convertStringXmlToCHGKQuestion(String responseWithoutNewLine)
      throws JsonProcessingException {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
    return xmlMapper.readValue(responseWithoutNewLine, CHGKQuestion.class);
  }
}
