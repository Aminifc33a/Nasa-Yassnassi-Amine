package org.example.nasaweb.dao.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.nasaweb.dao.AsteroidDao;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.util.NasaManager;

import java.util.List;

public class AsteroidDaoImpl implements AsteroidDao {

    @Override
    public void create(Asteroid asteroid) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(asteroid);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error saving asteroid: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Asteroid> findAll() {
        EntityManager em = NasaManager.getEntityManager();
        try {
            String jpql = "SELECT a FROM Asteroid a";
            TypedQuery<Asteroid> query = em.createQuery(jpql, Asteroid.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Asteroid findById(long id) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            return em.find(Asteroid.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Asteroid asteroid = em.find(Asteroid.class, id);
            if (asteroid != null) {
                em.remove(asteroid);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error deleting asteroid by id: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Asteroid asteroid) {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(asteroid); // Actualiza el asteroide en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error updating asteroid: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAll() {
        EntityManager em = NasaManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Asteroid").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException("Error deleting all asteroids: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}