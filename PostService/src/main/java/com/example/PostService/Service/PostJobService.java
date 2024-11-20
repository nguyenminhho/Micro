package com.example.PostService.Service;

import com.example.PostService.Dto.Request.PostJobRequest;
import com.example.PostService.Dto.Response.PostJobResponse;
import com.example.PostService.Entity.PostJob;

import com.example.PostService.Mapper.PostJobMapper;
import com.example.PostService.Repository.PostJobRepository;
import com.example.PostService.Repository.feign.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostJobService {
    @Autowired
    PostJobRepository postJobRepository;
    @Autowired
    PostJobMapper postJobMapper;

    @Autowired
    CompanyRepository companyRepository;

        public PostJobResponse addPost(PostJobRequest postJobRequest) throws Exception {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.getAuthorities().stream().anyMatch(roles -> roles.getAuthority().equals("SCOPE_COMPANY"))){
                throw new Exception("No role to add Post");
            };

            var company = companyRepository.getCompanyByUserId(authentication.getName());

            PostJob postJob = postJobMapper.toPostJob(postJobRequest);
            postJob.setCreatedAt(LocalDateTime.now());
            postJob.setCompanyId(company.getId());

            return postJobMapper.toPostJobResponse(postJobRepository.save(postJob));
        }

    public List<PostJobResponse> findAllPost() {
        return postJobRepository.findAll().stream().map(p -> postJobMapper.toPostJobResponse(p)).toList();
    }

    public PostJobResponse getPost(String id) {
        return postJobMapper.toPostJobResponse(postJobRepository.findById(id).get());
    }
    public List<PostJobResponse> findAllByUserId(String companyId,int page, int size) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page-1, size,sort);

        var listPage = postJobRepository.findAllByUserId(companyId,pageable);

        return listPage.stream().map(p -> postJobMapper.toPostJobResponse(p)).toList();
    }

//    public List<PostJobResponse> findAllPostByUserId(String userId) {
//        return postJobRepository.findAllPostByUserId(userId).stream().map(p -> postJobMapper.toPostJobResponse(p)).toList();
//    }

    public PostJobResponse updatePost(String id, PostJobRequest postJobRequest) {
            PostJob postJob = postJobRepository.findById(id).get();
        PostJob updatePost = postJobMapper.updatePostJobFromRequest(postJobRequest,postJob);
        postJobRepository.save(updatePost);
        return postJobMapper.toPostJobResponse(updatePost);
    }

    public Boolean deletePost(String id) {
      postJobRepository.deleteById(id);
        return true;
    }




}
