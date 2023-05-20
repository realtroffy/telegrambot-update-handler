package telegrambot.model.weather;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

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

    private static final long serialVersionUID = 644261103345605128L;

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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /*@JsonProperty("obs_time")
    public Integer getObsTime() {
        return obsTime;
    }

    @JsonProperty("obs_time")
    public void setObsTime(Integer obsTime) {
        this.obsTime = obsTime;
    }

    @JsonProperty("temp")
    public Integer getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @JsonProperty("feels_like")
    public Integer getFeelsLike() {
        return feelsLike;
    }

    @JsonProperty("feels_like")
    public void setFeelsLike(Integer feelsLike) {
        this.feelsLike = feelsLike;
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

    @JsonProperty("wind_speed")
    public Integer getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("wind_speed")
    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
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

    @JsonProperty("season")
    public String getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(String season) {
        this.season = season;
    }

    @JsonProperty("wind_gust")
    public Double getWindGust() {
        return windGust;
    }

    @JsonProperty("wind_gust")
    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

}