package com.example.PostService.Dto.Request;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class PostJobRequest {
    private String id;
    private String companyId;
    private String position;
    private String description;
    private LocalDateTime createdAt;
}
