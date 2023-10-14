package goit.devProjectTeam2.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class CacheManager {

    @Bean("CacheManager")
    public org.springframework.cache.CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                //винести у змінні оточення
                                .maximumSize(1000)
                                .expireAfterWrite(15, TimeUnit.MINUTES)
                                .build().asMap(),
                        false);
            }
        };
    }

}
