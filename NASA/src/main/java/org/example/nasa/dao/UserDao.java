package org.example.nasa.dao;

import org.example.nasa.model.User;

public interface UserDao {
    void save(User user);
    User findByUsername(String username);
}
