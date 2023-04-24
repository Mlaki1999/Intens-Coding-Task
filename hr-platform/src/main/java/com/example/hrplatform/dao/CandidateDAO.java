package com.example.hrplatform.dao;

import com.example.hrplatform.model.Candidate;
import com.example.hrplatform.model.Skill;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    private final JdbcTemplate jdbcTemplate;

    private final SkillDAO skillDAO;

    public CandidateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        skillDAO=new SkillDAO(jdbcTemplate);
    }

    public Candidate createCandidate(Candidate candidate) {
        String sql = "INSERT INTO candidates (name, date_of_birth, contact_number, email) " +
                "VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, candidate.getName());
            LocalDate localDate = candidate.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            pstmt.setDate(2, Date.valueOf(localDate));
            pstmt.setString(3, candidate.getContactNumber());
            pstmt.setString(4, candidate.getEmail());
            return pstmt;
        }, keyHolder);
        return candidate;
    }

    public Candidate updateCandidate(int id, Candidate candidate) {
        String sql = "UPDATE candidates SET name = ?, date_of_birth = ?, contact_number = ?, email = ? WHERE id = ?";
        LocalDate localDate = candidate.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        jdbcTemplate.update(sql, candidate.getName(),
                Date.valueOf(localDate),
                candidate.getContactNumber(),
                candidate.getEmail(),
                id);
        return candidate;
    }

    public void deleteCandidate(int id) {
        String sql = "DELETE FROM candidates WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Candidate> searchCandidatesByName(String name) {
        String sql = "SELECT * FROM candidates WHERE name LIKE ?";
        List<Candidate> candidates = jdbcTemplate.query(sql, new Object[]{"%" + name + "%"},
                (rs, rowNum) -> {
                    Candidate candidate = new Candidate();
                    candidate.setId(rs.getInt("id"));
                    candidate.setName(rs.getString("name"));
                    candidate.setDateOfBirth(rs.getDate("date_of_birth"));
                    candidate.setContactNumber(rs.getString("contact_number"));
                    candidate.setEmail(rs.getString("email"));
                    return candidate;
                });
        return candidates;
    }

    public List<Candidate> searchCandidatesBySkill(String skillName) {
        String sql = "SELECT c.id, c.name, c.date_of_birth, c.contact_number, c.email " +
                "FROM candidates c " +
                "JOIN candidate_skills cs ON c.id = cs.candidate_id " +
                "JOIN skills s ON cs.skill_id = s.id " +
                "WHERE s.name = ?";
        List<Candidate> candidates = jdbcTemplate.query(sql, new Object[]{skillName}, (rs, rowNum) -> {
            Candidate candidate = new Candidate(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("date_of_birth"),
                    rs.getString("contact_number"),
                    rs.getString("email")
            );
            return candidate;
        });
        return candidates;
    }

    public Candidate getCandidateById(int candidateId) {
        String sql = "SELECT * FROM candidates WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{candidateId},
                    (resultSet, i) -> new Candidate(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getString("contact_number"),
                            resultSet.getString("email")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Candidate> getAllCandidates() {
        String sql = "SELECT * FROM candidates";
        List<Candidate> candidates = new ArrayList<>();
        jdbcTemplate.query(sql, (resultSet, i) -> new Candidate(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("email")
                )
        ).forEach(candidates::add);
        return candidates;
    }

    public Candidate addSkillToCandidate(int candidateId, int skillId) {
        String sql = "INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, new Object[]{candidateId, skillId});
        getCandidateById(candidateId).addSkill(skillDAO.getSkillById(skillId));
        Candidate newCandidate = getCandidateById(candidateId);
        Skill newSkill = skillDAO.getSkillById(skillId);
        System.out.println(newCandidate);
        System.out.println(newSkill);
        return  getCandidateById(candidateId);
    }

    public Candidate removeSkillFromCandidate(int candidateId, int skillId) {
        String sql = "DELETE FROM candidate_skills WHERE candidate_id = ? AND skill_id = ?";
        jdbcTemplate.update(sql, new Object[]{candidateId, skillId});
        return getCandidateById(candidateId);
    }

    public List<Skill> getCandidateSkillsById(int candidateId) {
        String sql = "SELECT s.* FROM skills s " +
                "INNER JOIN candidate_skills cs ON s.id = cs.skill_id " +
                "INNER JOIN candidates c ON c.id = cs.candidate_id " +
                "WHERE c.id = ?";
        return jdbcTemplate.query(sql, new Object[]{candidateId}, (rs, rowNum) -> new Skill(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }
}
