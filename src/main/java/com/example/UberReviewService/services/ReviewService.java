package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public ReviewService(ReviewRepository reviewRepository,BookingRepository bookingRepository){
        this.reviewRepository=reviewRepository;
        this.bookingRepository=bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("*************");
//        Review r= Review
//                .builder()
//                .content("Amazing ride quality")
//                .rating(5.0)
//                .build();
//        Booking b=Booking
//                .builder()
//                .review(r)
//                .build();
//
//        bookingRepository.save(b);
       // reviewRepository.save(r);
        Optional<Booking> b=bookingRepository.findById(2L);
        if(b.isPresent()){
            bookingRepository.delete(b.get());
        }
    }
}
