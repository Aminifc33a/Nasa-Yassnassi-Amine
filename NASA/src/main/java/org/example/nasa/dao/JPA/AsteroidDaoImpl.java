package org.example.nasa.dao.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.nasa.dao.AsteroidDao;
import org.example.nasa.model.Asteroid;

import java.util.List;

public class AsteroidDaoImpl implements AsteroidDao {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    @Override
    public void save(Asteroid asteroid) {
        em.getTransaction().begin();
        em.persist(asteroid);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Asteroid asteroid) {
        em.getTransaction().begin();
        em.merge(asteroid);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteById(String id) {
        em.getTransaction().begin();
        Asteroid asteroidToDelete = em.find(Asteroid.class, id);
        em.remove(asteroidToDelete);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Asteroid findById(String id) {
        return em.find(Asteroid.class, id);
    }

    @Override
    public List<Asteroid> findAll() {
        return em.createQuery("SELECT a FROM Asteroid a", Asteroid.class).getResultList();
    }
}
