package nextoo.weather.sport.app.config;

import nextoo.weather.sport.app.data.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ConfigurationApp {
    public static final int WEEK_DAYS_NUMBER = 5;
    public static final int WEATHER_START_HOUR = 10;
    public static final int WEATHER_END_HOUR = 20;

    @Bean(name = "previsionMeteoAPI")
    public RestClient getWebClient(@Value("${api.weather.root.url}") String rootUrl) {
        return RestClient.builder()
                .baseUrl(rootUrl)
                .build();
    }

    @Bean(name = "database")
    public Database initDB() {
        return new Database();
    }

    @Bean(name = "weatherDAO")
    public WeatherRepository initWeatherDAO(@Qualifier("database") Database database) {
        WeatherRepository.setDatabase(database);
        return new WeatherRepository();
    }
}
