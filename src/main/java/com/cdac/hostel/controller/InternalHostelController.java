package com.cdac.hostel.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdac.hostel.model.Hostel;
import com.cdac.hostel.service.HostelService;

@RestController
@RequestMapping("/api/internal/hostels")
public class InternalHostelController {

    @Autowired
    private HostelService hostelService;

    // 1. Get all pending hostels (ADMIN)
	
	  @GetMapping("/pending") public List<Hostel> getPendingHostels() { return
	  hostelService.getPendingHostels(); }
	 

    // 2. Approve hostel
    @PutMapping("/{hostelId}/approve")
    public Hostel approveHostel(@PathVariable Long hostelId) {
        return hostelService.approveHostel(hostelId);
    }

    // 3. Reject hostel
    @PutMapping("/{hostelId}/reject")
    public Hostel rejectHostel(
            @PathVariable Long hostelId,
            @RequestParam(required = false) String reason) {

        return hostelService.rejectHostel(hostelId, reason);
    }
}

