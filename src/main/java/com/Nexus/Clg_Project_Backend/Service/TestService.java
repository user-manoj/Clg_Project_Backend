package com.Nexus.Clg_Project_Backend.Service;

import com.Nexus.Clg_Project_Backend.DTO.*;
import com.Nexus.Clg_Project_Backend.DTO.Test.*;
import com.Nexus.Clg_Project_Backend.Model.QuestionEntity;
import com.Nexus.Clg_Project_Backend.Model.TestEntity;
import com.Nexus.Clg_Project_Backend.Repo.QuestionRepository;
import com.Nexus.Clg_Project_Backend.Repo.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepo;

    @Autowired
    private QuestionRepository questionRepo;

    public TestEntity createTest(TestRequestDTO requestDTO) {
        TestEntity testEntity = TestEntity.builder()
                                .title(requestDTO.getTitle())
                                .description(requestDTO.getQuestions().size() + " MCQs")
                                .subject(requestDTO.getSubject())
                                .durationMin(requestDTO.getDurationMin())
                                .totalQuestions(requestDTO.getQuestions().size())
                                .createdBy("Lecturer")
                                .status("DRAFT")
                                .build();

        TestEntity savedTest = testRepo.save(testEntity);

        for(QuestionRequestDTO q : requestDTO.getQuestions()) {
            QuestionEntity questionEntity = QuestionEntity.builder()
                    .question(q.getQuestion())
                    .optionA(q.getOptions().get(0))
                    .optionB(q.getOptions().get(1))
                    .optionC(q.getOptions().get(2))
                    .optionD(q.getOptions().get(3))
                    .answer(q.getAnswer())
                    .test(savedTest)
                    .build();

            questionRepo.save(questionEntity);
        }

        return savedTest;
    }


    public List<TestResponseDTO> getAllTests() {
        List<TestEntity> tests = testRepo.findAllByStatus("AVAILABLE");
        return convertToDto(tests);
    }

    public List<TestResponseDTO> getAllTestsForLecturer() {
        List<TestEntity> tests = testRepo.findAll();
        return convertToDto(tests);
    }

    private List<TestResponseDTO> convertToDto(List<TestEntity> tests) {
        List<TestResponseDTO> list = new ArrayList<>();

        for (TestEntity t : tests) {
            TestResponseDTO dto = new TestResponseDTO();
            dto.setId(t.getId());
            dto.setSubject(t.getSubject());
            dto.setTitle(t.getTitle());
            dto.setDescription(t.getDescription());
            dto.setDurationMinutes(t.getDurationMin());
            dto.setTotalQuestions(t.getTotalQuestions());
            dto.setCreatedBy(t.getCreatedBy());
            dto.setStatus(t.getStatus());
            list.add(dto);
        }

        return list;
    }

    public List<QuestionResponseDTO> getQuestionsForStudent(Long testId) {
        List<QuestionEntity> questions = questionRepo.findAllByTestId(testId);
        List<QuestionResponseDTO> dtoList = new ArrayList<>();

        for (QuestionEntity q : questions) {
            QuestionResponseDTO dto = new QuestionResponseDTO();
                    dto.setId(q.getId());
                    dto.setQuestion(q.getQuestion());
                    dto.setOptions(List.of(q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()));

                    dtoList.add(dto);
        }

        return dtoList;
    }

    public ResultResponseDTO submitAnswers(Long testId, AnswerRequestDTO requestDTO) {

        List<QuestionEntity> questions = questionRepo.findAllByTestId(testId);

        Map<Long, Integer> answers = requestDTO.getAnswers();


        int score = 0;
        int total = questions.size();

        for (QuestionEntity q : questions) {
            Integer stdentPick =  answers.get(q.getId());

            if(stdentPick != null && stdentPick.equals(q.getAnswer())) {
                score++;
            }
        }

        return new ResultResponseDTO(score, total);

    }

    public TestEntity conductTest(Long testId) {
        TestEntity testEntity = testRepo.findById(testId)
                                .orElseThrow(() -> new RuntimeException("Test not found"));

        testEntity.setStatus("AVAILABLE");
        return testRepo.save(testEntity);
    }

    public String deleteTest(Long testId) {
        TestEntity test =  testRepo.findById(testId).orElse(null);

        if(test == null) {
            return "Not Found";
        }
        else {
            List<QuestionEntity> questions = questionRepo.findAllByTestId(testId);
            questionRepo.deleteAll(questions);

            testRepo.delete(test);
            return "Success";
        }
    }
}
