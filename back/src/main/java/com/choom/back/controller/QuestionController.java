package com.choom.back.controller;

import com.choom.back.entity.Question;
import com.choom.back.exception.BadRequestException;
import com.choom.back.exception.NotFoundException;
import com.choom.back.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController("question")
public class QuestionController {
    QuestionService questionServices;

    @GetMapping
    public ResponseEntity<?> getAllQuestions(){

         return ResponseEntity.status(HttpStatus.OK).body(questionServices.getAllQuestion());

    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable UUID id){

            Question question= questionServices.getQuestionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(question);

    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {

        Question created = questionServices.createQuestion(question);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
