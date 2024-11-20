package com.example.DACN.Repository.feign;

import com.example.DACN.Dto.Request.ProfileRequest;
import com.example.DACN.Dto.Response.ProfileResponse;
import com.example.DACN.configuration.AddTokenInFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.print.attribute.standard.Media;
import java.awt.*;

@FeignClient(name = "profile", url = "http://localhost:8081", configuration = {AddTokenInFeignClient.class})
public interface ProfileRepository{
    @PostMapping(value = "/profile/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileResponse addProfile(@RequestBody ProfileRequest profileRequest);
}
