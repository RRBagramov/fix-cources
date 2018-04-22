package ru.ivmiit.product.services.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ivmiit.product.models.User;
import ru.ivmiit.product.repositories.UserRepository;
import ru.ivmiit.product.services.UserService;

import java.util.List;

/**
 * 18.04.2018
 *
 * @author Robert Bagramov.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("userRepositoryJdbcTemplateImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean authenticateUserByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user != null) {
            return BCrypt.checkpw(password, user.getPassword());
        }

        return false;
    }

    @Override
    public void saveUser(User user) {
        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPassword);
        userRepository.create(user);
    }
}
