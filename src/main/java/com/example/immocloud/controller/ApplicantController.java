package com.example.immocloud.controller;

import com.example.immocloud.entities.ApplicantEntity;
import com.example.immocloud.entities.enums.Status;
import com.example.immocloud.service.ApplicantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applicant")
@Tag(name = "Applicant")
public class ApplicantController {
    private final ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<ApplicantEntity> saveApplicant(@RequestBody ApplicantEntity applicantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicantService.saveApplicant(applicantRequest));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ApplicantEntity>> getApplicant(@PathVariable("name") String name) {
        return ResponseEntity.ok().body(applicantService.findApplicantsByName(name));
    }

    @GetMapping
    public ResponseEntity<List<ApplicantEntity>> getApplicants() {
        return ResponseEntity.ok().body(applicantService.findApplicants());
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicantEntity>> getApplicantsByStatus(@PathVariable("status") Status status) {
        return ResponseEntity.ok().body(applicantService.findApplicantsByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicantEntity> updateApplicantStatus(@PathVariable("id") String id, @RequestBody ApplicantEntity applicantEntity) {
        ApplicantEntity updatedApplicant = applicantService.updateApplicantStatus(id, applicantEntity);
        return ResponseEntity.ok(updatedApplicant);
    }
}
