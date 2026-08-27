package com.Nexus.Clg_Project_Backend.Service;

import com.Nexus.Clg_Project_Backend.DTO.NoticeResponseDTO;
import com.Nexus.Clg_Project_Backend.Model.NoticeEntity;
import com.Nexus.Clg_Project_Backend.Repo.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepo;


    public List<NoticeResponseDTO> getAllNotices(){

        List<NoticeEntity> notices = noticeRepo.findAll();
        List<NoticeResponseDTO> Responsedto = new ArrayList<>();

        for (NoticeEntity n : notices){
            
            NoticeResponseDTO dto1 = new NoticeResponseDTO();
            dto1.setNoticeId(n.getNoticeId());
            dto1.setNoticeTitle(n.getNoticeTitle());
            dto1.setDescription(n.getDescription());
            dto1.setFileName(n.getFileName());
            dto1.setPostedOn(n.getPostedOn());
            
            Responsedto.add(dto1);
        }

        return Responsedto;

    }

    public NoticeEntity createNotice(String title, String description, MultipartFile file) throws IOException {
            NoticeEntity notice = NoticeEntity.builder()
                    .noticeTitle(title)
                    .description(description)
                    .fileName(file.getOriginalFilename())
                    .fileData(file.getBytes())
                    .fileType(file.getContentType())
                    .postedOn(LocalDate.now())
                    .build();
        return noticeRepo.save(notice);
    }


    //Update
    public NoticeEntity updateNotice(@RequestBody NoticeEntity noticeEntity){
        return noticeRepo.save(noticeEntity);
    }

    //Delete
    public ResponseEntity<String> deleteNotice(@PathVariable Integer ID){

        NoticeEntity notice = noticeRepo.findById(Long.valueOf(ID)).orElse(null);

        if(notice != null){
            noticeRepo.delete(notice);
            return ResponseEntity.ok("Notice Deleted");
        }else {
            return ResponseEntity.notFound().build();
        }


    }

    public NoticeEntity getNoticeWithFile(Long id){
        return noticeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found: " + id));
    }

}
