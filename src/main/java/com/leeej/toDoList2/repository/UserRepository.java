package com.leeej.toDoList2.repository;

import com.leeej.toDoList2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public int save(User user) {
        String sql = " INSERT INTO users(username, password) VALUES(?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
}
