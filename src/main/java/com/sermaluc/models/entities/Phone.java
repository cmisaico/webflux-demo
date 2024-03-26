package com.sermaluc.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
