package com.Nexus.Clg_Project_Backend.DTO.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {

    private String question;
    private List<String> options;
    private Integer answer;

}
