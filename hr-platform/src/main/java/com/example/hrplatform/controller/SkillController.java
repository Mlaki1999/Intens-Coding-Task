package com.example.hrplatform.controller;

import com.example.hrplatform.model.Skill;
import com.example.hrplatform.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) throws SQLException {
        Skill newSkill = new Skill(skill.getName());
        return skillService.addSkill(newSkill);
    }
    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable int id, @RequestBody Skill skill) throws SQLException {
        return skillService.updateSkill(id, skill);
    }
    @GetMapping
    public List<Skill> getAllSkills() throws SQLException {
        List<Skill> skills = skillService.getAllSkills();
        return skills;
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable int id) throws SQLException {
        Skill skill = skillService.getSkillById(id);
        return new Skill(skill.getId(), skill.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable int id) throws SQLException {
        skillService.deleteSkill(id);
    }
}