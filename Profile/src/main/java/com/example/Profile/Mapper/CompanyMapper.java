package com.example.Profile.Mapper;


import com.example.Profile.Dto.Request.CompanyRequest;
import com.example.Profile.Dto.Request.ProfileRequest;
import com.example.Profile.Dto.Response.CompanyResponse;
import com.example.Profile.Dto.Response.ProfileResponse;
import com.example.Profile.Entity.Company;
import com.example.Profile.Entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(target = "date",ignore = true)
    Company toCompany(CompanyRequest companyRequest);


    CompanyResponse toCompanyResponse(Company company);
    Company updateCompany(CompanyRequest companyRequest,@MappingTarget Company company);

    @Mapping(target = "id",ignore = true)
    default Company updateCompanyFromRequest(CompanyRequest companyRequest,@MappingTarget Company company) {
        if (companyRequest.getUserId() != null) {
            company.setUserId(companyRequest.getUserId());
        }
        if (companyRequest.getNameC() != null) {
            company.setNameC(companyRequest.getNameC());
        }
        if (companyRequest.getLocation() != null) {
            company.setLocation(companyRequest.getLocation());
        }
        if (companyRequest.getTypeC() != null) {
            company.setTypeC(companyRequest.getTypeC());
        }
        if (companyRequest.getDate() != null) {
            company.setDate(companyRequest.getDate());
        }

        return company;
    }






}
