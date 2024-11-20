package com.example.PostService.Controller;


import com.example.PostService.Dto.Response.ApplyJobResponse;
import com.example.PostService.Service.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ApplyJobController {

    @Autowired
    private ApplyJobService applyJobService;

    @PostMapping("/applyJob/add/{jobId}")
    public ApplyJobResponse addApplyJob(@PathVariable String jobId) throws Exception {
        return applyJobService.addApplyJob(jobId);
    }

    @GetMapping("/applyJob/getAll")
    public List<ApplyJobResponse> allApplyJob() {
        return applyJobService.findAllApplyJob();
    }

    @GetMapping("/applyJob/getAllByUser/{id}")
    public List<ApplyJobResponse> allApplyJobByUser(
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "1") int size) {
        return applyJobService.findAllByUserId(page, size);
    }

//    @PutMapping("/applyJob/activeStatus/{applyJobId}")
//    public Boolean activeStatus(@PathVariable  String applyJobId,@RequestParam Long status) throws Exception {
//        return applyJobService.actionApply(applyJobId,status);
//    }

}
