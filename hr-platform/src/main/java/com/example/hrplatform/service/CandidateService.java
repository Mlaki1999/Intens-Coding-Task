package com.example.hrplatform.service;

import com.example.hrplatform.dao.CandidateDAO;
import com.example.hrplatform.model.Candidate;
import com.example.hrplatform.model.Skill;
import java.sql.SQLException;
import java.util.List;

public class CandidateService {
    private final CandidateDAO candidateDAO;

    public CandidateService(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
    }

    public Candidate addCandidate(Candidate candidate) throws SQLException {
        return candidateDAO.createCandidate(candidate);
    }

    public Candidate updateCandidate(int id, Candidate candidate) throws SQLException {
        return candidateDAO.updateCandidate(id, candidate);
    }

    public void deleteCandidate(int candidateId) throws SQLException {
        candidateDAO.deleteCandidate(candidateId);
    }

    public Candidate getCandidateById(int candidateId) {
        return candidateDAO.getCandidateById(candidateId);
    }

    public List<Candidate> getAllCandidates() {
        return candidateDAO.getAllCandidates();
    }

    public List<Candidate> searchCandidatesByName(String name) throws SQLException {
        return candidateDAO.searchCandidatesByName(name);
    }

    public List<Candidate> searchCandidatesBySkill(String skill) {
        return candidateDAO.searchCandidatesBySkill(skill);
    }

    public Candidate addSkillToCandidate(int candidateId, int skillId) {
       return candidateDAO.addSkillToCandidate(candidateId, skillId);
    }

    public Candidate removeSkillFromCandidate(int candidateId, int skillId) {
        return candidateDAO.removeSkillFromCandidate(candidateId, skillId);
    }

    public List<Skill> getCandidateSkillsById(int candidateId) {
        return  candidateDAO.getCandidateSkillsById(candidateId);
    }
}
