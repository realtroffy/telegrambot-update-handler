package telegrambot.model.weather;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class Part {

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
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("part_name")
  public String getPartName() {
    return partName;
  }

  @JsonProperty("part_name")
  public void setPartName(String partName) {
    this.partName = partName;
  }

  @JsonProperty("temp_min")
  public Integer getTempMin() {
    return tempMin;
  }

  @JsonProperty("temp_min")
  public void setTempMin(Integer tempMin) {
    this.tempMin = tempMin;
  }

  @JsonProperty("temp_avg")
  public Integer getTempAvg() {
    return tempAvg;
  }

  @JsonProperty("temp_avg")
  public void setTempAvg(Integer tempAvg) {
    this.tempAvg = tempAvg;
  }

  @JsonProperty("temp_max")
  public Integer getTempMax() {
    return tempMax;
  }

  @JsonProperty("temp_max")
  public void setTempMax(Integer tempMax) {
    this.tempMax = tempMax;
  }

  @JsonProperty("wind_speed")
  public Integer getWindSpeed() {
    return windSpeed;
  }

  @JsonProperty("wind_speed")
  public void setWindSpeed(Integer windSpeed) {
    this.windSpeed = windSpeed;
  }

  @JsonProperty("wind_gust")
  public Double getWindGust() {
    return windGust;
  }

  @JsonProperty("wind_gust")
  public void setWindGust(Double windGust) {
    this.windGust = windGust;
  }

  @JsonProperty("wind_dir")
  public String getWindDir() {
    return windDir;
  }

  @JsonProperty("wind_dir")
  public void setWindDir(String windDir) {
    this.windDir = windDir;
  }

  @JsonProperty("pressure_mm")
  public Integer getPressureMm() {
    return pressureMm;
  }

  @JsonProperty("pressure_mm")
  public void setPressureMm(Integer pressureMm) {
    this.pressureMm = pressureMm;
  }

  @JsonProperty("pressure_pa")
  public Integer getPressurePa() {
    return pressurePa;
  }

  @JsonProperty("pressure_pa")
  public void setPressurePa(Integer pressurePa) {
    this.pressurePa = pressurePa;
  }

  @JsonProperty("humidity")
  public Integer getHumidity() {
    return humidity;
  }

  @JsonProperty("humidity")
  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  @JsonProperty("prec_mm")
  public Integer getPrecMm() {
    return precMm;
  }

  @JsonProperty("prec_mm")
  public void setPrecMm(Integer precMm) {
    this.precMm = precMm;
  }

  @JsonProperty("prec_prob")
  public Integer getPrecProb() {
    return precProb;
  }

  @JsonProperty("prec_prob")
  public void setPrecProb(Integer precProb) {
    this.precProb = precProb;
  }

  @JsonProperty("prec_period")
  public Integer getPrecPeriod() {
    return precPeriod;
  }

  @JsonProperty("prec_period")
  public void setPrecPeriod(Integer precPeriod) {
    this.precPeriod = precPeriod;
  }

  @JsonProperty("icon")
  public String getIcon() {
    return icon;
  }

  @JsonProperty("icon")
  public void setIcon(String icon) {
    this.icon = icon;
  }

  @JsonProperty("condition")
  public String getCondition() {
    return condition;
  }

  @JsonProperty("condition")
  public void setCondition(String condition) {
    this.condition = condition;
  }

  @JsonProperty("feels_like")
  public Integer getFeelsLike() {
    return feelsLike;
  }

  @JsonProperty("feels_like")
  public void setFeelsLike(Integer feelsLike) {
    this.feelsLike = feelsLike;
  }

  @JsonProperty("daytime")
  public String getDaytime() {
    return daytime;
  }

  @JsonProperty("daytime")
  public void setDaytime(String daytime) {
    this.daytime = daytime;
  }

  @JsonProperty("polar")
  public Boolean getPolar() {
    return polar;
  }

  @JsonProperty("polar")
  public void setPolar(Boolean polar) {
    this.polar = polar;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}