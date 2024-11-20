package com.example.DACN.Repository.feign;

import com.example.DACN.Dto.Request.CompanyRequest;
import com.example.DACN.Dto.Request.ProfileRequest;
import com.example.DACN.Dto.Response.CompanyResponse;
import com.example.DACN.Dto.Response.ProfileResponse;
import com.example.DACN.configuration.AddTokenInFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "company", url = "http://localhost:8081", configuration = {AddTokenInFeignClient.class})
public interface CompanyRepository {
    @PostMapping(value = "/company/add", produces = MediaType.APPLICATION_JSON_VALUE)
    CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest);
}
