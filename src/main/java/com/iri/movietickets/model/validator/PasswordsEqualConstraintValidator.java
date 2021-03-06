package com.iri.movietickets.model.validator;

import com.iri.movietickets.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, Object> {
    @Override
    public boolean isValid(Object user, ConstraintValidatorContext validatorContext) {
        UserRequestDto userRequestDto = (UserRequestDto) user;
        return userRequestDto.getPassword() != null
                && userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword());
    }
}
