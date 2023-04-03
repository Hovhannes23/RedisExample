package com.example.redisexample.controller;

import com.example.redisexample.entity.Mobile;
import com.example.redisexample.repository.MobileDao;
import org.springframework.beans.factory.annotation.Autowired;
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
        public List<Mobile> getAllMobiles() {
            return dao.findAll();
        }

        @GetMapping("/{id}")
        public Mobile getMobile(@PathVariable int id) {
            return dao.findMobileById(id);
        }

        @DeleteMapping("/{id}")
        public String remove(@PathVariable int id)   {
            return dao.deleteMobile(id);
        }

    }
