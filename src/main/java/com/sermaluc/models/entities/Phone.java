package com.sermaluc.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;
}
