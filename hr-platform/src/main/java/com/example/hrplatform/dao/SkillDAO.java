package com.example.hrplatform.dao;

import com.example.hrplatform.model.Skill;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO {

    private final JdbcTemplate jdbcTemplate;

    public SkillDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Skill addSkill(Skill skill) {
        String insertQuery = "INSERT INTO skills (name) VALUES (?)";
        jdbcTemplate.update(insertQuery, skill.getName());
        return skill;
    }

    public Skill updateSkill(int id, Skill skill) {
        String updateQuery = "UPDATE skills SET name = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, skill.getName(), id);
        return skill;
    }

    public void deleteSkill(int id) {
        String deleteQuery = "DELETE FROM skills WHERE id = ?";
        jdbcTemplate.update(deleteQuery, id);
    }

    public List<Skill> getAllSkills() {
        String selectQuery = "SELECT * FROM skills";
        return jdbcTemplate.query(selectQuery, (rs, rowNum) -> {
            Skill skill = new Skill();
            skill.setId(rs.getInt("id"));
            skill.setName(rs.getString("name"));
            return skill;
        });
    }

    public Skill getSkillById(int id) {
        String selectQuery = "SELECT * FROM skills WHERE id = ?";
        return jdbcTemplate.queryForObject(selectQuery, new Object[]{id}, (rs, rowNum) -> {
            Skill skill = new Skill();
            skill.setId(rs.getInt("id"));
            skill.setName(rs.getString("name"));
            return skill;
        });
    }
}
