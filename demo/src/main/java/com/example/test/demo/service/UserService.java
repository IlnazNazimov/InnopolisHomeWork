package com.example.test.demo.service;

import com.example.test.demo.repository.UserRepository;
import com.example.test.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DataSource dataSource;
    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Object getUserById(Integer id) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM users WHERE id=:id", map);
        return stringObjectMap;
    }
}
