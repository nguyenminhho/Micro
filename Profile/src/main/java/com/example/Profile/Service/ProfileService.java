package com.example.Profile.Service;


import com.example.Profile.Dto.Request.ProfileRequest;
import com.example.Profile.Dto.Response.ProfileResponse;
import com.example.Profile.Entity.Profile;
import com.example.Profile.Mapper.ProfileMapper;
import com.example.Profile.Repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;


    @Autowired
    private ProfileMapper profileMapper;
    public ProfileResponse addProfile(ProfileRequest profileRequest) throws Exception{
        System.out.println(profileRequest.getUserId());
        Profile profile = profileMapper.toProfile(profileRequest);
        profile.setDate(LocalDateTime.now());
        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }



    public List<ProfileResponse> getAllUser(){
        return profileRepository.findAll().stream().map(profileMapper::toProfileResponse).toList();
    }

    public ProfileResponse getProfile(String id){
        return profileMapper.toProfileResponse(profileRepository.findById(id).get());
    }

    public ProfileResponse getProfileByUserId(String userId){
        return profileMapper.toProfileResponse(profileRepository.getProfileByUserId(userId));
    }
//
//
//    ///con chua sua role khi update co role thay doi
//    public UserResponse updateUser(String id, UserRequest userRequest) throws Exception {
//        User user = userRepository.findById(id).get();
//
//        user.setId( userRequest.getId() );
//        user.setPassword( userRequest.getPassword() );
//        user.setName( userRequest.getName() );
//        user.setAge( userRequest.getAge() );
//
//        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//
//        return userMapper.toUserResponse(userRepository.save(user));
//    }
//
//
//
//
//    public Boolean removeUser(String id){
//        userRepository.deleteById(id);
//
//        return true;
//    }


}
