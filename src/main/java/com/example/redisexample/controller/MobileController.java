package com.example.redisexample.controller;

import com.example.redisexample.entity.Mobile;
import com.example.redisexample.repository.MobileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile")
@EnableCaching
public class MobileController {

        @Autowired
        private MobileDao dao;

        @PostMapping
        public Mobile save(@RequestBody Mobile mobile) {
            return dao.save(mobile);
        }

        @GetMapping
        @Cacheable(value = "AllMobiles")
        public List<Mobile> getAllMobiles() {
            return dao.findAll();
        }

        @GetMapping("/{id}")
        @Cacheable(key = "#id", value = "Mobile")
//        @Cacheable(key = "#id", value = "Mobile", unless = "#result.price > 40000")
        public Mobile getMobile(@PathVariable int id) {
            return dao.findMobileById(id);
        }
        @DeleteMapping("/{id}")
        @CacheEvict(key = "#id", value = "Mobile")
        public String remove(@PathVariable int id)   {
            return dao.deleteMobile(id);
        }

    }
