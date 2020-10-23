package com.example.test.demo.controller;

import com.example.test.demo.CatRepository;
import com.example.test.demo.entity.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class CatController {

    private final CatRepository catRepository;

    private final DataSource dataSource;

    public CatController(CatRepository catRepository, DataSource dataSource) {
        this.catRepository = catRepository;
        this.dataSource = dataSource;
    }

/*    @GetMapping("/cats")
    public Cat get*/

    @GetMapping("/cats/{id}")
    public Cat getCatId(@PathVariable("id") Integer id) {
        Cat cat = catRepository.getOne(id);
        Cat result = new Cat();
        result.setId(cat.getId());
        result.setName(cat.getName());
        result.setColor(cat.getColor());
        result.setPawsCount(cat.getPawsCount());
        return result;
    }

    @GetMapping("/users/{id}")
    public Object getCatByIdJDBC(@PathVariable("id") Integer id) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String,Integer> map = new HashMap<>();
        map.put("id", id);
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM cats WHERE id=:id", map);
        return stringObjectMap;
    }
}
