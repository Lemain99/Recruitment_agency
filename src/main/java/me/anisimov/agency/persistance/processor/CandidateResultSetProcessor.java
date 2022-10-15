package me.anisimov.agency.persistance.processor;

import me.anisimov.agency.domain.Candidate;
import me.anisimov.agency.persistance.resolver.CandidateSkillsResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateResultSetProcessor implements ResultSetProcessor<Candidate> {
    @Autowired
    CandidateSkillsResolver candidateSkillsResolver;
    @Override
    public Candidate process(ResultSet resultSet) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(resultSet.getLong("id"));
        candidate.setName(resultSet.getString("name"));
        candidate.setSurname(resultSet.getString("surname"));
        candidate.setMiddleName(resultSet.getString("middlename"));
        candidate.setCitizenShip(resultSet.getString("citizen_ship"));
        candidate.setRequiredExperience(resultSet.getByte("required_experience"));
        candidate.setDesiredSalary(resultSet.getInt("desired_salary"));
        candidate.setDesiredCareer(resultSet.getString("desired_career"));
        candidate.setBasicDescription(resultSet.getString("basic_description"));
        candidate.setKeySkills(candidateSkillsResolver.resolve(candidate));
        return candidate;
    }
}
