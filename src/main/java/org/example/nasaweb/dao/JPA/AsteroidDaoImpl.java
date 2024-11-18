//package org.example.nasaweb.dao.JPA;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import org.example.nasaweb.dao.AsteroidDao;
//import org.example.nasaweb.model.Asteroid;
//import org.example.nasaweb.util.NasaManager;
//
//import java.util.List;
//
//public class AsteroidDaoImpl implements AsteroidDao {
//    @Override
//    public void create(Asteroid asteroid) {
//        EntityManager em = NasaManager.getEntityManager(); // Suponiendo que tienes un EntityManager configurado
//        try {
//            em.getTransaction().begin();
//            em.persist(asteroid);  // Este método persistirá tanto el asteroide como sus acercamientos
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            throw e;
//        }
//    }
//
//
//    @Override
//    public List<Asteroid> findAll() {
//        EntityManager em = NasaManager.getEntityManager();
//        TypedQuery<Asteroid> query = em.createQuery("SELECT a FROM Asteroid a", Asteroid.class);
//        return query.getResultList();
//    }
//
//
//    @Override
//    public Asteroid findById(Long id) {
//        EntityManager em = NasaManager.getEntityManager();
//        return em.find(Asteroid.class, id);
//    }
//
//    @Override
//    public void update(Asteroid asteroid) {
//        EntityManager em = NasaManager.getEntityManager();
//        em.merge(asteroid);
//    }
//
//    @Override
//    public void delete(Long id) {
//        EntityManager em = NasaManager.getEntityManager();
//        Asteroid asteroid = em.find(Asteroid.class, id);
//        if (asteroid != null) {
//            em.remove(asteroid);
//        }
//    }
//}
