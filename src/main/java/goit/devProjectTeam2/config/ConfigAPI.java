package goit.devProjectTeam2.config;

import com.google.common.cache.CacheBuilder;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ConfigAPI {
    @Bean
    public OpenAPI customOpenAPI(@Value("${version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("API LinkShortener")
                .version(appVersion)
                .description("The Link Shortener project using Java and Spring Boot")
                  );
    }



}
