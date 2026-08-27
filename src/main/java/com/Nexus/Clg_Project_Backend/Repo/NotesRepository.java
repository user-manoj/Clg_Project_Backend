package com.Nexus.Clg_Project_Backend.Repo;

import com.Nexus.Clg_Project_Backend.Model.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity,Long> {
    List<NotesEntity> findAllBySubject(String subject);
}
