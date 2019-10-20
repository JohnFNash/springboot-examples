package com.johnfnash.redis.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching // Enables Spring's annotation-driven cache management capability
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.cache.redis.time-to-live}")
    private int entryTTL;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostName, port);
        return new LettuceConnectionFactory(config);
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            // ��û��ָ������� keyʱ�������������������ͷ�������������key
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append('.').append(method.getName());
                if(params.length > 0) {
                    sb.append('[');
                    for (Object obj : params) {
                        sb.append(obj.toString());
                    }
                    sb.append(']');
                }
                System.out.println("keyGenerator=" + sb.toString());
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return new RedisCacheManager(
                 RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory),
                 this.getRedisCacheConfigurationWithTtl(entryTTL),  // Ĭ�ϲ��ԣ�δ���õ� key ��ʹ�����
                 this.getRedisCacheConfigurationMap()               // ָ�� key ����
            );
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<String, RedisCacheConfiguration>();
        redisCacheConfigurationMap.put("user", this.getRedisCacheConfigurationWithTtl(30)); // ��������ĳЩcache�ĳ�ʱʱ��
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        // ����CacheManager��ֵ���л���ʽΪJdkSerializationRedisSerializer,
        // ����ʵRedisCacheConfigurationĬ�Ͼ���ʹ��StringRedisSerializer���л�key��
        // JdkSerializationRedisSerializer���л�value,��������ע�ʹ���ΪĬ��ʵ��
        // ClassLoader loader = this.getClass().getClassLoader();
        // JdkSerializationRedisSerializer jdkSerializer = new
        // JdkSerializationRedisSerializer(loader);
        // RedisSerializationContext.SerializationPair<Object> pair =
        // RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        // RedisCacheConfiguration defaultCacheConfig =
        // RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        // RedisCacheConfiguration defaultCacheConfig =
        // RedisCacheConfiguration.defaultCacheConfig();

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
