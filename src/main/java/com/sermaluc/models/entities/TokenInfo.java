package com.sermaluc.models.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Builder
public class TokenInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
