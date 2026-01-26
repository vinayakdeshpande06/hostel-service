package com.cdac.hostel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.hostel.model.Hostel;
import com.cdac.hostel.service.HostelService;

@RestController
@RequestMapping("/api/hostels")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @PostMapping
    public Hostel createHostel(
            @RequestBody Hostel hostel,
            @RequestParam Long userId) {

        return hostelService.createHostel(hostel, userId);
    }

    @GetMapping
    public List<Hostel> getApprovedHostels() {
        return hostelService.getApprovedHostels();
    }
}
