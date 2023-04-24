package com.example.hrplatform.service;

import com.example.hrplatform.dao.SkillDAO;
import com.example.hrplatform.model.Skill;

import java.sql.SQLException;
import java.util.List;

public class SkillService {
    private final SkillDAO skillDAO;

    public SkillService(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    public Skill addSkill(Skill skill) throws SQLException {
        return skillDAO.addSkill(skill);
    }

    public Skill updateSkill(int id, Skill skill) throws SQLException {
        return skillDAO.updateSkill(id, skill);
    }

    public void deleteSkill(int id) throws SQLException {
        skillDAO.deleteSkill(id);
    }

    public List<Skill> getAllSkills() throws SQLException {
        return skillDAO.getAllSkills();
    }

    public Skill getSkillById(int id) throws SQLException {
        return skillDAO.getSkillById(id);
    }
}
