package ru.ivmiit.product.repositories.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ivmiit.product.models.User;
import ru.ivmiit.product.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * 10.04.2018
 *
 * @author Robert Bagramov.
 */
@Repository
public class UserRepositoryHibernateImpl implements UserRepository {
    @Autowired
    private Session session;
    private Transaction tx;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        tx = session.beginTransaction();
        List Users = session.createQuery("FROM User").list();
        for (Object User : Users) {
            User user = (User) User;
            users.add(user);
        }
        tx.commit();

        return users;
    }

    @Override
    public User findByLogin(String login) {
        return session.createQuery("from User user where user.login = login", User.class).getSingleResult();
    }

    @Override
    public boolean create(User user) {
        tx = session.beginTransaction();
        if (session.save(user) != null) {
            tx.commit();
            return true;
        }

        return false;
    }
}
