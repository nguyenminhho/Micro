package com.example.PostService.Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyJobResponse {
    private String id;
    private String userId;
    private String jobId;
//    private Long status;
    private LocalDateTime createdAt;
}
