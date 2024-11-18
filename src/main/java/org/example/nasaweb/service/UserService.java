package org.example.nasaweb.service;

import org.example.nasaweb.dao.JPA.UserDaoImpl;
import org.example.nasaweb.dao.UserDao;
import org.example.nasaweb.model.User;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();
    public void create(User user) {
        userDao.create(user);
    }
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }
}
