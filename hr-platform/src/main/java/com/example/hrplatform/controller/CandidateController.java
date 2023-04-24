package com.example.hrplatform.controller;

import com.example.hrplatform.model.Candidate;
import com.example.hrplatform.model.Skill;
import com.example.hrplatform.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable int id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    public Candidate addCandidate(@RequestBody Candidate candidateDTO) throws SQLException {
        return candidateService.addCandidate(candidateDTO);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable int id, @RequestBody Candidate candidateDTO) throws SQLException {
        return candidateService.updateCandidate(id, candidateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable int id) throws SQLException {
        candidateService.deleteCandidate(id);
    }

    @GetMapping("/search")
    public List<Candidate> searchCandidatesBySkill(@RequestParam String skill) {
        return candidateService.searchCandidatesBySkill(skill);
    }

    @GetMapping("/searchByName")
    public List<Candidate> searchCandidatesByName(@RequestParam String name) throws SQLException {
        return candidateService.searchCandidatesByName(name);
    }

    @PostMapping("/{id}/skills")
    public Candidate addSkillToCandidate(@PathVariable int id, @RequestParam int skillId) {
        return candidateService.addSkillToCandidate(id, skillId);
    }

    @DeleteMapping("/{id}/skills/{skillId}")
    public Candidate removeSkillFromCandidate(@PathVariable int id, @PathVariable int skillId) {
        return candidateService.removeSkillFromCandidate(id, skillId);
    }

    @GetMapping("/allSkills/{candidateId}")
    public List<Skill> getCandidateSkillsById(@PathVariable int candidateId) {
        return  candidateService.getCandidateSkillsById(candidateId);
    }
}
