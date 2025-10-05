package com.telusko.quizapp.Service;

import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Model.QuestionWrapper;
import com.telusko.quizapp.Model.Quiz;
import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.dao.QuizDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizServiceTest {

    @Mock
    private QuizDao quizDao;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateQuizSuccess() {
        List<Question> questions = Arrays.asList(new Question());
        when(questionDao.findRandomQuestionsByCategory("Java", 1)).thenReturn(questions);

        ResponseEntity<String> response = quizService.createQuiz("Java", 1, "Sample Quiz");

        verify(quizDao, times(1)).save(any(Quiz.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    void testGetQuizQuestionsSuccess() {
        Question q = new Question();
        q.setId(1);
        q.setQuestionTitle("Title");
        q.setOption1("A");
        q.setOption2("B");
        q.setOption3("C");
        q.setOption4("D");
        Quiz quiz = new Quiz();
        quiz.setQuestions(Arrays.asList(q));
        when(quizDao.findById(1)).thenReturn(Optional.of(quiz));

        ResponseEntity<List<QuestionWrapper>> response = quizService.getQuizQuestions(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Title", response.getBody().get(0).getQuestionTitle());
    }

    @Test
    void testGetQuizQuestionsNotFound() {
        when(quizDao.findById(99)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> quizService.getQuizQuestions(99));
    }
}
