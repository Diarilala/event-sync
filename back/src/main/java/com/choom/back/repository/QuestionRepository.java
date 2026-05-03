package com.choom.back.repository;

import com.choom.back.config.DBConfig;
import com.choom.back.entity.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Repository
public class QuestionRepository {
    private final DBConfig dbConfig;

    public Question savaQuestion(Question question){

        String saveQuestionQuery = "Insert into question (id, content,author_name, creation_date, upvote_count) " +
                "values (?, ? , ?, ?) ";

        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveQuestionQuery)){

            if(question.getId() == null){
                question.setId(UUID.randomUUID());
            }

            preparedStatement.setObject(1, question.getId());
            preparedStatement.setString(2, question.getContent());
            preparedStatement.setString(3, question.getAuhtorName());

            Timestamp creationDate = question.getCreationDate() != null
                    ? question.getCreationDate()
                    : new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(4, creationDate);

            preparedStatement.setInt(5, question.getUpvoteCount() != null
                    ? question.getUpvoteCount()
                    : 0);

            preparedStatement.executeUpdate();
            return question;

        } catch (SQLException e) {
            throw new RuntimeException("error saving question",e);
        }
    }

}
