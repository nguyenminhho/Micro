package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.ProfileRequest;
import com.example.DACN.Dto.Request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileRequest toProfileRequest(UserRequest userRequest);


//    ProfileResponse toProfileResponse(Profile profile);







}
