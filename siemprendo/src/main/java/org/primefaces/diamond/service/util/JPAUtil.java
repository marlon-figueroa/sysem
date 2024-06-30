package org.primefaces.diamond.service.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.diamond.domain.Usuario;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "PU_SYSEM";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public static void main(String[] args) {
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Usuario> lista = new ArrayList<>();
        Query q = entity.createQuery("SELECT c FROM Usuario c");
        lista = q.getResultList();
        System.out.println("Lista: " + Arrays.toString(lista.toArray()));
    }
}
