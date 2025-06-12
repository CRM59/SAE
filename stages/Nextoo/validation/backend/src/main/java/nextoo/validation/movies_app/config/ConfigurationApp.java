package nextoo.validation.movies_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ConfigurationApp {

    @Bean(name = "themoviedbAPI")
    public RestClient getWebClient(@Value("${api.themoviedb.discover.url}") String rootUrl) {
        return RestClient.builder()
                .baseUrl(rootUrl)
                .build();
    }
}
