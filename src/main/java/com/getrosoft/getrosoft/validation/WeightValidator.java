package com.getrosoft.getrosoft.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WeightValidator implements ConstraintValidator<ValidWeight, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) return true; // Allow null for optional fields
        String stringValue = String.valueOf(value);
        return stringValue.matches("^\\d{1,3}(\\.\\d{1,2})?$");
    }
}
