package org.example.nasa.dao.JPA;

import jakarta.persistence.EntityManager;
import org.example.nasa.dao.UserDao;
import org.example.nasa.model.User;
import org.mindrot.jbcrypt.BCrypt;
public class UserDaoImpl implements UserDao {
    private EntityManager em;

    @Override
    public void save(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User findByUsername(String username) {
        return em.find(User.class, username);
    }
}
