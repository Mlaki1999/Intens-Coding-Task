package com.example.hrplatform.tests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.example.hrplatform.dao.SkillDAO;
import com.example.hrplatform.model.Skill;
import com.example.hrplatform.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SkillServiceTests {

    @Mock
    private SkillDAO skillDAO;

    @InjectMocks
    private SkillService skillService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSkill() throws SQLException {
        Skill skillToAdd = new Skill("Java");
        Skill addedSkill = new Skill(1, "Java");

        when(skillDAO.addSkill(skillToAdd)).thenReturn(addedSkill);

        Skill returnedSkill = skillService.addSkill(skillToAdd);

        assertEquals(addedSkill, returnedSkill);

        verify(skillDAO, times(1)).addSkill(skillToAdd);
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void testUpdateSkill() throws SQLException {
        int skillId = 1;
        Skill skillToUpdate = new Skill("Java");
        Skill updatedSkill = new Skill(skillId, "Java");

        when(skillDAO.updateSkill(skillId, skillToUpdate)).thenReturn(updatedSkill);

        Skill returnedSkill = skillService.updateSkill(skillId, skillToUpdate);

        assertEquals(updatedSkill, returnedSkill);

        verify(skillDAO, times(1)).updateSkill(skillId, skillToUpdate);
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void testDeleteSkill() throws SQLException {
        Skill skillToDelete = new Skill(1, "Java");

        doNothing().when(skillDAO).deleteSkill(skillToDelete.getId());

        skillService.deleteSkill(skillToDelete.getId());
        verify(skillDAO, times(1)).deleteSkill(skillToDelete.getId());
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void testGetAllSkills() throws SQLException {
        List<Skill> expectedSkills = Arrays.asList(new Skill(1, "Java"), new Skill(2, "Python"));

        when(skillDAO.getAllSkills()).thenReturn(expectedSkills);

        List<Skill> returnedSkills = skillService.getAllSkills();

        assertEquals(expectedSkills, returnedSkills);

        verify(skillDAO, times(1)).getAllSkills();
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void testGetSkillById() throws SQLException {
        int skillId = 1;
        Skill expectedSkill = new Skill(skillId, "Java");

        when(skillDAO.getSkillById(skillId)).thenReturn(expectedSkill);

        Skill returnedSkill = skillService.getSkillById(skillId);

        assertEquals(expectedSkill, returnedSkill);

        verify(skillDAO, times(1)).getSkillById(skillId);
        verifyNoMoreInteractions(skillDAO);
    }

}
