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
  "obs_time",
  "temp",
  "feels_like",
  "icon",
  "condition",
  "wind_speed",
  "wind_dir",
  "pressure_mm",
  "pressure_pa",
  "humidity",
  "daytime",
  "polar",
  "season",
  "wind_gust"
})
@Data
public class Fact implements Serializable {

  private static final long serialVersionUID = 5317596069133550167L;

  @JsonProperty("obs_time")
  private Integer obsTime;

  @JsonProperty("temp")
  private Integer temp;

  @JsonProperty("feels_like")
  private Integer feelsLike;

  @JsonProperty("icon")
  private String icon;

  @JsonProperty("condition")
  private String condition;

  @JsonProperty("wind_speed")
  private Integer windSpeed;

  @JsonProperty("wind_dir")
  private String windDir;

  @JsonProperty("pressure_mm")
  private Integer pressureMm;

  @JsonProperty("pressure_pa")
  private Integer pressurePa;

  @JsonProperty("humidity")
  private Integer humidity;

  @JsonProperty("daytime")
  private String daytime;

  @JsonProperty("polar")
  private Boolean polar;

  @JsonProperty("season")
  private String season;

  @JsonProperty("wind_gust")
  private Double windGust;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
