package com.choom.back.controller;

import com.choom.back.entity.Answer;
import com.choom.back.exception.BadRequestException;
import com.choom.back.exception.NotFoundException;
import com.choom.back.service.AnswerService;
import com.choom.back.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("answer")
public class AnswerController {
    private final AnswerService answerService;


    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;

    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAllAnswers(){

            return ResponseEntity.status(HttpStatus.OK).body(answerService.getAllAnswers());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable UUID id){

            Answer answer= answerService.getAnswerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(answer);

    }

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer){

        Answer created = answerService.createAnswer(answer);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }



}
