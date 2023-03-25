package com.example.redisexample.controller;

import com.example.redisexample.entity.Product;
import com.example.redisexample.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

        @Autowired
        private ProductDao dao;

        @PostMapping
        public Product save(@RequestBody Product product) {
            return dao.save(product);
        }

        @GetMapping
        public List<Product> getAllProducts() {
            return dao.findAll();
        }

        @GetMapping("/{id}")
        public Product findProduct(@PathVariable int id) {
            return dao.findProductById(id);
        }
        @DeleteMapping("/{id}")
        public String remove(@PathVariable int id)   {
            return dao.deleteProduct(id);
        }

    }
