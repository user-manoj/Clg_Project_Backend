package com.Nexus.Clg_Project_Backend.Service;

import com.Nexus.Clg_Project_Backend.DTO.NotesResponseDTO;
import com.Nexus.Clg_Project_Backend.Model.NotesEntity;
import com.Nexus.Clg_Project_Backend.Repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepo;

    public NotesEntity createNotes(String title, String subject, String description, MultipartFile file) throws IOException {
        NotesEntity notes = NotesEntity.builder()
                            .title(title)
                            .subject(subject)
                            .description(description)
                            .fileName(file.getOriginalFilename())
                            .fileType(file.getContentType())
                            .fileData(file.getBytes())
                            .uploadedBy("Lecturer")
                            .uploadedOn(LocalDate.now())
                            .build();

        return notesRepo.save(notes);
    }

    public List<NotesResponseDTO> getAllNotes() {
            List<NotesEntity> notes = notesRepo.findAll();
            List<NotesResponseDTO> list = new ArrayList<>();

            for (NotesEntity n : notes) {
                NotesResponseDTO dto = new NotesResponseDTO();

                dto.setId(n.getId());
                dto.setTitle(n.getTitle());
                dto.setSubject(n.getSubject());
                dto.setDescription(n.getDescription());
                dto.setFileName(n.getFileName());
                dto.setUploadedBy(n.getUploadedBy());
                dto.setUploadedOn(n.getUploadedOn());

                list.add(dto);
            }

            return list;
    }


    public List<NotesResponseDTO> getAllNotesBySubject(String subject) {
        List<NotesEntity> notes = notesRepo.findAllBySubject(subject);
        List<NotesResponseDTO> list = new ArrayList<>();

        for (NotesEntity n : notes) {
            NotesResponseDTO dto = new NotesResponseDTO();

            dto.setId(n.getId());
            dto.setTitle(n.getTitle());
            dto.setSubject(n.getSubject());
            dto.setDescription(n.getDescription());
            dto.setFileName(n.getFileName());
            dto.setUploadedBy(n.getUploadedBy());
            dto.setUploadedOn(n.getUploadedOn());

            list.add(dto);
        }

        return list;
    }

    public String deleteNotes(Long id) {
        NotesEntity notes = notesRepo.findById(id).orElse(null);

        if (notes != null) {
            notesRepo.delete(notes);
            return "Success";
        }

        return "Fail";

    }


    public NotesEntity getNoteWithFile(Long id) {
        return notesRepo.findById(id).orElse(null);
    }
}
