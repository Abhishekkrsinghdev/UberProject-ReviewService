package com.example.UberReviewService.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String content;
    private Double rating;
    private Long booking;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
