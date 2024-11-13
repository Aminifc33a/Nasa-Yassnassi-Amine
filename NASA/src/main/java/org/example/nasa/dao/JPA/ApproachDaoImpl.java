package org.example.nasa.dao.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.nasa.dao.ApproachDao;
import org.example.nasa.model.Approach;

import java.util.List;

public class ApproachDaoImpl implements ApproachDao {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    @Override
    public void save(Approach approach) {
        em.getTransaction().begin();
        em.persist(approach);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Approach> findAllByAsteroidId(String asteroidId) {
        return em.createQuery("SELECT a FROM Approach a WHERE a.asteroid.id = :asteroidId", Approach.class)
               .setParameter("asteroidId", asteroidId)
               .getResultList();
    }
}
