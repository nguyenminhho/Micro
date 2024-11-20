package com.example.DACN.Dto.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String userId;
    private String nameC;
    private String location;
    private String typeC;
    private LocalDateTime date;

}
