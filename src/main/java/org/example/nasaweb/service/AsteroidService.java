package org.example.nasaweb.service;

import org.example.nasaweb.dao.AsteroidDao;
import org.example.nasaweb.dao.JDBC.AsteroidDaoImpl;
import org.example.nasaweb.model.Asteroid;

import javax.inject.Inject;
import java.util.List;

public class AsteroidService {

    @Inject
    private AsteroidDao asteroidDao;
public AsteroidService(){
    asteroidDao = new AsteroidDaoImpl();
}
    public List<Asteroid> findAll() {
        return asteroidDao.findAll();
    }

    public Asteroid findById(long id) {
        return asteroidDao.findById(id);
    }

    public void create(Asteroid asteroid) {
        asteroidDao.create(asteroid);
    }
    public void deleteAll() {
        asteroidDao.deleteAll();
    }
}