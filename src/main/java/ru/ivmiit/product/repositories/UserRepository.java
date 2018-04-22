package ru.ivmiit.product.repositories;

import ru.ivmiit.product.models.User;

import java.util.List;

/**
 * 05.04.2018
 *
 * @author Robert Bagramov.
 */
public interface UserRepository {
    List<User> findAll();

    User findByLogin(String login);

    boolean create(User user);
}
