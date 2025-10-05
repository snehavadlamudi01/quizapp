package com.telusko.quizapp.Model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    @Test
    void testQuizFields() {
        Quiz quiz = new Quiz();
        quiz.setId(1);
        quiz.setTitle("Sample Quiz");

        Question q1 = new Question();
        q1.setId(101);
        Question q2 = new Question();
        q2.setId(102);

        List<Question> questions = Arrays.asList(q1, q2);
        quiz.setQuestions(questions);

        assertEquals(1, quiz.getId());
        assertEquals("Sample Quiz", quiz.getTitle());
        assertEquals(2, quiz.getQuestions().size());
        assertEquals(101, quiz.getQuestions().get(0).getId());
        assertEquals(102, quiz.getQuestions().get(1).getId());
    }

    @Test
    void testEmptyQuestionsList() {
        Quiz quiz = new Quiz();
        quiz.setQuestions(null);
        assertNull(quiz.getQuestions());
    }
}
