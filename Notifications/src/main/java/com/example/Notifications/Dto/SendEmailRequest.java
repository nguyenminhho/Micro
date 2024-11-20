package com.example.Notifications.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SendEmailRequest {
    private To to;
    private String htmlContent;
    private String subject;
}
