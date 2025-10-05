package com.telusko.quizapp.Service;

import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.dao.QuestionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestionsSuccess() {
        List<Question> questions = Arrays.asList(new Question());
        when(questionDao.findAll()).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionService.getAllQuestions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questions, response.getBody());
    }

    @Test
    void testGetAllQuestionsException() {
        when(questionDao.findAll()).thenThrow(new RuntimeException());

        ResponseEntity<List<Question>> response = questionService.getAllQuestions();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testGetQuestionsByCategorySuccess() {
        List<Question> questions = Arrays.asList(new Question());
        when(questionDao.findByCategory("Java")).thenReturn(questions);

        ResponseEntity<List<Question>> response = questionService.getQuestionsByCategory("Java");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questions, response.getBody());
    }

    @Test
    void testGetQuestionsByCategoryException() {
        when(questionDao.findByCategory("Java")).thenThrow(new RuntimeException());

        ResponseEntity<List<Question>> response = questionService.getQuestionsByCategory("Java");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testAddQuestion() {
        Question question = new Question();
        ResponseEntity<String> response = questionService.addQuestion(question);

        verify(questionDao, times(1)).save(question);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }

    @Test
    void testDeleteQuestion() {
        ResponseEntity<String> response = questionService.deleteQuestion(1);

        verify(questionDao, times(1)).deleteById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted", response.getBody());
    }

    @Test
    void testUpdateQuestionFound() {
        Question oldQuestion = new Question();
        oldQuestion.setId(1);
        Question newQuestion = new Question();
        newQuestion.setId(1);

        when(questionDao.findById(1)).thenReturn(Optional.of(oldQuestion));

        ResponseEntity<String> response = questionService.updateQuestion(1, newQuestion);

        verify(questionDao, times(1)).save(newQuestion);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated", response.getBody());
    }

    @Test
    void testUpdateQuestionNotFound() {
        Question newQuestion = new Question();
        when(questionDao.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = questionService.updateQuestion(1, newQuestion);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("not found", response.getBody());
    }
}
