package com.telusko.quizapp.Model;

import com.telusko.quizapp.validation.QuestionTitleValid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private static Validator validator;

    static class TestQuestion {
        @QuestionTitleValid
        @NotNull
        String questionTitle;

        TestQuestion(String questionTitle) {
            this.questionTitle = questionTitle;
        }
    }

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validQuestionTitle() {
        TestQuestion q = new TestQuestion("This is a valid question title with more than fifty letters in it");
        Set violations = validator.validate(q);
        assertTrue(violations.isEmpty());
    }

    @Test
    void blankQuestionTitle() {
        TestQuestion q = new TestQuestion("   ");
        Set violations = validator.validate(q);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nullQuestionTitle() {
        TestQuestion q = new TestQuestion(null);
        Set violations = validator.validate(q);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shortQuestionTitle() {
        TestQuestion q = new TestQuestion("Short title");
        Set violations = validator.validate(q);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nonAlphabeticalQuestionTitle() {
        TestQuestion q = new TestQuestion("Question 123 with numbers!");
        Set violations = validator.validate(q);
        assertFalse(violations.isEmpty());
    }
}
