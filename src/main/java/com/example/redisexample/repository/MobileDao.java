package com.example.redisexample.repository;

import com.example.redisexample.entity.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MobileDao {

    public static final String HASH_KEY = "Mobile";
    @Autowired
    private RedisTemplate<String, Object> template;

    @CachePut(value = "Mobile")
    public Mobile save(Mobile mobile){
        template.opsForHash().put(HASH_KEY, mobile.getId(), mobile);
        return mobile;
    }

    @Cacheable(value = "Mobile")
    public List<Mobile> findAll(){
        System.out.println("calles findAll from DB");
        return template.opsForHash().values(HASH_KEY).stream().map(m -> (Mobile)m).toList();
    }

    @Cacheable(key = "#id", value = "Mobile")
//    @Cacheable(key = "#id", value = "Mobile", unless = "#result.price > 40000")
    public Mobile findMobileById(int id){
        System.out.println("calles findMobileById from DB");
        return (Mobile) template.opsForHash().get(HASH_KEY,id);
    }


//        @CacheEvict(key = "#id", value = "Mobile")
    @Caching(evict = {
            @CacheEvict(key = "#id", value = "Mobile"),
            @CacheEvict(value = "AllMobiles")
    })
    public String deleteMobile(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "mobile removed !!";
    }
}
