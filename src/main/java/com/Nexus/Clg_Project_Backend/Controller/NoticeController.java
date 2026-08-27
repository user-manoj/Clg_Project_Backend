package com.Nexus.Clg_Project_Backend.Controller;

import com.Nexus.Clg_Project_Backend.DTO.NoticeResponseDTO;
import com.Nexus.Clg_Project_Backend.Model.NoticeEntity;
import com.Nexus.Clg_Project_Backend.Service.NoticeService;
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
public class NoticeController {

   @Autowired
   private NoticeService noticeService;

   @GetMapping("/notice")
    public List<NoticeResponseDTO> getAllNotices(){
        return noticeService.getAllNotices();
    }

    @GetMapping("/notice/{id}/file")
    public ResponseEntity<byte[]> getNoticeFile(@PathVariable Long id){
        NoticeEntity notice = noticeService.getNoticeWithFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(notice.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + notice.getFileName() + "\"")
                .body(notice.getFileData());
    }

//    @PostMapping("/notice")
//    public NoticeEntity createNotice(@RequestParam String title, @RequestParam String description, @RequestParam MultipartFile file) throws IOException {
//        return noticeService.createNotice(title, description, file);
//    }

    @PostMapping("/notice")
    public NoticeEntity createNotice(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws IOException {
        return noticeService.createNotice(title, description, file);
    }

    @PutMapping("/notice/")
    public ResponseEntity<NoticeEntity> updateNotice(@RequestBody NoticeEntity noticeEntity){
       noticeService.updateNotice(noticeEntity);
       return ResponseEntity.ok(noticeEntity);
    }

    @DeleteMapping("/notice/{ID}")
    public ResponseEntity<String> deleteNotice(@PathVariable Integer ID){
        return noticeService.deleteNotice(ID);
    }
}
