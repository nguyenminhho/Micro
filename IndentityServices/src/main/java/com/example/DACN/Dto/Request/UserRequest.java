package com.example.DACN.Dto.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String id;
    private String username;
    private String password;
    private String email;
    private Long type;

    private String firstName;
    private String lastName;
    private String city;
    private Long age;

    private String nameC;
    private String location;
    private String typeC;
    private Set<String> roles;
}
