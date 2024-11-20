package com.example.PostService.Dto.Response;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class PostJobResponse {
    private String id;
    private String companyId;
    private String position;
    private String description;
    private LocalDateTime createdAt;
}
