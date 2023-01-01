package telegrambot.parser.json;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import telegrambot.model.weather.Weather;
import telegrambot.service.WebClientService;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherJSONParser {

  private final WebClientService<Weather> webClientService;

  public String getWeatherResponse(String url) {
    ResponseEntity<Weather> weatherResponseEntity = webClientService.getResponseEntity(url);
    Weather weather = weatherResponseEntity.getBody();

    translateCondition(weather);
    translateParts(weather);

    return "Температура сейчас: "
        + weather.getFact().getTemp()
        + "\nОщущается как: "
        + weather.getFact().getFeelsLike()
        + "\n"
        + weather.getFact().getCondition()
        + "\n\nТемпература "
        + weather.getForecast().getParts().get(0).getPartName()
        + ": "
        + weather.getForecast().getParts().get(0).getTempAvg()
        + "\n"
        + weather.getForecast().getParts().get(0).getCondition()
        + "\n\nТемпература "
        + weather.getForecast().getParts().get(1).getPartName()
        + ": "
        + weather.getForecast().getParts().get(1).getTempAvg()
        + "\n"
        + weather.getForecast().getParts().get(1).getCondition();
  }

  private void translateParts(Weather weather) {
    for (int i = 0; i < weather.getForecast().getParts().size(); i++) {

      switch (weather.getForecast().getParts().get(i).getPartName()) {
        case "night":
          weather.getForecast().getParts().get(i).setPartName("ночью");
          break;
        case "morning":
          weather.getForecast().getParts().get(i).setPartName("утром");
          break;
        case "day":
          weather.getForecast().getParts().get(i).setPartName("днем");
          break;
        case "evening":
          weather.getForecast().getParts().get(i).setPartName("вечером");
          break;
        default:
          weather.getForecast().getParts().get(i).setPartName("не установлено");
      }
    }
  }

  private void translateCondition(Weather weather) {
    for (int i = 0; i < weather.getForecast().getParts().size(); i++) {
      switch (weather.getForecast().getParts().get(i).getCondition()) {
        case "clear":
          weather.getForecast().getParts().get(i).setCondition("ясно");
          break;
        case "partly-cloudy":
          weather.getForecast().getParts().get(i).setCondition("малооблачно");
          break;
        case "cloudy":
          weather.getForecast().getParts().get(i).setCondition("облачно с прояснениями");
          break;
        case "overcast":
          weather.getForecast().getParts().get(i).setCondition("пасмурно");
          break;
        case "drizzle":
          weather.getForecast().getParts().get(i).setCondition("морось");
          break;
        case "light-rain":
          weather.getForecast().getParts().get(i).setCondition("небольшой дождь");
          break;
        case "rain":
          weather.getForecast().getParts().get(i).setCondition("дождь");
          break;
        case "moderate-rain":
          weather.getForecast().getParts().get(i).setCondition("умеренно сильный дождь");
          break;
        case "heavy-rain":
          weather.getForecast().getParts().get(i).setCondition("сильный дождь");
          break;
        case "continuous-heavy-rain":
          weather.getForecast().getParts().get(i).setCondition("длительный сильный дождь");
          break;
        case "showers":
          weather.getForecast().getParts().get(i).setCondition("ливень");
          break;
        case "wet-snow":
          weather.getForecast().getParts().get(i).setCondition("дождь со снегом");
          break;
        case "light-snow":
          weather.getForecast().getParts().get(i).setCondition("небольшой снег");
          break;
        case "snow":
          weather.getForecast().getParts().get(i).setCondition("снег");
          break;
        case "snow-showers":
          weather.getForecast().getParts().get(i).setCondition("снегопад");
          break;
        case "hail":
          weather.getForecast().getParts().get(i).setCondition("град");
          break;
        case "thunderstorm":
          weather.getForecast().getParts().get(i).setCondition("гроза");
          break;
        case "thunderstorm-with-rain":
          weather.getForecast().getParts().get(i).setCondition("дождь с грозой");
          break;
        case "thunderstorm-with-hail":
          weather.getForecast().getParts().get(i).setCondition("гроза с градом");
          break;
        default:
          weather.getForecast().getParts().get(i).setCondition("не установлено");
      }
    }
    switch (weather.getFact().getCondition()) {
      case "clear":
        weather.getFact().setCondition("ясно");
        break;
      case "partly-cloudy":
        weather.getFact().setCondition("малооблачно");
        break;
      case "cloudy":
        weather.getFact().setCondition("облачно с прояснениями");
        break;
      case "overcast":
        weather.getFact().setCondition("пасмурно");
        break;
      case "drizzle":
        weather.getFact().setCondition("морось");
        break;
      case "light-rain":
        weather.getFact().setCondition("небольшой дождь");
        break;
      case "rain":
        weather.getFact().setCondition("дождь");
        break;
      case "moderate-rain":
        weather.getFact().setCondition("умеренно сильный дождь");
        break;
      case "heavy-rain":
        weather.getFact().setCondition("сильный дождь");
        break;
      case "continuous-heavy-rain":
        weather.getFact().setCondition("длительный сильный дождь");
        break;
      case "showers":
        weather.getFact().setCondition("ливень");
        break;
      case "wet-snow":
        weather.getFact().setCondition("дождь со снегом");
        break;
      case "light-snow":
        weather.getFact().setCondition("небольшой снег");
        break;
      case "snow":
        weather.getFact().setCondition("снег");
        break;
      case "snow-showers":
        weather.getFact().setCondition("снегопад");
        break;
      case "hail":
        weather.getFact().setCondition("град");
        break;
      case "thunderstorm":
        weather.getFact().setCondition("гроза");
        break;
      case "thunderstorm-with-rain":
        weather.getFact().setCondition("дождь с грозой");
        break;
      case "thunderstorm-with-hail":
        weather.getFact().setCondition("гроза с градом");
        break;
      default:
        weather.getFact().setCondition("не установлено");
    }
  }
}
