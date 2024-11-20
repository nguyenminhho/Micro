package com.example.PostService.Mapper;

import com.example.PostService.Dto.Request.PostJobRequest;
import com.example.PostService.Dto.Response.PostJobResponse;
import com.example.PostService.Entity.PostJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostJobMapper {
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PostJob toPostJob(PostJobRequest postJobRequest);
    PostJobResponse toPostJobResponse(PostJob postJob);


    @Mapping(target = "id", ignore = true)  // Giữ lại id cũ
    PostJob toUpdatePostJob(PostJobRequest postJobRequest, @MappingTarget PostJob postJob);
    default PostJob updatePostJobFromRequest(PostJobRequest postJobRequest, @MappingTarget PostJob postJob) {
        if (postJobRequest.getCompanyId() != null) {
            postJob.setCompanyId(postJobRequest.getCompanyId());
        }
        if (postJobRequest.getPosition() != null) {
            postJob.setPosition(postJobRequest.getPosition());
        }
        if (postJobRequest.getDescription() != null) {
            postJob.setDescription(postJobRequest.getDescription());
        }

        if (postJobRequest.getCreatedAt() != null) {
            postJob.setCreatedAt(postJobRequest.getCreatedAt());
        }
        return postJob;
    }

}
