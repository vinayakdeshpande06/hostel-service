package com.cdac.hostel.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.hostel.client.AuthServiceClient;
import com.cdac.hostel.dto.MultiCriteriaRatingRequest;
import com.cdac.hostel.model.HostelRating;
import com.cdac.hostel.repository.HostelRatingRepository;

@Service
public class HostelRatingService {

    @Autowired
    private HostelRatingRepository ratingRepository;

    @Autowired
    private AuthServiceClient authClient;

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
    
    
    public List<HostelRating> getRatingsByHostel(Long hostelId) {
        return ratingRepository.findByHostelId(hostelId);
    }
}
