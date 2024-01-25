package com.sermaluc.valids;

import com.sermaluc.valids.impl.ClaveValidaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClaveValidaValidator.class)
public @interface ClaveValida {

    String message() default "Formato de clave incorrecto";

    String pattern() default ".*";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}