package com.example.immocloud.models;

import com.example.immocloud.entities.enums.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApplicantRequest {
    private String firstName;
    private String lastName;
    private Status status;
}
