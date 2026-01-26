package com.cdac.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.hostel.model.UserCache;

public interface UserCacheRepository
        extends JpaRepository<UserCache, Long> {
}
