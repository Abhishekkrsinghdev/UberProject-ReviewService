package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Review review;

    @Enumerated(value= EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;
}
