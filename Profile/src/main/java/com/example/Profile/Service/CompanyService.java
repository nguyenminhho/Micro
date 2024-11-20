package com.example.Profile.Service;


import com.example.Profile.Dto.Request.CompanyRequest;
import com.example.Profile.Dto.Request.ProfileRequest;
import com.example.Profile.Dto.Response.CompanyResponse;
import com.example.Profile.Dto.Response.ProfileResponse;
import com.example.Profile.Entity.Company;
import com.example.Profile.Entity.Profile;
import com.example.Profile.Mapper.CompanyMapper;
import com.example.Profile.Mapper.ProfileMapper;
import com.example.Profile.Repository.CompanyRepository;
import com.example.Profile.Repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private CompanyMapper companyMapper;
    public CompanyResponse addCompany(CompanyRequest companyRequest) throws Exception{
        Company company = companyMapper.toCompany(companyRequest);
        company.setDate(LocalDateTime.now());
        return companyMapper.toCompanyResponse(companyRepository.save(company));
    }

    public List<CompanyResponse> getAllCompany(){
        return companyRepository.findAll().stream().map(companyMapper::toCompanyResponse).toList();
    }

    public CompanyResponse getCompany(String id){
        return companyMapper.toCompanyResponse(companyRepository.findById(id).get());
    }

    public CompanyResponse getCompanyByUserId(String userId){
        return companyMapper.toCompanyResponse(companyRepository.getCompanyByUserId(userId));
    }

    public CompanyResponse updateCompany(String id, CompanyRequest companyRequest) throws Exception {
        Company company = companyRepository.findById(id).get();
        company = companyMapper.updateCompanyFromRequest(companyRequest,company);
        return companyMapper.toCompanyResponse(   companyRepository.save(company));
    }

    public Boolean removeCompany(String id){
        companyRepository.deleteById(id);
        return true;
    }


}
