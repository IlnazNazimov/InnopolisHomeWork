package com.example.test.demo.service;

import com.example.test.demo.repository.CatRepository;
import com.example.test.demo.entity.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final DataSource dataSource;

    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }

    public Object getCatsById(Integer id) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM cats WHERE id=:id", map);
        return stringObjectMap;
    }
}
