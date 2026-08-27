package com.Nexus.Clg_Project_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OldQuestionPaperDTO {
    private Long id;
    private String subject;
    private String title;
    private String description;
    private Integer year;
    private String fileName;
    private String uploadedBy;
    private LocalDate uploadedOn;

}
