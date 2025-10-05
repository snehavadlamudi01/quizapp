package com.telusko.quizapp.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionTitleValidatorTest {

    private static Validator validator;

    static class TestDTO {
        @QuestionTitleValid
        @NotNull
        String title;

        TestDTO(String title) {
            this.title = title;
        }
    }

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validTitle() {
        TestDTO dto = new TestDTO("This is a valid question title with more than fifty letters in it");
        Set<ConstraintViolation<TestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void blankTitle() {
        TestDTO dto = new TestDTO("   ");
        Set<ConstraintViolation<TestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nullTitle() {
        TestDTO dto = new TestDTO(null);
        Set<ConstraintViolation<TestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nonAlphabeticalTitle() {
        TestDTO dto = new TestDTO("Question 123 with numbers and symbols!");
        Set<ConstraintViolation<TestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shortTitle() {
        TestDTO dto = new TestDTO("Short title");
        Set<ConstraintViolation<TestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
