package goit.devProjectTeam2.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheManager {

    @Value("app.cache_period")
    private static long cachePeriod;

    @Value("app.cache_size")
    private static long cacheSize;

    @Bean("CacheManager")
    public org.springframework.cache.CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                .maximumSize(cacheSize)
                                .expireAfterWrite(cachePeriod, TimeUnit.MINUTES)
                                .build().asMap(),
                        false);
            }
        };
    }

}
