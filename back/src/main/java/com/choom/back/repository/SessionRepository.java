package com.choom.back.repository;

import com.choom.back.config.DBConfig;
import com.choom.back.entity.Session;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class SessionRepository {

    private DBConfig dbConfig;
    public List<Session> findAllSession() {
        List<Session> sessionList = new ArrayList<>();
        String query = """
                SELECT id, title, description, start_time, end_time, room_id, capacity, event_id
                FROM session
                ORDER BY start_time; 
                """;

        try(Connection connection = dbConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                Session session = new Session();
                session.setSessionId((UUID) resultSet.getObject("id"));
                session.setTitle(resultSet.getString("title"));
                session.setDescription(resultSet.getString("description"));
                session.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
                session.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
                session.setRoom((UUID) resultSet.getObject("room_id"));
                session.setEventId((UUID) resultSet.getObject("event_id"));
                sessionList.add(session);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sessionList;
    }

    public Session findSessionById(UUID id) {
        String  query = """
                SELECT id, title, description, start_time, end_time, room_id, capacity, event_id
                FROM session
                WHERE id = ?;
        """;

        try(Connection connection = dbConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Session session = new Session();
                session.setSessionId((UUID) resultSet.getObject("id"));
                session.setTitle(resultSet.getString("title"));
                session.setDescription(resultSet.getString("description"));
                session.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
                session.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
                session.setRoom((UUID) resultSet.getObject("room_id"));
                session.setEventId((UUID) resultSet.getObject("event_id"));
                return session;
            }
    }
        catch (SQLException e) {
        throw new RuntimeException(e);
        }
        return null;
    }

}
