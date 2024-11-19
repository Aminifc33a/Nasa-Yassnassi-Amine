package org.example.nasaweb.service;

import org.example.nasaweb.dao.ApproachDao;
import org.example.nasaweb.dao.JDBC.ApproachDaoImpl;
import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;

import java.util.List;

public class ApproachService {
    private final ApproachDao approachDao;

    public ApproachService() {
        this.approachDao = new ApproachDaoImpl();
    }

    public void create(Approach approach, Asteroid asteroid) {
        approachDao.create(approach, asteroid);
    }

    public List<Approach> findByAsteroidId(long id) {
        return approachDao.findByAsteroidId(id);
    }

    public void deleteAll() {
        approachDao.deleteAll();
    }
}
