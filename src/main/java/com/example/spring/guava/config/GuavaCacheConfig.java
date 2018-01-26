package com.example.spring.guava.config;

import com.example.spring.guava.domain.Person;
import com.google.common.cache.*;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunboyu
 * @date 2018/1/26
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {

    @Bean
    public CacheManager cacheManager() {

        final List<Person> list = new ArrayList<>();
        list.add(new Person("1", "zhangsan"));
        list.add(new Person("2", "lisi"));
        list.add(new Person("3", "wangwu"));

        GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder()
                // 设置大小，条目数
                .maximumSize(1 << 10)
                // 设置失效时间，创建时间
                .expireAfterWrite(20, TimeUnit.SECONDS)
                // 设置时效时间，最后一次被访问
                .expireAfterAccess(20, TimeUnit.HOURS)
                // 移除监听
                .removalListener(new RemovalListener() {
                                     @Override
                                     public void onRemoval(RemovalNotification removalNotification) {
                                         System.out.println("有缓存数据被移除了");
                                     }
                                 }
                ));
        return guavaCacheManager;
    }
}
