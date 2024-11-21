package org.example.nasaweb.dao;

import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;

import java.util.List;

public interface ApproachDao {
    void create(Approach approach, Asteroid asteroid);
    List<Approach> findByAsteroidId(long id);
    void deleteById(long id);
    void deleteAll();
}
