package com.example.DACN.Mapper;

import com.example.DACN.Dto.Request.CompanyRequest;
import com.example.DACN.Dto.Request.ProfileRequest;
import com.example.DACN.Dto.Request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyRequest toCompanyRequest(UserRequest userRequest);









}
