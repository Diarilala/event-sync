package com.choom.back.service;

import com.choom.back.entity.Answer;
import com.choom.back.exception.NotFoundException;
import com.choom.back.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AnswerService {
   private final AnswerRepository answerRepository;

    public List<Answer> getAllAnswers(){
        return answerRepository.findAllAnswers();
    }

    public Answer getAnswerById(UUID id){
        Optional<Answer> optionalAnswer = answerRepository.findAnswerById(id);

        if(optionalAnswer.isEmpty()){
            throw new NotFoundException("Answer.id=" + id + "is not found");
        }
        return optionalAnswer.get();
    }

    public Answer createAnswer (Answer answer){
        return answerRepository.createAnswer(answer);
    }


}
