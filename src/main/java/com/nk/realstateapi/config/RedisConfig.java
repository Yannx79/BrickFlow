package com.nk.realstateapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(org.springframework.core.env.Environment env) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(env.getProperty("spring.data.redis.host", "localhost"));
        config.setPort(Integer.parseInt(env.getProperty("spring.data.redis.port", "6379")));
        String password = env.getProperty("spring.data.redis.password");
        if (password != null && !password.isBlank()) {
            config.setPassword(RedisPassword.of(password));
        }
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisSerializer<Object> jacksonSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.activateDefaultTyping(
                com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
        );

        return new GenericJackson2JsonRedisSerializer(mapper);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory,
                                                       RedisSerializer<Object> jacksonSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jacksonSerializer);
        template.setHashValueSerializer(jacksonSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory,
                                     RedisSerializer<Object> serializer) {
        // Base Config
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues();

        // Custom TTL
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put("CompanyServiceImplCache", defaultConfig.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("HousingServiceImplCache", defaultConfig.entryTtl(Duration.ofHours(6)));
        cacheConfigurations.put("PopulationServiceImplCache", defaultConfig.entryTtl(Duration.ofMinutes(15)));
        cacheConfigurations.put("PromotionServiceImplCache", defaultConfig.entryTtl(Duration.ofHours(3)));
        cacheConfigurations.put("RealStateServiceImplCache", defaultConfig.entryTtl(Duration.ofMinutes(45)));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .transactionAware()
                .build();
    }

    @Bean
    public KeyGenerator versionedKeyGenerator() {
        return (target, method, params) -> {
            String prefix = "realStateAPI:v1:" + target.getClass().getSimpleName() + ":" + method.getName();
            String args = Arrays.stream(params)
                    .map(String::valueOf)
                    .collect(Collectors.joining(":"));
            return args.isEmpty() ? prefix : prefix + ":" + args;
        };
    }
}
