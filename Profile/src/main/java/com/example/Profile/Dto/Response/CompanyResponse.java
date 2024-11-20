package com.example.Profile.Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private String id;
    private String userId;
    private String nameC;
    private String location;
    private String typeC;
    private LocalDateTime date;

}
