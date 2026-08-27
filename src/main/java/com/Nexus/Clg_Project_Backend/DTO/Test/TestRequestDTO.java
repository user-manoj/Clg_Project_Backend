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
public class TestRequestDTO {

    private String title;
    private String subject;
    private Integer durationMin;
    private List<QuestionRequestDTO> questions;
}
