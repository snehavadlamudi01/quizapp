package com.telusko.quizapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        ApiResponse response = new ApiResponse(200, "success", "Operation completed");
        assertEquals(200, response.getResultCode());
        assertEquals("success", response.getStatus());
        assertEquals("Operation completed", response.getResultMessage());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        ApiResponse response = new ApiResponse();
        response.setResultCode(400);
        response.setStatus("failed");
        response.setResultMessage("Error occurred");

        assertEquals(400, response.getResultCode());
        assertEquals("failed", response.getStatus());
        assertEquals("Error occurred", response.getResultMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        ApiResponse r1 = new ApiResponse(200, "success", "OK");
        ApiResponse r2 = new ApiResponse(200, "success", "OK");
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        ApiResponse response = new ApiResponse(200, "success", "OK");
        String str = response.toString();
        assertTrue(str.contains("resultCode=200"));
        assertTrue(str.contains("status=success"));
        assertTrue(str.contains("resultMessage=OK"));
    }
}
