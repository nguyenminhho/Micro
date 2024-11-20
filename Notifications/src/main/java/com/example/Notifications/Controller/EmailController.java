package com.example.Notifications.Controller;

import com.example.Notifications.Dto.EmailResponse;
import com.example.Notifications.Dto.SendEmailRequest;
import com.example.Notifications.Dto.To;
import com.example.Notifications.Service.EmailService;
import com.example.event.dto.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class EmailController {

    @Autowired
    EmailService emailService;
    @PostMapping("/email/send")
    public EmailResponse sentEmail(@RequestBody SendEmailRequest sendEmailRequest){
        System.out.println(sendEmailRequest);
        System.out.println("1");
        return emailService.sendEmail(sendEmailRequest);
    }

}
