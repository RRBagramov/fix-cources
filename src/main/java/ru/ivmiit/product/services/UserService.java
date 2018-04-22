package ru.ivmiit.product.services;

import ru.ivmiit.product.models.User;

import java.util.List;

/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
public interface UserService {
    List<User> getUsers();

    boolean authenticateUserByLoginAndPassword(String login, String password);

    void saveUser(User user);
}
