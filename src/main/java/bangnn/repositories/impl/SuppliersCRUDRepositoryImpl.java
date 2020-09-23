/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.repositories.impl;

import bangnn.blos.SuppliersBLO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author bangmaple
 */
public class SuppliersCRUDRepositoryImpl extends GenericCRUDRepositoryImpl<SuppliersBLO, String> {

    private static final String GET_ITEMS_BY_LIKE_ANY_QUERY = "SELECT s FROM "
            + "SuppliersBLO s WHERE s.supCode LIKE :code OR s.supName LIKE "
            + ":name OR s.address LIKE :address";
    @Override
    public boolean add(SuppliersBLO k) throws IllegalArgumentException {
        super.addEntityToDB(k);
        return true;
    }

    @Override
    public SuppliersBLO get(String v) throws IllegalArgumentException {
        return super.findEntityFromDB(v);
    }

    @Override
    public boolean update(SuppliersBLO k) throws IllegalArgumentException {
        super.updateEntityToDB(k);
        return true;
    }

    @Override
    public boolean delete(SuppliersBLO k) throws IllegalArgumentException {

        deleteEntityFromDB(k);
        return true;
    }

    public List<SuppliersBLO> getAllSuppliers() throws IllegalArgumentException {
        EntityManager em = getEntityManager();
        List<SuppliersBLO> list = null;
        em.getTransaction().begin();
        try {
            list = em.createNamedQuery("SuppliersBLO.findAll").getResultList();
            em.getTransaction().commit();
        } catch (IllegalArgumentException | IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            attemptToCloseEntityManager(em);
        }
        return list;
    }

    public List<SuppliersBLO> findSuppliersByLikeAny(final String search) {
        EntityManager em = getEntityManager();
        List<SuppliersBLO> list = null;
        em.getTransaction().begin();
        try {
            list = em.createQuery(GET_ITEMS_BY_LIKE_ANY_QUERY)
                    .setParameter("code", "%" + search + "%")
                    .setParameter("name", "%" + search + "%")
                    .setParameter("address", "%" + search + "%").getResultList();
            em.getTransaction().commit();
        } catch (IllegalArgumentException | IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(e);
        } finally {
            attemptToCloseEntityManager(em);
        }
        return list;
    }
    
}
