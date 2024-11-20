package com.example.Profile.Mapper;


import com.example.Profile.Dto.Request.ProfileRequest;
import com.example.Profile.Dto.Response.ProfileResponse;
import com.example.Profile.Entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "date",ignore = true)
    Profile toProfile(ProfileRequest profileRequest);


    ProfileResponse toProfileResponse(Profile profile);
    Profile updateProfile(ProfileRequest profileRequest,@MappingTarget Profile profile);

    @Mapping(target = "id",ignore = true)
    default Profile updateProfileFromRequest(ProfileRequest profileRequest, @MappingTarget Profile profile) {
        if (profileRequest.getUserId() != null) {
            profile.setUserId(profileRequest.getUserId());
        }
        if (profileRequest.getFirstName() != null) {
            profile.setFirstName(profileRequest.getFirstName());
        }
        if (profileRequest.getLastName() != null) {
            profile.setLastName(profileRequest.getLastName());
        }
        if (profileRequest.getCity() != null) {
            profile.setCity(profileRequest.getCity());
        }
        if (profileRequest.getAge() != null) {
            profile.setAge(profileRequest.getAge());
        }
        if (profileRequest.getDate() != null) {
            profile.setDate(profileRequest.getDate());
        }

        return profile;
    }




}
