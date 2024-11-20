package com.example.event.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NotificationEvent {
    private String channel; //kenh gui: email,...
    private String recipient; // nguoi nhan
    private String template; //mau email, mau thong bao..
    Map<String, Object> param;
    private String subject;
    private String body;

}
