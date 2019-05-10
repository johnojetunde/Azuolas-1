package com.teamseven.repository;

import com.teamapt.base.lib.repository.BaseRepository;
import com.teamseven.model.ApplicantStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantStatusRepository extends BaseRepository<ApplicantStatus,Long> {
    ApplicantStatus findByApplicant_Email(String email);
}
