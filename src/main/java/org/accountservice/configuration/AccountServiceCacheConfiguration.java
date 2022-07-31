package org.accountservice.configuration;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Account service cache configuration
 */
@Configuration
@EnableCaching
public class AccountServiceCacheConfiguration {

    @Bean
    CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(
                new ConcurrentMapCache(
                        "cacheValue",
                        CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build().asMap(),
                        false
                )
        ));
        return simpleCacheManager;
    }
}
