package com.Nexus.Clg_Project_Backend.Service;

import com.Nexus.Clg_Project_Backend.DTO.OldQuestionPaperDTO;
import com.Nexus.Clg_Project_Backend.Model.OldQuestionPaperEntity;
import com.Nexus.Clg_Project_Backend.Repo.OldQuestionPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OldQuestionPaperService {

    @Autowired
    private OldQuestionPaperRepository oldQPRepo;

    public OldQuestionPaperEntity createQP(String title, String subject, String description, String year, MultipartFile file) throws IOException {
        OldQuestionPaperEntity oldQP = OldQuestionPaperEntity.builder()
                .title(title)
                .subject(subject)
                .description(description)
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileData(file.getBytes())
                .year(Integer.parseInt(year))
                .uploadedBy("Lecturer")
                .uploadedOn(LocalDate.now())
                .build();

        return oldQPRepo.save(oldQP);
    }

    public List<OldQuestionPaperDTO> getAllQP() {
        List<OldQuestionPaperEntity> oldQP = oldQPRepo.findAll();

        List<OldQuestionPaperDTO> oldQPDTO = new ArrayList<>();

        for (OldQuestionPaperEntity n : oldQP) {
            OldQuestionPaperDTO dto = new OldQuestionPaperDTO();
            dto.setId(n.getId());
            dto.setSubject(n.getSubject());
            dto.setTitle(n.getTitle());
            dto.setDescription(n.getDescription());
            dto.setYear(n.getYear());
            dto.setFileName(n.getFileName());
            dto.setUploadedBy(n.getUploadedBy());
            dto.setUploadedOn(n.getUploadedOn());

            oldQPDTO.add(dto);
        }

        return oldQPDTO;
    }

    public List<OldQuestionPaperDTO> getAllQPBySubject(String subject) {
        List<OldQuestionPaperEntity> oldQP = oldQPRepo.findAllBySubject(subject);

        List<OldQuestionPaperDTO> oldQPDTO = new ArrayList<>();

        for (OldQuestionPaperEntity n : oldQP) {
            OldQuestionPaperDTO dto = new OldQuestionPaperDTO();
            dto.setId(n.getId());
            dto.setSubject(n.getSubject());
            dto.setTitle(n.getTitle());
            dto.setDescription(n.getDescription());
            dto.setYear(n.getYear());
            dto.setFileName(n.getFileName());
            dto.setUploadedBy(n.getUploadedBy());
            dto.setUploadedOn(n.getUploadedOn());

            oldQPDTO.add(dto);
        }

        return oldQPDTO;
    }

    public String deleteQP(Long id) {
        OldQuestionPaperEntity oldQP = oldQPRepo.findById(id).orElse(null);
        if (oldQP != null) {
            oldQPRepo.delete(oldQP);
            return "Success";
        }
        return "Fail";
    }


    public OldQuestionPaperEntity getQPwithfile(Long id) {
        return  oldQPRepo.findById(id).orElse(null);
    }
}
