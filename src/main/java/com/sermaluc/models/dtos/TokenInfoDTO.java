package com.sermaluc.models.dtos;

import lombok.Data;
import java.util.Date;

@Data
public class TokenInfoDTO {

    private String token;

    private Date expirationDate;

}
