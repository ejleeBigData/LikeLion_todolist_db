package com.leeej.toDoList2.repository;

import com.leeej.toDoList2.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public int save(Todo todo) {
        String sql = " INSERT INTO todos(user_id, title, completed) VALUES (?, ?, ?) ";

        return jdbcTemplate.update(sql, todo.getUserId(), todo.getTitle(), false);
    }
}
