package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    @Override
    public Review publishReview(Review review) {
        return this.reviewRepository.save(review);
    }
}
