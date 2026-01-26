package com.cdac.hostel.service;

import org.springframework.stereotype.Service;

@Service
public class HostelRankingService {

    private static final double GLOBAL_AVERAGE = 3.5;
    private static final int MIN_RATINGS = 5;

    public double calculateWeightedRating(double avg, long count) {
        return (avg * count + GLOBAL_AVERAGE * MIN_RATINGS)
                / (count + MIN_RATINGS);
    }
}

