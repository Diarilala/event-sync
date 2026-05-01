package com.choom.back.repository;

import com.choom.back.config.DBConfig;
import com.choom.back.entity.Admin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@Repository
@AllArgsConstructor

public class AdminRepository {

    private final DBConfig dbConfig;

    public Admin getAdmin (String email) {
        Admin admin = new Admin();
        String query = """
                SELECT id, first_name, last_name, email, password_hash FROM admin
                WHERE email = ?;
                """;

        try (Connection connection = dbConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin.setId((UUID) resultSet.getObject("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPasswordHash(resultSet.getString("password_hash"));
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}
