package com.telusko.quizapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionTitleValidator implements ConstraintValidator<com.telusko.quizapp.validation.QuestionTitleValid, String> {

    private static final String ALPHA_SPACE = "^[A-Za-z ]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        ctx.disableDefaultConstraintViolation();

        if (value == null || value.trim().isEmpty()) {
            ctx.buildConstraintViolationWithTemplate("Question cannot be blank").addConstraintViolation();
            return false;
        }
        if (!value.matches(ALPHA_SPACE)) {
            ctx.buildConstraintViolationWithTemplate("Question must be in alphabetical order").addConstraintViolation();
            return false;
        }
        if (value.length() < 50) {
            ctx.buildConstraintViolationWithTemplate("Question must contain more than 50 letters").addConstraintViolation();
            return false;
        }
        return true;
    }
}