package org.example.nasa.dao.JDBC;

import org.example.nasa.dao.AsteroidDao;
import org.example.nasa.model.Asteroid;

import java.util.List;

public class AsteroidDaoJdbc implements AsteroidDao {
    @Override
    public void save(Asteroid asteroid) {

    }

    @Override
    public void update(Asteroid asteroid) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Asteroid findById(String id) {
        return null;
    }

    @Override
    public List<Asteroid> findAll() {
        return List.of();
    }
}
