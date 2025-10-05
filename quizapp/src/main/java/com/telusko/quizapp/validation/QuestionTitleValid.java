package com.telusko.quizapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuestionTitleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionTitleValid {
    String message() default "Invalid question title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}