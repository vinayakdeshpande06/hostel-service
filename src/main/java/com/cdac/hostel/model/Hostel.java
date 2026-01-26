package com.cdac.hostel.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostelId;

    private String hostelName;
    private String description;
    private String address;
    private String city;
    private String locality;

    private BigDecimal monthlyRentMin;
    private BigDecimal monthlyRentMax;

    private Boolean hasWifi;
    private Boolean hasAc;
    private Boolean hasMess;
    private Boolean hasLaundry;

    private String contactPersonName;
    private String contactPersonPhone;

    @Enumerated(EnumType.STRING)
    private HostelStatus status = HostelStatus.PENDING;
}
