package com.example.immocloud.repositories;

import com.example.immocloud.entities.ApplicantEntity;
import com.example.immocloud.entities.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IApplicantRepository extends MongoRepository<ApplicantEntity, String> {
    List<ApplicantEntity> findApplicantEntitiesByNameContains(String name);
    List<ApplicantEntity> findApplicantEntitiesByStatus(Status status);
}

