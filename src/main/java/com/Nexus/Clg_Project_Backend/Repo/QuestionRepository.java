package com.Nexus.Clg_Project_Backend.Repo;

import com.Nexus.Clg_Project_Backend.Model.QuestionEntity;
import com.Nexus.Clg_Project_Backend.Model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    List<QuestionEntity> findAllByTestId(Long testId);

}
