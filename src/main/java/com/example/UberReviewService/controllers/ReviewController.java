package com.example.UberReviewService.controllers;
import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.dtos.CreateReviewDto;
import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/reviews")
@RestController
public class ReviewController {
    private final ReviewService reviewService;
    private final CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter){
        this.reviewService=reviewService;
        this.createReviewDtoToReviewAdapter=createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request){
        Review incomingReview=this.createReviewDtoToReviewAdapter.convertDto(request);
        if(incomingReview==null){
            return new ResponseEntity<>("Invalid arguments",HttpStatus.BAD_REQUEST);
        }
        Review review=this.reviewService.publishReview(incomingReview);
        ReviewDto response=ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .booking(review.getBooking().getId())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
