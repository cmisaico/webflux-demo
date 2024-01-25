package com.sermaluc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClaveValidaProperties {

    @Value(value = "${user.password.regexp:0}")
    private String claveRegex;

}