package com.example.test.demo.repository;

import com.example.test.demo.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}