package com.Nexus.Clg_Project_Backend.Controller;

import com.Nexus.Clg_Project_Backend.DTO.OldQuestionPaperDTO;
import com.Nexus.Clg_Project_Backend.Model.NotesEntity;
import com.Nexus.Clg_Project_Backend.Model.OldQuestionPaperEntity;
import com.Nexus.Clg_Project_Backend.Service.OldQuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class OldQuestionPaperController {

    @Autowired
    OldQuestionPaperService oldQPService;

    @PostMapping("old-question-papers/upload")
    public ResponseEntity<OldQuestionPaperEntity> uploadQuestionPaper(@RequestParam String title,
                                                                      @RequestParam String subject,
                                                                      @RequestParam String description,
                                                                      @RequestParam String year,
                                                                      @RequestParam MultipartFile file) throws IOException {

        OldQuestionPaperEntity saved = oldQPService.createQP(title, subject, description, year, file);
        return ResponseEntity.ok().body(saved);

    }

    @GetMapping("old-question-papers")
    public ResponseEntity<List<OldQuestionPaperDTO>> getAllQuestionPapers(@RequestParam(required = false) String subject) {

        if (subject == null || subject.equals("") || subject.equals("All")) {
            List<OldQuestionPaperDTO> oldQPDTO = oldQPService.getAllQP();
            return ResponseEntity.ok().body(oldQPDTO);
        }else {
            List<OldQuestionPaperDTO> oldQPDTO = oldQPService.getAllQPBySubject(subject);
            return ResponseEntity.ok().body(oldQPDTO);
        }
    }


    @DeleteMapping("/old-question-papers/{id}")
    public ResponseEntity<String> deleteQuestionPaper(@PathVariable Long id) {
        String res = oldQPService.deleteQP(id);

        if (res.equals("Success")) {
            return ResponseEntity.ok("Success");
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("old-question-papers/{id}/file")
    public ResponseEntity<byte[]> getQuestionPaperFile(@PathVariable Long id) {
        OldQuestionPaperEntity oldQP = oldQPService.getQPwithfile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(oldQP.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + oldQP.getFileName() + "\"")
                .body(oldQP.getFileData());
    }
}
