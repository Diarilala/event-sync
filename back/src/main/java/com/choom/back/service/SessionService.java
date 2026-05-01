package com.choom.back.service;

import com.choom.back.entity.Session;
import com.choom.back.exception.NotFoundException;
import com.choom.back.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public List<Session> getAllSession(){
        return sessionRepository.findAllSession();
    }

    public Session getSessionById(UUID id){
        try {
            return sessionRepository.findSessionById(id);
        } catch (NotFoundException e){
            throw new NotFoundException("Session with id " + id + " not found");
        }
    }
}
