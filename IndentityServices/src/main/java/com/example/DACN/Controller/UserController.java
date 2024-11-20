package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.AddPasswordRequest;
import com.example.DACN.Dto.Request.UserRequest;
import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @PostMapping("/user/add")
     ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) throws Exception {

        System.out.println(userRequest);

        return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(userRequest));
    }

    @PostMapping("/user/addPassword")
    ResponseEntity<Void> addPassword(@RequestBody AddPasswordRequest addPasswordRequest) throws Exception {
        userService.addPassword(addPasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/user/all")
     ResponseEntity<List<UserResponse>> getAllUser() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }


    @GetMapping("/user/my-info")
    ResponseEntity<UserResponse> currentUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMyInfo());
    }


    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable  String id, @RequestBody UserRequest userRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,userRequest));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(id));
    }



}
