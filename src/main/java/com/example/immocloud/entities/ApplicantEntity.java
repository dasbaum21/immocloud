package com.example.immocloud.entities;

import com.example.immocloud.entities.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ApplicantEntity {
    @Id
    private String id;
    private String name;
    private Status status = Status.OPEN;
}
