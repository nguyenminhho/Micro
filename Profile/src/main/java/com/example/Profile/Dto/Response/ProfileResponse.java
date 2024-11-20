package com.example.Profile.Dto.Response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private String id;
    private String userId;
    private String firstName;
    private String lastName;
    private String city;
    private Long age;
    private LocalDateTime date;
}
