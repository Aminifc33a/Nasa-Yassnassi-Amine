package org.example.nasaweb.dao.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.nasaweb.dao.ApproachDao;
import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.util.NasaManager;

import java.util.List;

public class ApproachDaoImpl implements ApproachDao {

    @Override
    public void create(Approach approach, Asteroid asteroid) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            approach.setAsteroid(asteroid); // Asociar el asteroide al enfoque
            em.persist(approach);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error saving approach: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Approach> findByAsteroidId(long asteroidId) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            // Consulta para buscar enfoques asociados a un asteroide
            String jpql = "SELECT a FROM Approach a WHERE a.asteroid.id = :asteroidId";
            TypedQuery<Approach> query = em.createQuery(jpql, Approach.class);
            query.setParameter("asteroidId", asteroidId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Approach approach = em.find(Approach.class, id);
            if (approach != null) {
                em.remove(approach);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error deleting approach by id: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAll() {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            // Consulta para eliminar todos los enfoques
            em.createQuery("DELETE FROM Approach").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error deleting all approaches: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
