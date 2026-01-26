package com.cdac.hostel.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.hostel.client.AuthServiceClient;
import com.cdac.hostel.dto.MultiCriteriaRatingRequest;
import com.cdac.hostel.dto.RatingDTO;
import com.cdac.hostel.model.HostelRating;
import com.cdac.hostel.repository.HostelRatingRepository;

@Service
public class HostelRatingService {

    @Autowired
    private HostelRatingRepository ratingRepository;

    @Autowired
    private AuthServiceClient authClient;

    
    //1.Rating a Hostel (POST Operation)
    public HostelRating rateHostel(
            Long hostelId, Long userId, MultiCriteriaRatingRequest req) {

        if (!authClient.userExists(userId)) {
            throw new RuntimeException("User not found");
        }

        ratingRepository.findByHostelIdAndUserId(hostelId, userId)
            .ifPresent(r -> {
                throw new RuntimeException("User already rated this hostel");
            });

        HostelRating rating = new HostelRating();
        rating.setHostelId(hostelId);
        rating.setUserId(userId);

        rating.setCleanlinessRating(req.getCleanlinessRating());
        rating.setFoodQualityRating(req.getFoodQualityRating());
        rating.setSafetyRating(req.getSafetyRating());
        rating.setLocationRating(req.getLocationRating());
        rating.setAffordabilityRating(req.getAffordabilityRating());
        rating.setReviewText(req.getReviewText());

        return ratingRepository.save(rating);
    }
    
    
    
    
    //2.Get All Ratings of Hostel
    public List<HostelRating> getRatingsByHostel(Long hostelId) {
        return ratingRepository.findByHostelId(hostelId);
    }
    
    
    //3.Get Summary Rating/Average of a Hostel
    
    public RatingDTO getRatingSummary(Long hostelId) {

        List<HostelRating> ratings = ratingRepository.findByHostelId(hostelId);

        RatingDTO dto = new RatingDTO();
        dto.setHostelId(hostelId);

        if (ratings == null || ratings.isEmpty()) {
            dto.setOverallRating(0.0);
            return dto;
        }

        double cleanlinessSum = 0;
        double foodQualitySum = 0;
        double safetySum = 0;
        double locationSum = 0;
        double affordabilitySum = 0;

        int count = ratings.size();

        for (HostelRating rating : ratings) {
            cleanlinessSum += rating.getCleanlinessRating();
            foodQualitySum += rating.getFoodQualityRating();
            safetySum += rating.getSafetyRating();
            locationSum += rating.getLocationRating();
            affordabilitySum += rating.getAffordabilityRating();
        }

        double cleanlinessAvg = cleanlinessSum / count;
        double foodQualityAvg = foodQualitySum / count;
        double safetyAvg = safetySum / count;
        double locationAvg = locationSum / count;
        double affordabilityAvg = affordabilitySum / count;

        double overallAvg =
                (cleanlinessAvg +
                 foodQualityAvg +
                 safetyAvg +
                 locationAvg +
                 affordabilityAvg) / 5;

        
		
		   
          dto.setCleanlinessRating(cleanlinessAvg);
		  dto.setFoodQualityRating(foodQualityAvg); 
		  dto.setSafetyRating(safetyAvg);
		  dto.setLocationRating(locationAvg); 
		  dto.setAffordabilityRating(affordabilityAvg);
		 

        dto.setOverallRating(overallAvg);

        return dto;
    }

}
