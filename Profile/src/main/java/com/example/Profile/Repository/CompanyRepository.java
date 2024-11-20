package com.example.Profile.Repository;


import com.example.Profile.Entity.Company;
import com.example.Profile.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query("Select c From Company c where c.userId = :userId")
    Company getCompanyByUserId(String userId);



}
