package com.example.UberReviewService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings;
}
