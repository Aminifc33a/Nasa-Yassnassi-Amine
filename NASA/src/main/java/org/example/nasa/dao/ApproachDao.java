package org.example.nasa.dao;

import org.example.nasa.model.Approach;
import java.util.List;

public interface ApproachDao {
    void save(Approach approach);
    List<Approach> findAllByAsteroidId(String asteroidId);
}

