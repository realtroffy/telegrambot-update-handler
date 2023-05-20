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
@JsonPropertyOrder({
  "part_name",
  "temp_min",
  "temp_avg",
  "temp_max",
  "wind_speed",
  "wind_gust",
  "wind_dir",
  "pressure_mm",
  "pressure_pa",
  "humidity",
  "prec_mm",
  "prec_prob",
  "prec_period",
  "icon",
  "condition",
  "feels_like",
  "daytime",
  "polar"
})
@Data
public class Part implements Serializable {

  private static final long serialVersionUID = 3454662520886456996L;

  @JsonProperty("part_name")
  private String partName;

  @JsonProperty("temp_min")
  private Integer tempMin;

  @JsonProperty("temp_avg")
  private Integer tempAvg;

  @JsonProperty("temp_max")
  private Integer tempMax;

  @JsonProperty("wind_speed")
  private Integer windSpeed;

  @JsonProperty("wind_gust")
  private Double windGust;

  @JsonProperty("wind_dir")
  private String windDir;

  @JsonProperty("pressure_mm")
  private Integer pressureMm;

  @JsonProperty("pressure_pa")
  private Integer pressurePa;

  @JsonProperty("humidity")
  private Integer humidity;

  @JsonProperty("prec_mm")
  private Integer precMm;

  @JsonProperty("prec_prob")
  private Integer precProb;

  @JsonProperty("prec_period")
  private Integer precPeriod;

  @JsonProperty("icon")
  private String icon;

  @JsonProperty("condition")
  private String condition;

  @JsonProperty("feels_like")
  private Integer feelsLike;

  @JsonProperty("daytime")
  private String daytime;

  @JsonProperty("polar")
  private Boolean polar;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
