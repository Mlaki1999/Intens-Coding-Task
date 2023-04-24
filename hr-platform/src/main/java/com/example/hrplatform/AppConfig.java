package com.example.hrplatform;

import com.example.hrplatform.dao.CandidateDAO;
import com.example.hrplatform.dao.SkillDAO;
import com.example.hrplatform.service.CandidateService;
import com.example.hrplatform.service.SkillService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/hr_platform");
        dataSource.setUsername("postgres");
        dataSource.setPassword("mladen");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public CandidateDAO candidateDAO() {
        return new CandidateDAO(jdbcTemplate());
    }

    @Bean
    public CandidateService candidateService() {
        return new CandidateService(candidateDAO());
    }

    @Bean
    public SkillDAO skillDAO() {
        return new SkillDAO(jdbcTemplate());
    }

    @Bean
    public SkillService skillService() {
        return new SkillService(skillDAO());
    }


}