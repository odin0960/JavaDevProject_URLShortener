package goit.devProjectTeam2.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAPI {
    @Bean
    public OpenAPI customOpenAPI(@Value("${app.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("API LinkShortener")
                .version(appVersion)
                .description("The Link Shortener project using Java and Spring Boot")
                  );
    }



}
