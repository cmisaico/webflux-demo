package com.sermaluc.models;

import lombok.Data;
import java.util.Date;

@Data
public class TokenInfoDTO {

    private String token;

    private Date expirationDate;

}
