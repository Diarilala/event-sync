package com.choom.back.model;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Session {
    private long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;
}