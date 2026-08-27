package com.Nexus.Clg_Project_Backend.Repo;

import com.Nexus.Clg_Project_Backend.Model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    List<TestEntity> findAllByStatus(String status);
}
