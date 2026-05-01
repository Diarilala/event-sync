package com.choom.back.entity.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class QuestionResponseDto {
    private String id;
    private String content;
    private String name;
    private Timestamp creationDate;
    private Integer upvoteCount;

}
