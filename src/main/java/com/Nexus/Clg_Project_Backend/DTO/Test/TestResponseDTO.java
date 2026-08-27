package com.Nexus.Clg_Project_Backend.DTO.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResponseDTO {
    private Long id;
    private String subject;
    private String title;
    private String description;
    private Integer durationMinutes;
    private Integer totalQuestions;
    private String createdBy;
    private String status;
}