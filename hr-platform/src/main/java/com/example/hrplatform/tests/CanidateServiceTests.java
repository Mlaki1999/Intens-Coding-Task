package com.example.hrplatform.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.hrplatform.dao.CandidateDAO;
import com.example.hrplatform.model.Candidate;
import com.example.hrplatform.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class CanidateServiceTests {

    private CandidateDAO candidateDAO;
    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        candidateDAO = mock(CandidateDAO.class);
        candidateService = new CandidateService(candidateDAO);
    }

    @Test
    public void testAddCandidate() throws SQLException {
        Date newDate = new Date(1990,1,1);
        Candidate candidate = new Candidate("John Doe", newDate, "123", "john.doe@example.com");
        Candidate expectedCandidate = new Candidate(1, "John Doe", newDate, "1234", "john.doe@example.com");

        when(candidateDAO.createCandidate(candidate)).thenReturn(expectedCandidate);

        Candidate result = candidateService.addCandidate(candidate);

        assertEquals(expectedCandidate, result);
        verify(candidateDAO, times(1)).createCandidate(candidate);
    }

    @Test
    public void testUpdateCandidate() throws SQLException {
        Date newDate = new Date(1990,1,1);
        Candidate candidate = new Candidate("John Doe", newDate, "123", "john.doe@example.com");
        Candidate expectedCandidate = new Candidate(1, "John Doe2", newDate, "123", "john.doe@example.com");

        when(candidateDAO.updateCandidate(1, candidate)).thenReturn(expectedCandidate);

        Candidate result = candidateService.updateCandidate(1, candidate);

        assertEquals(expectedCandidate, result);
        verify(candidateDAO, times(1)).updateCandidate(1, candidate);
    }

    @Test
    public void testDeleteCandidate() throws SQLException {
        int candidateId = 1;

        candidateService.deleteCandidate(candidateId);

        verify(candidateDAO, times(1)).deleteCandidate(candidateId);
    }

    @Test
    public void testGetCandidateById() throws SQLException {
        Date newDate = new Date(1990,1,1);
        int candidateId = 1;
        Candidate expectedCandidate = new Candidate(candidateId, "John Doe", newDate, "123", "john.doe@example.com");

        when(candidateDAO.getCandidateById(candidateId)).thenReturn(expectedCandidate);

        Candidate result = candidateService.getCandidateById(candidateId);

        assertEquals(expectedCandidate, result);
        verify(candidateDAO, times(1)).getCandidateById(candidateId);
    }

    @Test
    public void testGetAllCandidates() throws SQLException {
        Date newDate = new Date(1990,1,1);
        List<Candidate> expectedCandidates = new ArrayList<>(Arrays.asList(
                new Candidate(1, "John Doe", newDate, "12345", "john.doe@example.com"),
                new Candidate(2, "Jane Doe", newDate, "123", "john.doe@example.com")));

        when(candidateDAO.getAllCandidates()).thenReturn(expectedCandidates);

        List<Candidate> result = candidateService.getAllCandidates();

        assertEquals(expectedCandidates, result);
        verify(candidateDAO, times(1)).getAllCandidates();
    }
}
