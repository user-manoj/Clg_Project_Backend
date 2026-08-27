package com.Nexus.Clg_Project_Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesResponseDTO {
    private Long id;
    private String title;
    private String subject;
    private String description;
    private String fileName;
    private String uploadedBy;
    private LocalDate uploadedOn;
}
