package com.example.spring_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class CustomizedStudentRepositoryImpl implements CustomizedStudentRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from student where name = :name",
                Map.of("name", name));
    }
}
