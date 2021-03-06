package com.example.spring.guava.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author sunboyu
 * @date 2018/1/26
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {

    /**
     * cache key
     */
    private static final String HOTEL_POSTION = "hotel_position";

    @Bean
    public Cache defaultCacheBuilder() {
        return CacheBuilder.newBuilder()
                .recordStats()
                // 设置大小，条目数
                .maximumSize(1 << 10)
                // 设置失效时间，创建时间
                .expireAfterWrite(20, TimeUnit.SECONDS)
                // 设置时效时间，最后一次被访问
                .expireAfterAccess(20, TimeUnit.HOURS)
                // 移除监听
                .removalListener((RemovalListener) removalNotification -> System.out.println("有缓存数据被移除了")
                ).build();
    }

    @Bean
    public GuavaCache buildHotelPositionCache() {
        return new GuavaCache(HOTEL_POSTION, defaultCacheBuilder());
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(buildHotelPositionCache()));
        return manager;
    }
}