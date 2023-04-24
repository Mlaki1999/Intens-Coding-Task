package com.example.hrplatform.tests;

import com.example.hrplatform.controller.SkillController;
import com.example.hrplatform.model.Skill;
import com.example.hrplatform.service.SkillService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
@WebMvcTest(SkillController.class)
public class RestApiSkillTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

    @Test
    public void testAddSkill_ReturnsNewSkill() throws Exception {
        Skill skill = new Skill(1, "Java");
        when(skillService.addSkill(any(Skill.class))).thenReturn(skill);

        String requestBody = "{\"name\": \"Java\"}";
        MockHttpServletRequestBuilder requestBuilder = post("/api/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(skill.getId()))
                .andExpect(jsonPath("$.name").value(skill.getName()));
    }

    @Test
    public void testUpdateSkill_ReturnsUpdatedSkill() throws Exception {
        Skill skill = new Skill(1, "Java");
        when(skillService.updateSkill(eq(1), any(Skill.class))).thenReturn(skill);

        String requestBody = "{\"name\": \"Java\"}";
        MockHttpServletRequestBuilder requestBuilder = put("/api/skills/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(skill.getId()))
                .andExpect(jsonPath("$.name").value(skill.getName()));
    }

    @Test
    public void testGetAllSkills_ReturnsListOfSkills() throws Exception {
        List<Skill> skills = Arrays.asList(new Skill(1, "Java"), new Skill(2, "Python"));
        when(skillService.getAllSkills()).thenReturn(skills);

        MockHttpServletRequestBuilder requestBuilder = get("/api/skills");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(skills.get(0).getId()))
                .andExpect(jsonPath("$[0].name").value(skills.get(0).getName()))
                .andExpect(jsonPath("$[1].id").value(skills.get(1).getId()))
                .andExpect(jsonPath("$[1].name").value(skills.get(1).getName()));
    }

    @Test
    public void testGetSkillById_ReturnsSkillWithMatchingId() throws Exception {
        Skill skill = new Skill(1, "Java");
        when(skillService.getSkillById(1)).thenReturn(skill);

        MockHttpServletRequestBuilder requestBuilder = get("/api/skills/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(skill.getId()))
                .andExpect(jsonPath("$.name").value(skill.getName()));
    }

    @Test
    public void testDeleteSkill_DeletesSkillWithMatchingId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/api/skills/1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(skillService, times(1)).deleteSkill(eq(1));
    }
}
