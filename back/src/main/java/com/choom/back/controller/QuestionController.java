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
@RestController
public class QuestionController {
    QuestionService questionServices;

    @GetMapping("/questions")
    public ResponseEntity<?> getAllQuestions(){
        try{
         return ResponseEntity.status(HttpStatus.OK).body(questionServices.getAllQuestion());
        }catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable UUID id){
        try{
            Question question= questionServices.getQuestionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(questionServices.getQuestionById(id));
        }catch (BadRequestException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
//        try{
        Question questionMapped = questionServices.mapToEntity(question);
        Question created = questionServices.createQuestion(questionMapped);

            return ResponseEntity.status(HttpStatus.CREATED).body(created);
//        }catch (IllegalArgumentException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input:" + e.getMessage());
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to create question: " + e.getMessage());
//        }
    }


}
