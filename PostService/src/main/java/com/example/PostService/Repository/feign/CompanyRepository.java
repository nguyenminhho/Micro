package com.example.PostService.Repository.feign;


import com.example.PostService.Dto.Response.PostJobResponse;
import com.example.PostService.configuration.AddTokenInFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "company", url = "http://localhost:8081", configuration = {AddTokenInFeignClient.class})
public interface CompanyRepository {
    @GetMapping(value = "/company/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    PostJobResponse getCompanyByUserId(@PathVariable("userId") String userId);
}
//@FeignClient(name = "postJob", url = "http://localhost:8082",configuration = AddTokenInFeignClient.class)
//public interface PostJobRepository {
//    @GetMapping(value = "/postJob/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    PostJobResponse getPost(@PathVariable("userId") String id);
//}
