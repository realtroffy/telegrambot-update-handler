package telegrambot.model.weather;

import java.util.HashMap;
import java.util.List;
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
        "date",
        "date_ts",
        "week",
        "sunrise",
        "sunset",
        "moon_code",
        "moon_text",
        "parts"
})
public class Forecast {

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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("date_ts")
    public Integer getDateTs() {
        return dateTs;
    }

    @JsonProperty("date_ts")
    public void setDateTs(Integer dateTs) {
        this.dateTs = dateTs;
    }

    @JsonProperty("week")
    public Integer getWeek() {
        return week;
    }

    @JsonProperty("week")
    public void setWeek(Integer week) {
        this.week = week;
    }

    @JsonProperty("sunrise")
    public String getSunrise() {
        return sunrise;
    }

    @JsonProperty("sunrise")
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    @JsonProperty("sunset")
    public String getSunset() {
        return sunset;
    }

    @JsonProperty("sunset")
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @JsonProperty("moon_code")
    public Integer getMoonCode() {
        return moonCode;
    }

    @JsonProperty("moon_code")
    public void setMoonCode(Integer moonCode) {
        this.moonCode = moonCode;
    }

    @JsonProperty("moon_text")
    public String getMoonText() {
        return moonText;
    }

    @JsonProperty("moon_text")
    public void setMoonText(String moonText) {
        this.moonText = moonText;
    }

    @JsonProperty("parts")
    public List<Part> getParts() {
        return parts;
    }

    @JsonProperty("parts")
    public void setParts(List<Part> parts) {
        this.parts = parts;
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