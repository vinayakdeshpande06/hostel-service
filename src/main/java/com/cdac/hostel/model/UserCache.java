package com.cdac.hostel.model;

 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserCache {

    @Id
    private Long userId;

    private String username;
    private String email;
}
