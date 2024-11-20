package com.example.Notifications.Service;

import com.example.Notifications.Dto.EmailRequest;
import com.example.Notifications.Dto.EmailResponse;
import com.example.Notifications.Dto.SendEmailRequest;
import com.example.Notifications.Dto.Sender;
import com.example.Notifications.Repository.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    EmailSend emailSend;

    @Value("${apiKey}")
    protected String apiKey;

    public EmailResponse sendEmail(SendEmailRequest sendEmailRequest){
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .email("hn9875388@gmail.com")
                        .name("MinhHoang")
                        .build())
                .to(List.of(sendEmailRequest.getTo()))
                .subject(sendEmailRequest.getSubject())
                .htmlContent(sendEmailRequest.getHtmlContent())
                .build();

        return emailSend.email(apiKey,emailRequest);
    }

}
