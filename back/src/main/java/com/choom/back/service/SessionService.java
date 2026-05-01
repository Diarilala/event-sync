package com.choom.back.service;

import com.choom.back.entity.Session;
import com.choom.back.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public List<Session> getAllSession(){
        return sessionRepository.findAllSession();
    }
}
