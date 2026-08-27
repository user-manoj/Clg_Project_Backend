package com.Nexus.Clg_Project_Backend.Controller;


import com.Nexus.Clg_Project_Backend.DTO.*;
import com.Nexus.Clg_Project_Backend.DTO.Test.QuestionResponseDTO;
import com.Nexus.Clg_Project_Backend.DTO.Test.ResultResponseDTO;
import com.Nexus.Clg_Project_Backend.DTO.Test.TestRequestDTO;
import com.Nexus.Clg_Project_Backend.DTO.Test.TestResponseDTO;
import com.Nexus.Clg_Project_Backend.Model.TestEntity;
import com.Nexus.Clg_Project_Backend.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http://localhost:5173")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    public ResponseEntity<TestEntity> createTest(@RequestBody TestRequestDTO requestDTO){
        TestEntity testEntity = testService.createTest(requestDTO);
        return ResponseEntity.ok(testEntity);
    }

    @GetMapping
    public ResponseEntity<List<TestResponseDTO>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }


    @GetMapping("/mine")
    public ResponseEntity<List<TestResponseDTO>> getAllTestsForLecturer() {
        return ResponseEntity.ok(testService.getAllTestsForLecturer());
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestions(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getQuestionsForStudent(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TestEntity> conductTest(@PathVariable Long id) {
        return ResponseEntity.ok(testService.conductTest(id));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<ResultResponseDTO> submitAnswers(
            @PathVariable Long id,
            @RequestBody AnswerRequestDTO request) {
        return ResponseEntity.ok(testService.submitAnswers(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
        String result = testService.deleteTest(id);

        if (result.equals("Success")) {
            return ResponseEntity.ok("Successfully deleted test");
        }

        return ResponseEntity.notFound().build();
    }
}
