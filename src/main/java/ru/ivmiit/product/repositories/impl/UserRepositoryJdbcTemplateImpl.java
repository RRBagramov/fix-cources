package ru.ivmiit.product.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ivmiit.product.models.User;
import ru.ivmiit.product.repositories.UserRepository;

import java.util.List;

/**
 * 10.04.2018
 *
 * @author Robert Bagramov.
 */
@Repository("userRepositoryJdbcTemplateImpl")
public class UserRepositoryJdbcTemplateImpl implements UserRepository {
    private static String INSERT_USER_SQL = "INSERT INTO kfu_user (login, password) VALUES (?, ?)";
    private static String SELECT_ALL_FROM_PRODUCTS_SQL = "SELECT * FROM kfu_user";
    private static String FIND_USER_BY_LOGIN = "SELECT * FROM kfu_user WHERE " +
            "kfu_user.login = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_PRODUCTS_SQL, User.userRowMapper);
    }

    @Override
    public User findByLogin(String login) {
        return jdbcTemplate
                .queryForObject(FIND_USER_BY_LOGIN, new Object[]{login}, User.userRowMapper);
    }

    public boolean create(User user) {
        return jdbcTemplate
                .update(INSERT_USER_SQL, user.getLogin(), user.getPassword()) > 0;

    }
}
