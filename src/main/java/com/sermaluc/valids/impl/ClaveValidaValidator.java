package com.sermaluc.valids.impl;

import com.sermaluc.config.ClaveValidaProperties;
import com.sermaluc.valids.ClaveValida;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ClaveValidaValidator implements ConstraintValidator<ClaveValida, String> {

    private String pattern;

    private final ClaveValidaProperties claveValidaProperties;

    @Autowired
    ClaveValidaValidator(ClaveValidaProperties claveValidaProperties) {
        this.claveValidaProperties = claveValidaProperties;
    }

    @Override
    public void initialize(ClaveValida constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(claveValidaProperties.getClaveRegex() != null && !claveValidaProperties.getClaveRegex().isEmpty()
            && !claveValidaProperties.getClaveRegex().equalsIgnoreCase("0")){
            return value != null && value.matches(claveValidaProperties.getClaveRegex());
        } else {
            return value != null && value.matches(pattern);
        }
    }
}
