package com.choom.back.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Session {
    @Id
    private long eventId;
    private UUID SessionId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;
}