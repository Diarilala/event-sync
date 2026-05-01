package com.choom.back.repository;

import com.choom.back.config.DBConfig;
import com.choom.back.entity.Admin;
import com.choom.back.exception.AuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor

public class AdminRepository {

    private final DBConfig dbConfig;

    public Admin getAdmin (String email) {
        String query = """
                SELECT id, first_name, last_name, email, password_hash FROM admin
                WHERE email = ?;
                """;

        try (Connection connection = dbConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setId((UUID) resultSet.getObject("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPasswordHash(resultSet.getString("password_hash"));
                    return admin;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error fetching admin: " +email, e);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error fetching admin: " +email,e);
        }
        return null;
    }
}
