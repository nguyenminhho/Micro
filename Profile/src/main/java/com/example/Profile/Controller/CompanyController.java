package com.example.Profile.Controller;


import com.example.Profile.Dto.Request.CompanyRequest;
import com.example.Profile.Dto.Request.ProfileRequest;
import com.example.Profile.Dto.Response.CompanyResponse;
import com.example.Profile.Dto.Response.ProfileResponse;
import com.example.Profile.Service.CompanyService;
import com.example.Profile.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/company/add")
     ResponseEntity<CompanyResponse> addProfile(@RequestBody CompanyRequest companyRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(companyService.addCompany(companyRequest));
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/company/all")
     ResponseEntity<List<CompanyResponse>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompany());
    }


    @GetMapping("/company/{id}")
    ResponseEntity<CompanyResponse> getProfile(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany(id));
    }

    @GetMapping("/company/user/{userId}")
    ResponseEntity<CompanyResponse> getCompanyByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanyByUserId(userId));
    }
//
//
    @PutMapping("/company/update/{id}")
    public ResponseEntity<CompanyResponse> updateUser(@PathVariable  String id, @RequestBody CompanyRequest companyRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id,companyRequest));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.removeCompany(id));
    }



}
