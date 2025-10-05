package com.telusko.quizapp.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuestionWrapperTest {

    @Test
    void testConstructorAndGetters() {
        QuestionWrapper wrapper = new QuestionWrapper(1, "Title", "A", "B", "C", "D");
        assertEquals(1, wrapper.getId());
        assertEquals("Title", wrapper.getQuestionTitle());
        assertEquals("A", wrapper.getOption1());
        assertEquals("B", wrapper.getOption2());
        assertEquals("C", wrapper.getOption3());
        assertEquals("D", wrapper.getOption4());
    }

    @Test
    void testSetters() {
        QuestionWrapper wrapper = new QuestionWrapper(1, "Title", "A", "B", "C", "D");
        wrapper.setId(2);
        wrapper.setQuestionTitle("New Title");
        wrapper.setOption1("E");
        wrapper.setOption2("F");
        wrapper.setOption3("G");
        wrapper.setOption4("H");

        assertEquals(2, wrapper.getId());
        assertEquals("New Title", wrapper.getQuestionTitle());
        assertEquals("E", wrapper.getOption1());
        assertEquals("F", wrapper.getOption2());
        assertEquals("G", wrapper.getOption3());
        assertEquals("H", wrapper.getOption4());
    }
}
