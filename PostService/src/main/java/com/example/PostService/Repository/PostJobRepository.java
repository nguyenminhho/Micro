package com.example.PostService.Repository;

import com.example.PostService.Entity.PostJob;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJobRepository extends MongoRepository<PostJob, String> {
    @Query("Select p from Post p Where p.companyId=:companyId")
    Page<PostJob> findAllByUserId(String companyId, Pageable pageable);
//    @Query("{ 'userId': ?0 }")
//    List<PostJob> findAllPostByUserId(String userId);
}
