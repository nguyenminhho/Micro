package com.example.PostService.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ApplyJob {
    @MongoId
    private String id;
    private String userId;
    private String jobId;
    //0. da nop 1. da xem 2.phu hop 3.khong phu hop
//    private Long status;
    private LocalDateTime createdAt;
}
