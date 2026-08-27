package com.Nexus.Clg_Project_Backend.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class AnswerRequestDTO {
    private Map<Long, Integer> answers; // questionId -> the option index the student picked
}