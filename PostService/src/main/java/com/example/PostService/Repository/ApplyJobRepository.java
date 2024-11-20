package com.example.PostService.Repository;


import com.example.PostService.Entity.ApplyJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyJobRepository extends MongoRepository<ApplyJob, String> {
    @Query("Select a from ApplyJob a Where a.userId=:userId")
    Page<ApplyJob> findAllByUserId(String userId, Pageable pageable);

}
