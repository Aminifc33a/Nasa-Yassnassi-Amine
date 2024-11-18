package org.example.nasaweb.dao.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.nasaweb.dao.UserDao;
import org.example.nasaweb.model.User;
import org.example.nasaweb.util.NasaManager;
import org.mindrot.jbcrypt.BCrypt;

public class UserDaoImpl implements UserDao {

    @Override
    public void create(User user) {
        EntityManager manager = NasaManager.getEntityManager();
        manager.getTransaction().begin();

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public User getUser(String username, String password) {
        try (EntityManager manager = NasaManager.getEntityManager()) {
            TypedQuery<User> query = manager.createQuery("select u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            User user = query.getSingleResult();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        } catch (NoResultException e) {
            return null;
        }
        return null;
    }
    @Override
    public boolean existsByUsername(String username) {
        EntityManager manager = NasaManager.getEntityManager();
        boolean exists = false;
        try {
            TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            exists = !query.getResultList().isEmpty();
        } finally {
            manager.close();
        }
        return exists;
    }
}