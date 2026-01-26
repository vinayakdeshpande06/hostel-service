package com.cdac.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.hostel.client.AuthServiceClient;
import com.cdac.hostel.model.Hostel;
import com.cdac.hostel.model.HostelStatus;
import com.cdac.hostel.repository.HostelRepository;

@Service
public class HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private AuthServiceClient authClient;

    public Hostel createHostel(Hostel hostel, Long userId) {
        if (!authClient.userExists(userId)) {
            throw new RuntimeException("User not found");
        }
        return hostelRepository.save(hostel);
    }

    public List<Hostel> getPendingHostels() {
        return hostelRepository.findByStatus(HostelStatus.PENDING);
    }
    
    public List<Hostel> getApprovedHostels() {
        return hostelRepository.findByStatus(HostelStatus.APPROVED);
    }
    
    //This is Like Updating operation of CRUD
    public Hostel approveHostel(Long hostelId) {
    	System.out.println("Entered HostelService approveHostel Method");
        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new RuntimeException("Hostel not found"));

        hostel.setStatus(HostelStatus.APPROVED);
        return hostelRepository.save(hostel);
    }

    
    //This is Like Updating operation of CRUD
    // 🔹 ADMIN: reject hostel
    public Hostel rejectHostel(Long hostelId, String reason) {
        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new RuntimeException("Hostel not found"));

        hostel.setStatus(HostelStatus.REJECTED);
        // optional: hostel.setRejectionReason(reason);
        return hostelRepository.save(hostel);
    }
    
}
