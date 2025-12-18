package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    
}
