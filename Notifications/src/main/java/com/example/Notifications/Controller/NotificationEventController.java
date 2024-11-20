package com.example.Notifications.Controller;

import com.example.Notifications.Dto.SendEmailRequest;
import com.example.Notifications.Dto.To;
import com.example.Notifications.Service.EmailService;
import com.example.event.dto.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventController {

    @Autowired
    EmailService emailService;
    @KafkaListener(topics = "notification-success")
    public void sendEmailToRegisterSuccess(NotificationEvent notificationEvent){
        emailService.sendEmail(SendEmailRequest.builder()
                        .to(To.builder()
                                .email(notificationEvent.getRecipient())
                                .name("MinhHoang")
                                .build())
                        .subject(notificationEvent.getSubject())
                        .htmlContent(notificationEvent.getBody())
                .build());
    }
}
