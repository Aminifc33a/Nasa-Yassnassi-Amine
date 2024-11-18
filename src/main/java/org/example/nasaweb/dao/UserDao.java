package org.example.nasaweb.dao;

import org.example.nasaweb.model.User;

public interface UserDao {
    void create(User user);
    User getUser(String username, String password);
    boolean existsByUsername(String username);
}
