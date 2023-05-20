package telegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"now", "now_dt", "info", "fact", "forecast"})
@Data
public class Weather implements Serializable {

  private static final long serialVersionUID = 8662466785887074659L;

  @JsonProperty("now")
  private Integer now;

  @JsonProperty("now_dt")
  private String nowDt;

  @JsonProperty("info")
  private Info info;

  @JsonProperty("fact")
  private Fact fact;

  @JsonProperty("forecast")
  private Forecast forecast;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
