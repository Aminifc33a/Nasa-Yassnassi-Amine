package org.example.nasaweb.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NasaManager {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("nasa");
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
