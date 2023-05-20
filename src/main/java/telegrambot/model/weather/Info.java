package telegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"url", "lat", "lon"})
public class Info implements Serializable {

  private static final long serialVersionUID = -3328101123143951756L;

  @JsonProperty("url")
  private String url;

  @JsonProperty("lat")
  private Double lat;

  @JsonProperty("lon")
  private Double lon;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(String url) {
    this.url = url;
  }

  @JsonProperty("lat")
  public Double getLat() {
    return lat;
  }

  @JsonProperty("lat")
  public void setLat(Double lat) {
    this.lat = lat;
  }

  @JsonProperty("lon")
  public Double getLon() {
    return lon;
  }

  @JsonProperty("lon")
  public void setLon(Double lon) {
    this.lon = lon;
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
