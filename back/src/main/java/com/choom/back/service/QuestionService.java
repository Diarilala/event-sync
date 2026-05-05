package com.choom.back.service;

import com.choom.back.entity.Question;
import com.choom.back.exception.NotFoundException;
import com.choom.back.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QuestionService {
    QuestionRepository questionRepository;

    public List<Question> getAllQuestion(){
        return questionRepository.findAllQuestion();
    }

    public Question getQuestionById(UUID id){
        Optional<Question> optionalQuestion = questionRepository.findQuestionById(id);

        if(optionalQuestion.isEmpty()){
            throw new NotFoundException("Question.id=" + id + "is not found");
        }
        return optionalQuestion.get();
    }

    public Question createQquestion (Question question){
        return questionRepository.createQuestion(question);
    }

    public Question mapToEntity(Question question){
        Question q = new Question();
        q.setId(question.getId());
        q.setContent(question.getContent());
        q.setCreationDate(question.getCreationDate());
        q.setAuthorName(question.getAuthorName());

        return q;
    }
}

