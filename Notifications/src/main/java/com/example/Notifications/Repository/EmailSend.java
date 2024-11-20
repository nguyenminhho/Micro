package com.example.Notifications.Repository;


import com.example.Notifications.Dto.EmailRequest;
import com.example.Notifications.Dto.EmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "sendEmail" , url = "https://api.brevo.com")
public interface EmailSend {

    @PostMapping(value = "/v3/smtp/email", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailResponse email(@RequestHeader("api-key") String apiKey, @RequestBody EmailRequest emailRequest);
}
