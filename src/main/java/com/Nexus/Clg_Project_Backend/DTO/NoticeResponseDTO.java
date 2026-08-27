package com.Nexus.Clg_Project_Backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDTO {

    private Long noticeId;

    private String noticeTitle;

    private String description;

    private String fileName;

    private LocalDate postedOn;


}
