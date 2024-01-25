package com.sermaluc.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sermaluc.models.TokenInfoDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static String secretKey = "s3rm41uc";
    private static Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public TokenInfoDTO create(String username){
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
        tokenInfoDTO.setExpirationDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)));
        tokenInfoDTO.setToken(JWT.create()
                .withSubject(username)
                .withIssuer("sermaluc")
                .withIssuedAt(new Date())
                .withExpiresAt(tokenInfoDTO.getExpirationDate())
                .sign(algorithm));

        return tokenInfoDTO;
    }
}
