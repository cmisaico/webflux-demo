package com.sermaluc.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
