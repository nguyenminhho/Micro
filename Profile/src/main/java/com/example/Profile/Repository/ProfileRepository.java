package com.example.Profile.Repository;


import com.example.Profile.Entity.Company;
import com.example.Profile.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    @Query("Select p From Profile p where p.userId = :userId")
    Profile getProfileByUserId(String userId);


}
