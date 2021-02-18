package com.iri.movietickets.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator
        implements ConstraintValidator<CustomEmailConstraint, String> {

    @Override
    public boolean isValid(String emailField,
                           ConstraintValidatorContext cxt) {
        return emailField != null && emailField.matches(".+@.+\\..+")
                && (emailField.length() > 4);
    }
}
