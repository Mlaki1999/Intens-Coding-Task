//package com.example.hrplatform.tests;
//
//
//import com.example.hrplatform.controller.CandidateController;
//import com.example.hrplatform.dao.CandidateDAO;
//import com.example.hrplatform.model.Candidate;
//import com.example.hrplatform.service.CandidateService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(CandidateController.class)
//public class RestApiCandidateTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    private CandidateDAO candidateDAO;
//    private CandidateService candidateService;
//
//    @BeforeEach
//    public void setUp() {
//        candidateDAO = mock(CandidateDAO.class);
//        candidateService = new CandidateService(candidateDAO);
//    }
//
//    @Test
//    public void getAllCandidates_ReturnsListOfCandidates() throws Exception {
//
//        Date newDate = new Date(1990,1,1);
//
//        Candidate newCandidate1 = new Candidate("John Smith", newDate, "123456", "john.smith@example.com");
//        Candidate newCandidate2 = new Candidate("Jane Doe", newDate, "654321", "jane.doe@example.com");
//
//        System.out.println(candidateService.addCandidate(newCandidate1));
//        candidateService.addCandidate(newCandidate2);
//
//
//        System.out.println(newCandidate1.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//
//
//        List<Candidate> candidates = Arrays.asList(
////                new Candidate("John Smith", new Date(1980, 1, 1), "123456", "john.smith@example.com"),
////                new Candidate("Jane Doe", new Date(1985, 2, 2), "654321", "jane.doe@example.com")
//                newCandidate1,
//                newCandidate2
//        );
//
////        LocalDate newDate = candidates.get(0).getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
//
//        when(candidateService.getAllCandidates()).thenReturn(candidates);
//
//        mockMvc.perform(get("/candidates"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{name:'John Smith',dateOfBirth:'1980-01-01',contactNumber:'123456',email:'john.smith@example.com'},{name:'Jane Doe',dateOfBirth:'1985-02-02',contactNumber:'654321',email:'jane.doe@example.com'}]"));
//    }
//
//    @Test
//    public void getCandidateById_ReturnsCandidate() throws Exception {
//        Candidate candidate = new Candidate("John Smith", new Date(1980, 1, 1), "123456", "john.smith@example.com");
//
//        when(candidateService.getCandidateById(1)).thenReturn(candidate);
//
//        mockMvc.perform(get("/candidates/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{name:'John Smith',dateOfBirth:'1980-01-01',contactNumber:'123456',email:'john.smith@example.com'}"));
//    }
//
//    @Test
//    public void addCandidate_ReturnsAddedCandidate() throws Exception {
//        Candidate candidate = new Candidate("John Smith",new Date(1980, 1, 1), "123456", "john.smith@example.com");
//
//        when(candidateService.addCandidate(any(Candidate.class))).thenReturn(candidate);
//
//        mockMvc.perform(post("/candidates")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{name:'John Smith',dateOfBirth:'1980-01-01',contactNumber:'123456',email:'john.smith@example.com'}"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{name:'John Smith',dateOfBirth:'1980-01-01',contactNumber:'123456',email:'john.smith@example.com'}"));
//    }
//
//    @Test
//    public void updateCandidate_ReturnsUpdatedCandidate() throws Exception {
//        Candidate candidate = new Candidate("John Smith", new Date(1980, 1, 1), "123456", "john.smith@example.com");
//
//        when(candidateService.updateCandidate(eq(1), any(Candidate.class))).thenReturn(candidate);
//
//        mockMvc.perform(put("/candidates/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{name:'John Smith',dateOfBirth:'1980-01-01',contactNumber:'123456',email:'john.smith@example.com'}"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{name:'John Smith',dateOfBirth:'1980-01-01',contactNumber:'123456',email:'john.smith@example.com'}"));
//    }
//
//    @Test
//    public void deleteCandidate_DeletesCandidate() throws Exception {
//        mockMvc.perform(delete("/candidates/1"))
//                .andExpect(status().isOk());
//
//        verify(candidateService, times(1)).deleteCandidate(eq(1));
//    }
//}
