package com.serj;

import com.serj.openweather.current.CurrentWeatherFetcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class AppConfig {

    @Bean
    public WeatherBot weatherBot(@Value("${telegram.bot.username}") String username,
                                 @Value("${telegram.bot.token}") String token) {
        return new WeatherBot(username, token);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(WeatherBot bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrentWeatherFetcher currentWeatherFetcher(RestTemplate restTemplate,
                                                       @Value("${open-weather.link}") String link,
                                                       @Value("${open-weather.api-key}") String apiKey) {
        return new CurrentWeatherFetcher(restTemplate, link, apiKey);
    }
}