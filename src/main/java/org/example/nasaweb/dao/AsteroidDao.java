package org.example.nasaweb.dao;

import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;

import java.util.List;

public interface AsteroidDao {
    void create(Asteroid asteroid);
    List<Asteroid> findAll();
    Asteroid findById(long id);
    void deleteById(long id);
    void update(Asteroid asteroid);
    void deleteAll();
}
