package com.example.PostService.Controller;

import com.example.PostService.Dto.Request.PostJobRequest;
import com.example.PostService.Dto.Response.PostJobResponse;
import com.example.PostService.Entity.PostJob;
import com.example.PostService.Repository.PostJobRepository;
import com.example.PostService.Service.PostJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping()
public class PostJobController {

    @Autowired
    private PostJobService postJobService;


    @PostMapping("/postJob/add")
    public PostJobResponse addPost(@RequestBody PostJobRequest postJobRequest) throws Exception {
        return postJobService.addPost(postJobRequest);
    }

    @GetMapping("/postJob/getAll")
    public List<PostJobResponse> allPost() {
        return postJobService.findAllPost();
    }
    @GetMapping("/postJob/getById/{id}")
    public PostJobResponse getPost(@PathVariable String id) {
        return postJobService.getPost(id);
    }
    @GetMapping("/postJob/{userId}")
    public List<PostJobResponse> getAll(@PathVariable String userId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "2") int size
                                        ) {
        return postJobService.findAllByUserId(userId, page, size);
    }
//    @GetMapping("/postJob/getAll/{userId}")
//    public List<PostJobResponse> gatAllPost(@PathVariable String userId) {
//        return postJobService.findAllPostByUserId(userId);
//    }

    @PutMapping("/postJob/update/{id}")
    public PostJobResponse updatePost(@PathVariable String id, @RequestBody PostJobRequest postJobRequest) {
        System.out.println(postJobRequest);
        return postJobService.updatePost(id,postJobRequest);
    }
    @DeleteMapping("/postJob/delete/{id}")
    public Boolean deletePost(@PathVariable String id) {
        return postJobService.deletePost(id);
    }
}
