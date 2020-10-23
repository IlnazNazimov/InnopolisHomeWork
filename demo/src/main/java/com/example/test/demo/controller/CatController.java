package com.example.test.demo.controller;

import com.example.test.demo.entity.Cat;
import com.example.test.demo.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/cats")
    public List<Cat> getAllCats() {
        return catService.findAllCats();
    }

    @GetMapping("/cats/{id}")
    public Object getCatId(@PathVariable("id") Integer id) {
        return catService.getCatsById(id);
    }
}
