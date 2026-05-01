package com.choom.back.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor

public class Admin {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
}
