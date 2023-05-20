package telegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "date",
  "date_ts",
  "week",
  "sunrise",
  "sunset",
  "moon_code",
  "moon_text",
  "parts"
})
@Data
public class Forecast implements Serializable {

  private static final long serialVersionUID = -1938473937554563210L;

  @JsonProperty("date")
  private String date;

  @JsonProperty("date_ts")
  private Integer dateTs;

  @JsonProperty("week")
  private Integer week;

  @JsonProperty("sunrise")
  private String sunrise;

  @JsonProperty("sunset")
  private String sunset;

  @JsonProperty("moon_code")
  private Integer moonCode;

  @JsonProperty("moon_text")
  private String moonText;

  @JsonProperty("parts")
  private List<Part> parts = null;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
