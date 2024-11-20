package com.example.PostService.Mapper;



import com.example.PostService.Dto.Response.ApplyJobResponse;
import com.example.PostService.Entity.ApplyJob;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplyJobMapper {
    //    @Mapping(target = "userId", ignore = true)
//    @Mapping(target = "jobId", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    ApplyJob toApplyJob(ApplyJobRequest applyJobRequest);
    ApplyJobResponse toApplyJobResponse(ApplyJob applyJob);


//    @Mapping(target = "id", ignore = true)  // Giữ lại id cũ
//    ApplyJob toUpdateApplyJob(ApplyJobRequest applyJobRequest, @MappingTarget ApplyJob applyJob);
//    default ApplyJob updateApplyJobFromRequest(ApplyJobRequest applyJobRequest, @MappingTarget ApplyJob applyJob) {
//        if (applyJobRequest.getUserId() != null) {
//            applyJob.setUserId(applyJobRequest.getUserId());
//        }
//        if (applyJobRequest.getJobId() != null) {
//            applyJob.setJobId(applyJobRequest.getJobId());
//        }
//        if (applyJobRequest.getStatus() != null) {
//            applyJob.setStatus(applyJobRequest.getStatus());
//        }
//
//        return applyJob;
//    }

}
