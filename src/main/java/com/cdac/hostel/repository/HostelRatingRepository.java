package com.cdac.hostel.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.hostel.model.HostelRating;

public interface HostelRatingRepository
        extends JpaRepository<HostelRating, Long> {

    Optional<HostelRating> findByHostelIdAndUserId(Long hostelId, Long userId);

    List<HostelRating> findByHostelId(Long hostelId);

    long countByHostelId(Long hostelId);
}
