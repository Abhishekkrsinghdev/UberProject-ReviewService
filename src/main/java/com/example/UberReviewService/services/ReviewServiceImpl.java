package com.example.UberReviewService.services;

import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.dtos.UpdateReviewDto;
import com.example.UberReviewService.mapper.ReviewMapper;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository,ReviewMapper reviewMapper){
        this.reviewRepository=reviewRepository;
        this.reviewMapper=reviewMapper;
    }

    @Override
    public ReviewDto publishReview(Review review) {
        return this.reviewMapper.toDto(this.reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getReviews() {
        return this.reviewMapper.toDtoList(this.reviewRepository.findAll());
    }

    @Override
    public ReviewDto getReview(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElse(null);
    }

    @Override
    public Boolean deleteReview(Long id) {
        if(reviewRepository.existsById(id)){
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public ReviewDto updateReview(Long id, UpdateReviewDto updateReviewDto) {
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    // Update the entity fields
                    existingReview.setContent(updateReviewDto.getContent());
                    existingReview.setRating(updateReviewDto.getRating());
                    // No need to call .save() manually if using @Transactional
                    return reviewMapper.toDto(existingReview);
                })
                .orElse(null);
    }
}
