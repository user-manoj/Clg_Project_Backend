package com.Nexus.Clg_Project_Backend.Controller;

import com.Nexus.Clg_Project_Backend.DTO.NotesResponseDTO;
import com.Nexus.Clg_Project_Backend.Model.NotesEntity;
import com.Nexus.Clg_Project_Backend.Service.NotesService;
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
public class NotesController {

    @Autowired
    private NotesService notes;

    @PostMapping("/notes/upload")
    public ResponseEntity<NotesEntity> uploadNotes(@RequestParam String title,
                                                   @RequestParam String subject,
                                                   @RequestParam String description,
                                                   @RequestParam MultipartFile file) throws IOException {

        NotesEntity saved = notes.createNotes(title, subject, description, file);
        return ResponseEntity.ok().body(saved);

    }

    @GetMapping("/notes")
    public ResponseEntity<List<NotesResponseDTO>> getAllNotes(@RequestParam(required = false) String subject) {

        if (subject == null || subject.equals("All")) {
            List<NotesResponseDTO> list = notes.getAllNotes();
            return ResponseEntity.ok().body(list);
        }

        List<NotesResponseDTO> notesDto = notes.getAllNotesBySubject(subject);
        return ResponseEntity.ok().body(notesDto);
    }


    @DeleteMapping("/notes/{id}")
    public ResponseEntity<String> deleteNotes(@PathVariable Long id) {
        String res = notes.deleteNotes(id);

        if (res.equals("Success")) {
            return ResponseEntity.ok(res);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/notes/{id}/file")
    public ResponseEntity<byte[]> getNoteFile(@PathVariable Long id) {
        NotesEntity note = notes.getNoteWithFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(note.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + note.getFileName() + "\"")
                .body(note.getFileData());
    }
}
