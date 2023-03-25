package com.example.redisexample.repository;

import com.example.redisexample.entity.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MobileDao {

    public static final String HASH_KEY = "Mobile";
    @Autowired
    private RedisTemplate template;

    public Mobile save(Mobile mobile){
        template.opsForHash().put(HASH_KEY, mobile.getId(), mobile);
        return mobile;
    }

    public List<Mobile> findAll(){
        System.out.println("calles findAll from DB");
        return template.opsForHash().values(HASH_KEY);
    }

    public Mobile findMobileById(int id){
        System.out.println("calles findMobileById from DB");
        return (Mobile) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteMobile(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "mobile removed !!";
    }
}
