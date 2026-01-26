package com.cdac.hostel.dto;

 
import lombok.Data;

@Data
public class RatingDTO {

    private Long ratingId;
    private Long hostelId;
    private Long userId;

    private Integer cleanlinessRating;
    private Integer foodQualityRating;
    private Integer safetyRating;
    private Integer locationRating;
    private Integer affordabilityRating;

    private Double overallRating;
    private String reviewText;
}
