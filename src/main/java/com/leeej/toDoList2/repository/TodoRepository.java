package com.leeej.toDoList2.repository;

import com.leeej.toDoList2.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Todo> todoRowMapper = (resultSet, rowNum) -> {
        Todo todo = Todo.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .completed(resultSet.getBoolean("completed"))
                .userId(resultSet.getInt("user_id"))
                .build();

        return todo;
    };

    public List<Todo> findAllByUserId(int userId) {
        String sql = "SELECT * FROM todos WHERE user_id = ? ORDER BY id";

        return jdbcTemplate.query(sql, todoRowMapper, userId);
    }

    public int save(Todo todo) {
        String sql = " INSERT INTO todos(user_id, title, completed) VALUES (?, ?, ?) ";

        return jdbcTemplate.update(sql, todo.getUserId(), todo.getTitle(), todo.isCompleted());
    }

    public int update(Todo todo) {
        String sql = " UPDSTE todos SET title = ?, completed = ? WHERE id = ? AND user_id = ? ";

        return jdbcTemplate.update(sql, todo.getTitle(), todo.isCompleted(), todo.getId(), todo.getUserId());
    }

    public int deleteByIdAndUserId(int id, int userId) {
        String sql = " DELETE FROM todos WHERE id = ? AND user_id = ? ";

        return jdbcTemplate.update(sql, id, userId);
    }

    public Todo findByIdAndUserId(int id, int userId) {
        String sql = "SELECT * FROM todos WHERE id = ? AND user_id = ?";

        return jdbcTemplate.queryForObject(sql, todoRowMapper, id , userId);
    }
}
