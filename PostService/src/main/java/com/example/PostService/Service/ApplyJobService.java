package com.example.PostService.Service;



import com.example.PostService.Dto.Response.ApplyJobResponse;
import com.example.PostService.Entity.ApplyJob;
import com.example.PostService.Mapper.ApplyJobMapper;
import com.example.PostService.Repository.ApplyJobRepository;
import com.example.PostService.Repository.feign.ProfileRepository;
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
public class ApplyJobService {
    @Autowired
    ApplyJobRepository applyJobRepository;
    @Autowired
    ApplyJobMapper applyJobMapper;

    @Autowired
    PostJobService postJobService;

    @Autowired
    ProfileRepository profileRepository;

        public ApplyJobResponse addApplyJob(String jobId) throws Exception {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.getAuthorities().stream().anyMatch(roles -> roles.getAuthority().equals("SCOPE_USER"))){
                throw new Exception("No role to apply job");
            };

            var profile = profileRepository.getProfileByUserId(authentication.getName());

            if(postJobService.getPost(jobId) == null){
                throw new Exception("Post is empty");
            }
            //Check xem co post do khong
            ApplyJob applyJob = ApplyJob.builder()
                    .userId(profile.getId())
                    .jobId(jobId)

                    .createdAt(LocalDateTime.now())
                    .build();
            return applyJobMapper.toApplyJobResponse(applyJobRepository.save(applyJob));
        }

    public List<ApplyJobResponse> findAllApplyJob() {
        return applyJobRepository.findAll().stream().map(a -> applyJobMapper.toApplyJobResponse(a)).toList();
    }


    public List<ApplyJobResponse> findAllByUserId(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Sort sort = Sort.by("createdAt").descending();
            Pageable pageable = PageRequest.of(page-1, size, sort);
            var applyJobs = applyJobRepository.findAllByUserId(authentication.getName(),pageable);

        return applyJobs.stream().map(a -> applyJobMapper.toApplyJobResponse(a)).toList();
    }


//    public Boolean actionApply(String applyJobId,Long status) throws Exception {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(!authentication.getAuthorities().stream().anyMatch(roles -> roles.getAuthority().equals("SCOPE_ADMIN"))){
//                throw new Exception("Not role to active");
//            }
//
//            Optional<ApplyJob> applyJob = applyJobRepository.findById(applyJobId);
//       if(applyJob.isEmpty()){
//           throw new Exception("ApplyJob is Empty");
//       }
//
//        applyJob.get().setStatus(status);
//
//        applyJobRepository.save(applyJob.get());
//
//        return true;
//    }
}
