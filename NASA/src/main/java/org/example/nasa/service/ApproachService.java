package org.example.nasa.service;

import org.example.nasa.dao.ApproachDao;
import org.example.nasa.dao.JPA.ApproachDaoImpl;
import org.example.nasa.model.Approach;

import java.util.List;

public class ApproachService  {
    private final ApproachDao approachDao = new ApproachDaoImpl();
    public void save(Approach approach) {
        approachDao.save(approach);
    }

    public List<Approach> findAllByAsteroidId(String asteroidId) {
        return approachDao.findAllByAsteroidId(asteroidId);
    }
}
