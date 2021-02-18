package com.iri.movietickets.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator
        implements ConstraintValidator<CustomEmailConstraint, String> {
    private static final String EMAIL_PATTERN = ".+@.+\\..+";

    @Override
    public boolean isValid(String emailField,
                           ConstraintValidatorContext validatorContext) {
        return emailField != null && emailField.matches(EMAIL_PATTERN)
                && (emailField.length() > 4);
    }
}
