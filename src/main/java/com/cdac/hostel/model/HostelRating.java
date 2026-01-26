package com.cdac.hostel.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(
  uniqueConstraints = @UniqueConstraint(columnNames = {"hostelId", "userId"})
)
public class HostelRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private Long hostelId;
    private Long userId;

    private Integer cleanlinessRating;
    private Integer foodQualityRating;
    private Integer safetyRating;
    private Integer locationRating;
    private Integer affordabilityRating;

    private String reviewText;
}
