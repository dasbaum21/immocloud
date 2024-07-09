package com.example.immocloud.service;

import com.example.immocloud.entities.ApplicantEntity;
import com.example.immocloud.entities.enums.Status;
import com.example.immocloud.exeptions.ObjectPersistenceExeption;
import com.example.immocloud.repositories.IApplicantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApplicantService {
    @Autowired
    private IApplicantRepository applicantRepository;

    public ApplicantEntity saveApplicant(ApplicantEntity applicantEntity) {
        log.info("Saving new applicant {} to database", applicantEntity);
        try {
            applicantEntity.setId(null);
            return applicantRepository.save(applicantEntity);
        } catch (Exception e) {
            throw new ObjectPersistenceExeption(e.getMessage());
        }
    }

    public List<ApplicantEntity> findApplicantsByName(String name) {
        log.info("Finding applicants by name {}", name);
        return applicantRepository.findApplicantEntitiesByNameContains(name);
    }

    public List<ApplicantEntity> findApplicants() {
        log.info("Finding applicants");
        return applicantRepository.findAll();
    }

    public List<ApplicantEntity> findApplicantsByStatus(Status status) {
        log.info("Finding applicants by status {}", status);
        return applicantRepository.findApplicantEntitiesByStatus(status);
    }

    public ApplicantEntity updateApplicantStatus(String id, ApplicantEntity applicantEntity) {
        log.info("Updating status of applicant with id {} to {}", id, applicantEntity);
        Optional<ApplicantEntity> applicantOpt = applicantRepository.findById(id);

        if (applicantOpt.isPresent()) {
            ApplicantEntity applicant = applicantOpt.get();
            applicant.setStatus(applicantEntity.getStatus());
            ApplicantEntity updatedApplicant = applicantRepository.save(applicant);
            log.info("Updated applicant: {}", updatedApplicant);
            return updatedApplicant;
        } else {
            throw new ObjectPersistenceExeption("Applicant with id " + id + " not found");
        }
    }
}
