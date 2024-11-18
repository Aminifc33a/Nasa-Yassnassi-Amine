//package org.example.nasaweb.dao.JPA;
//
//import jakarta.persistence.EntityManager;
//import org.example.nasaweb.dao.ApproachDao;
//import org.example.nasaweb.model.Approach;
//import org.example.nasaweb.util.NasaManager;
//
//public class ApproachDaoImpl implements ApproachDao {
//
//    @Override
//    public void create(Approach approach) {
//        EntityManager em = NasaManager.getEntityManager();
//        em.getTransaction().begin(); // Iniciar transacción
//        em.persist(approach);
//        em.getTransaction().commit(); // Confirmar la transacción
//    }
//
//    @Override
//    public Approach findById(Long id) {
//        EntityManager em = NasaManager.getEntityManager();
//        return em.find(Approach.class, id);
//    }
//
//    @Override
//    public void update(Approach approach) {
//        EntityManager em = NasaManager.getEntityManager();
//        em.merge(approach);
//    }
//
//    @Override
//    public void delete(Long id) {
//        EntityManager em = NasaManager.getEntityManager();
//        Approach approach = em.find(Approach.class, id);
//        if (approach != null) {
//            em.remove(approach);
//        }
//    }
//}
