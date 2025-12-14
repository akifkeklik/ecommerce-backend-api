package com.ecommerce.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis cache configuration with graceful fallback.
 */
@Configuration
@EnableCaching
public class RedisConfig {

        private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

        @Bean
        @ConditionalOnProperty(name = "spring.data.redis.enabled", havingValue = "true", matchIfMissing = false)
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
                RedisTemplate<String, Object> template = new RedisTemplate<>();
                template.setConnectionFactory(connectionFactory);
                template.setKeySerializer(new StringRedisSerializer());
                template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
                template.setHashKeySerializer(new StringRedisSerializer());
                template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
                template.afterPropertiesSet();
                return template;
        }

        @Bean
        @Primary
        @ConditionalOnProperty(name = "spring.data.redis.enabled", havingValue = "true", matchIfMissing = false)
        public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
                log.info("Initializing Redis cache manager");
                RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofHours(1))
                                .serializeKeysWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                                .disableCachingNullValues();

                Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
                cacheConfigurations.put("products", defaultConfig.entryTtl(Duration.ofMinutes(15)));
                cacheConfigurations.put("categories", defaultConfig.entryTtl(Duration.ofHours(2)));
                cacheConfigurations.put("featured-products", defaultConfig.entryTtl(Duration.ofMinutes(30)));
                cacheConfigurations.put("users", defaultConfig.entryTtl(Duration.ofMinutes(30)));
                cacheConfigurations.put("reviews", defaultConfig.entryTtl(Duration.ofHours(1)));
                cacheConfigurations.put("settings", defaultConfig.entryTtl(Duration.ofHours(24)));

                return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(defaultConfig)
                                .withInitialCacheConfigurations(cacheConfigurations)
                                .build();
        }

        @Bean
        @ConditionalOnProperty(name = "spring.data.redis.enabled", havingValue = "false", matchIfMissing = true)
        public CacheManager simpleCacheManager() {
                log.info("Redis not available, using in-memory cache");
                return new ConcurrentMapCacheManager("products", "categories", "featured-products", "users", "reviews",
                                "settings");
        }
}
