package com.example.Profile.Controller;


import com.example.Profile.Dto.Request.ProfileRequest;
import com.example.Profile.Dto.Response.ProfileResponse;
import com.example.Profile.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile/add")
     ResponseEntity<ProfileResponse> addProfile(@RequestBody ProfileRequest profileRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(profileService.addProfile(profileRequest));
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/profile/all")
     ResponseEntity<List<ProfileResponse>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getAllUser());
    }


    @GetMapping("/profile/{id}")
    ResponseEntity<ProfileResponse> getProfile(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfile(id));
    }

    @GetMapping("/profile/user/{userId}")
    ResponseEntity<ProfileResponse> getProfileByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileByUserId(userId));
    }
//
//
//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<UserResponse> updateUser(@PathVariable  String id, @RequestBody UserRequest userRequest) throws Exception {
//
//        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,userRequest));
//    }
//
//    @DeleteMapping("/deleteUser/{id}")
//    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
//        return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(id));
//    }



}
