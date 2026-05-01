package com.choom.back.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Question {
    private String id;
    private String content;
    private String name;
    private Timestamp creationDate;
    private List<Answer> answers;
}
