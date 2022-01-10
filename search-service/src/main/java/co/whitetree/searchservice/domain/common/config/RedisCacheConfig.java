package co.whitetree.searchservice.domain.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class RedisCacheConfig {
    private static final String PREFIX_CACHE_NAME = "search-service:";
    private static final int EXPIRATION_DAYS = 7; // 캐시 만료일 7일

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .prefixCacheNameWith(PREFIX_CACHE_NAME)
                .computePrefixWith(name -> name + ":") // 스프링 기본 separator 는 :: 이지만 관례상 : 로 변환
                .entryTtl(Duration.ofDays(EXPIRATION_DAYS))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer())); // 객체는 json 으로 변환해서 저장

        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(configuration)
                .build();
    }
}
