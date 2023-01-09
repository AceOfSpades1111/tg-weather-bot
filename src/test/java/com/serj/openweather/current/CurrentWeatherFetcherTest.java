package com.serj.openweather.current;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

// TODO maybe something like contract test with openweatherapi
class CurrentWeatherFetcherTest {

    private CurrentWeatherFetcher weatherFetcher;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void init() {
        RestTemplate restTemplate = new RestTemplate();
        weatherFetcher = new CurrentWeatherFetcher(restTemplate,
                "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s",
                "7b0a92bf3d8a70ebcf466da40444ed89");
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void fetch() {
        mockServer.expect(ExpectedCount.once(),
                        requestTo("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=7b0a92bf3d8a70ebcf466da40444ed89"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(getResponseBody()));

        double latitude = 44.34;
        double longitude = 10.99;

        CurrentWeather currentWeather = weatherFetcher.fetch(longitude, latitude);
        mockServer.verify();

        assertThat(currentWeather.coord()).satisfies(coordinate -> {
            assertEquals(longitude, coordinate.lon());
            assertEquals(latitude, coordinate.lat());
        });
    }

    private static String getResponseBody() {
        return """
                {
                  "coord": {
                    "lon": 10.99,
                    "lat": 44.34
                  },
                  "weather": [
                    {
                      "id": 804,
                      "main": "Clouds",
                      "description": "overcast clouds",
                      "icon": "04d"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 279.54,
                    "feels_like": 278.03,
                    "temp_min": 276.07,
                    "temp_max": 282.64,
                    "pressure": 1017,
                    "humidity": 87,
                    "sea_level": 1017,
                    "grnd_level": 930
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 2.11,
                    "deg": 168,
                    "gust": 5.52
                  },
                  "clouds": {
                    "all": 100
                  },
                  "dt": 1673167259,
                  "sys": {
                    "type": 2,
                    "id": 2004688,
                    "country": "IT",
                    "sunrise": 1673160680,
                    "sunset": 1673193201
                  },
                  "timezone": 3600,
                  "id": 3163858,
                  "name": "Zocca",
                  "cod": 200
                }""";
    }
}