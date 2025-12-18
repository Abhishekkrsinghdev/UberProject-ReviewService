package com.example.UberReviewService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDto {

    private String content;

    private double rating;

    private Long bookingId;

}
