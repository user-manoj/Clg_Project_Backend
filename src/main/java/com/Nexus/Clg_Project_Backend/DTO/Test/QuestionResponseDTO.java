package com.Nexus.Clg_Project_Backend.DTO.Test;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponseDTO {
    private Long id;
    private String question;
    private List<String> options;
}
