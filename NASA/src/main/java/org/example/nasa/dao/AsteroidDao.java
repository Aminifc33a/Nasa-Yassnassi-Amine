package org.example.nasa.dao;

import org.example.nasa.model.Asteroid;
import java.util.List;

public interface AsteroidDao {
    void save(Asteroid asteroid);
    void update(Asteroid asteroid);
    void deleteById(String id);
    Asteroid findById(String id);
    List<Asteroid> findAll();
}