package com.example.UberReviewService;

import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.controllers.ReviewController;
import com.example.UberReviewService.dtos.CreateReviewDto;
import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {
    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Mock
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReview_Success(){
        long reviewId=1L;
        ReviewDto mockReview= ReviewDto.builder().build();
        mockReview.setId(reviewId);

        when(reviewService.getReview(reviewId)).thenReturn(mockReview);

        ResponseEntity<ReviewDto> response=reviewController.getReview(reviewId);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        ReviewDto returnedReview=(ReviewDto) response.getBody();

        assertEquals(reviewId,returnedReview.getId());
    }

    @Test
    public void testGetReview_NOTFOUND(){
        long reviewId=1L;
        ReviewDto mockReview= ReviewDto.builder().build();
        mockReview.setId(reviewId);

        when(reviewService.getReview(reviewId)).thenReturn(null);

        ResponseEntity<ReviewDto> response=reviewController.getReview(reviewId);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testPublishReview_Success(){
        CreateReviewDto requestDto=new CreateReviewDto();
        Booking booking=new Booking();
        booking.setId(1L);
        requestDto.setBookingId(booking.getId());

        Review incomingReview=Review.builder()
                .content("Test reiew content")
                .rating(4.5)
                .booking(booking)
                .build();

        when(createReviewDtoToReviewAdapter.convertDto(requestDto)).thenReturn(incomingReview);

        ReviewDto reviewDto=ReviewDto.builder()
                        .content(incomingReview.getContent())
                        .booking(incomingReview.getBooking().getId())
                        .rating(incomingReview.getRating())
                        .build();

        when(reviewService.publishReview(incomingReview)).thenReturn(reviewDto);

        ResponseEntity<?> response=reviewController.publishReview(requestDto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }
}
