package telegrambot.converter.extractor;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtractorPictureTest {

  public static final String PICTURE_URL = "https://db.chgk.info/images/db/";
  public static final String EXPECTED_NAME_PICTURE = "121.jpg";
  public static final String STRING_FOR_FIND_PICTURE_BY_PATTERN = "abc121.jpg1234";

  @Test
  void getPictureUrlIfPresent() {
    ExtractorPicture extractorPicture = new ExtractorPicture();
    List<String> expected = new ArrayList<>();
    expected.add(PICTURE_URL + EXPECTED_NAME_PICTURE);

    List<String> actual =
        extractorPicture.getPictureUrlIfPresent(STRING_FOR_FIND_PICTURE_BY_PATTERN);

    assertEquals(expected, actual);
  }
}
