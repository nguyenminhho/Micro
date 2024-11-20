package com.example.DACN.Dto.Response;


import com.example.DACN.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private Long type;
//    private String password;
    private Set<Roles> roles;
}
