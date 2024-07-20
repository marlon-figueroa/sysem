/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.diamond.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.primefaces.diamond.service.util.JPAUtil;

/**
 *
 * @author marlo
 * @param <T>
 */
public class GenericDAO<T> {

    private static EntityManager em;
    private final Class<T> entityClass;
    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class);

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void setEntityManager(EntityManager entityManagerInstance) {
        em = entityManagerInstance;
    }

    public GenericDAO(Class<T> entityClass) {
        setEntityManager(JPAUtil.getEntityManagerFactory().createEntityManager());
        this.entityClass = entityClass;
    }

    /**
     *
     * Inserts an Entity in the database
     *
     * @param entity to be added to the database.
     * @return
     */
    public boolean create(T entity) {
        boolean wasSuccessOperation = false;
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
            getEntityManager().getTransaction().commit();
            wasSuccessOperation = true;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            LOGGER.error(e);
        }
        return wasSuccessOperation;
    }

    /**
     *
     * Edit an Entity in the database
     *
     * @param entity to be added to the database.
     * @return
     */
    public boolean edit(T entity) {
        boolean wasSuccessOperation = false;
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();
            wasSuccessOperation = true;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            LOGGER.error(e);
        }
        return wasSuccessOperation;
    }

    /**
     *
     * Remove an Entity in the database
     *
     * @param entity to be added to the database.
     * @return
     */
    public boolean remove(T entity) {
        boolean wasSuccessOperation = false;
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(getEntityManager().merge(entity));
            getEntityManager().getTransaction().commit();
            wasSuccessOperation = true;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            LOGGER.error(e);
        }
        return wasSuccessOperation;
    }

    /**
     * Get an Entity in the database
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Get all list of Entities in the database
     *
     * @return
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Get all list of Entities in the database by range
     *
     * @param range
     * @return
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Get count elements by enetity
     *
     * @return
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
