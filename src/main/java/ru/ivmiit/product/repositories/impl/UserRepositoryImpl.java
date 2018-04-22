package ru.ivmiit.product.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ivmiit.product.models.User;
import ru.ivmiit.product.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 05.04.2018
 *
 * @author Robert Bagramov.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static String INSERT_USER_SQL = "INSERT INTO kfu_user (login, password) VALUES (?, ?)";
    private static String SELECT_ALL_FROM_PRODUCTS_SQL = "SELECT * FROM kfu_user";
    private static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM kfu_user WHERE " +
            "kfu_user.login = ?";
    private final Connection jdbcConnection;

    @Autowired
    public UserRepositoryImpl(Connection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }


    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<User>();

            Statement statement = jdbcConnection.createStatement();

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PRODUCTS_SQL);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .build();

                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return null;
    }

    public User findByLogin(String login) {
        try {
            PreparedStatement statement = jdbcConnection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            User user = null;

            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
            }

            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean create(User user) {
        try {
            PreparedStatement statement = jdbcConnection.prepareStatement(INSERT_USER_SQL);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
