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
public class PostJob {
    @MongoId
    private String id;
    private String companyId;
    private String position;
    private String description;
    private LocalDateTime createdAt;
}
