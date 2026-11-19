package com.manu.jobapplicationtracker.service;

import com.manu.jobapplicationtracker.entity.RedisValueEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final ObjectMapper mapper;
    private final RedisTemplate<String, String> redisTemplate;

    RedisService(RedisTemplate<String, String> redisTemplate) {
        this.mapper = new ObjectMapper();
        this.redisTemplate = redisTemplate;
    }

    @Async
    public void set(String key,RedisValueEntity value){
        String valueAsString = mapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key,valueAsString,30, TimeUnit.MINUTES);
    }

    public RedisValueEntity get(String key){
        String valueAsString = redisTemplate.opsForValue().get(key);
        if(valueAsString == null) return null;
        RedisValueEntity value = mapper.readValue(valueAsString,RedisValueEntity.class);
        return value;
    }
    public void delete(String key){
        redisTemplate.delete(key);
    }
}
