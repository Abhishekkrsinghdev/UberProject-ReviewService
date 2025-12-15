package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="booking_review")
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends Base{
    @Column(nullable = false)
    private String content;

    private Double rating;
}
