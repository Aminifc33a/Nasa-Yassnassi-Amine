package org.example.nasa.service;

import org.example.nasa.dao.AsteroidDao;
import org.example.nasa.dao.JPA.AsteroidDaoImpl;
import org.example.nasa.model.Asteroid;

import java.util.List;

public class AsteroidService  {
    private final AsteroidDao asteroidDao = new AsteroidDaoImpl();
    public void save(Asteroid asteroid) {
        asteroidDao.save(asteroid);
    }

    public void update(Asteroid asteroid) {
        asteroidDao.update(asteroid);
    }

    public void deleteById(String id) {
        asteroidDao.deleteById(id);
    }

    public Asteroid findById(String id) {
        return asteroidDao.findById(id);
    }

    public List<Asteroid> findAll() {
        return asteroidDao.findAll();
    }
}
