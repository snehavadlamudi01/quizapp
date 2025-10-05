package com.telusko.quizapp.Controller;

import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Service.QuestionService;
import com.telusko.quizapp.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<ApiResponse> addQuestion(@Valid @RequestBody Question question){
        questionService.addQuestion(question);

        ApiResponse response =new ApiResponse(200, "Success", "Question added successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    @PutMapping ("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

}
