package com.serj.openweather.current;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.*;

@WireMockTest
class CurrentWeatherFetcherTest {

    @Test
    void simpleStubTesting(WireMockRuntimeInfo wmRuntimeInfo) {

        String responseBody = "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": 10.99,\n" +
                "    \"lat\": 44.34\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 804,\n" +
                "      \"main\": \"Clouds\",\n" +
                "      \"description\": \"overcast clouds\",\n" +
                "      \"icon\": \"04d\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 279.54,\n" +
                "    \"feels_like\": 278.03,\n" +
                "    \"temp_min\": 276.07,\n" +
                "    \"temp_max\": 282.64,\n" +
                "    \"pressure\": 1017,\n" +
                "    \"humidity\": 87,\n" +
                "    \"sea_level\": 1017,\n" +
                "    \"grnd_level\": 930\n" +
                "  },\n" +
                "  \"visibility\": 10000,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 2.11,\n" +
                "    \"deg\": 168,\n" +
                "    \"gust\": 5.52\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 100\n" +
                "  },\n" +
                "  \"dt\": 1673167259,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 2,\n" +
                "    \"id\": 2004688,\n" +
                "    \"country\": \"IT\",\n" +
                "    \"sunrise\": 1673160680,\n" +
                "    \"sunset\": 1673193201\n" +
                "  },\n" +
                "  \"timezone\": 3600,\n" +
                "  \"id\": 3163858,\n" +
                "  \"name\": \"Zocca\",\n" +
                "  \"cod\": 200\n" +
                "}";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=7b0a92bf3d8a70ebcf466da40444ed89";

        //Define stub
        stubFor(WireMock.get(apiUrl).willReturn(ok(responseBody)));

        //Hit API and check response
        String apiResponse = getContent(apiUrl);
        System.out.println(apiResponse);
        assertEquals(apiResponse, responseBody);

        //Verify API is hit
    }

    private String getContent(String url) {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        return testRestTemplate.getForObject(url, String.class);
    }
}