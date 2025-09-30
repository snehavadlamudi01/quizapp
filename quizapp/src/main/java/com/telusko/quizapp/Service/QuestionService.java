package com.telusko.quizapp.Service;

import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String>  addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED );
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question newQuestion ) {
        return questionDao.findById(id).map(existing -> {
            existing.setQuestionTitle(newQuestion.getQuestionTitle());
            existing.setOption1(newQuestion.getOption1());
            existing.setOption2(newQuestion.getOption2());
            existing.setOption3(newQuestion.getOption3());
            existing.setOption4(newQuestion.getOption4());
            existing.setRightAnswer(newQuestion.getRightAnswer());
            existing.setDifficultyLevel(newQuestion.getDifficultyLevel());
            existing.setCategory(newQuestion.getCategory());
            questionDao.save(newQuestion);
            return new ResponseEntity<>("updated",HttpStatus.OK);

        })

                .orElse(new ResponseEntity<>("not found",HttpStatus.NOT_FOUND));
    }
}
