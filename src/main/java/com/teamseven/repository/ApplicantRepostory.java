package com.teamseven.repository;

import com.teamapt.base.lib.repository.BaseRepository;
import com.teamseven.model.Applicant;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ApplicantRepostory extends BaseRepository<Applicant,Long> {

    List<Applicant> findAllByYearsOfExperience(Long yearsOfExperience);

    Applicant findFirstByEmail(String email);

    List<Applicant> findAllByDateOfBirthGreaterThanEqual(Date dateOfBirth);

    List<Applicant> findAllByDateOfBirthLessThanEqual(Date dateOfBirth);

    List<Applicant> findAllByDateOfBirthBetween(Date startDate, Date endDate);

}
