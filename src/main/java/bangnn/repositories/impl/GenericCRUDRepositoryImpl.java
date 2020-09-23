/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.repositories.impl;

import bangnn.repositories.ICRUDRepository;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author bangmaple
 * @param <K>
 * @param <V>
 */
public abstract class GenericCRUDRepositoryImpl<K, V> implements ICRUDRepository<K, V> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("J2.L.P0001");

    protected final EntityManager getEntityManager() throws IllegalStateException {
        return emf.createEntityManager();
    }

    private void closeEntityManager(final EntityManager em) throws IllegalStateException {
        if (em != null) {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    protected final void attemptToCloseEntityManager(final EntityManager em)
            throws IllegalArgumentException {
        try {
            closeEntityManager(em);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected final void addEntityToDB(K k) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
        } catch (IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            attemptToCloseEntityManager(em);
        }
    }

    protected final void deleteEntityFromDB(K k) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(k)) {
                k = em.merge(k);
            }
            em.remove(k);
            em.getTransaction().commit();
        } catch (IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            attemptToCloseEntityManager(em);
        }
    }

    protected final void updateEntityToDB(K k) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(k);
            em.getTransaction().commit();
        } catch (IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            attemptToCloseEntityManager(em);
        }
    }

    protected final K findEntityFromDB(V v) throws IllegalArgumentException {
        EntityManager em = emf.createEntityManager();
        K k = null;
        try {
            em.getTransaction().begin();
            k = em.find((Class<K>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0], v);
            em.getTransaction().commit();
        } catch (IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            attemptToCloseEntityManager(em);
        }
        return k;
    }

}
