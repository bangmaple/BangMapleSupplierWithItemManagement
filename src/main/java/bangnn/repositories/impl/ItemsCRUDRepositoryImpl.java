/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.repositories.impl;

import bangnn.blos.ItemsBLO;
import bangnn.blos.SuppliersBLO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author bangmaple
 */
public class ItemsCRUDRepositoryImpl extends GenericCRUDRepositoryImpl<ItemsBLO, String> {

    private static final String SEARCH_ITEMS_BY_ANY_WORD = "SELECT I FROM "
            + "ItemsBLO AS I WHERE I.itemCode LIKE :itemCode OR I.itemName LIKE "
            + ":itemName OR I.price BETWEEN 0 AND :itemPrice OR I.unit LIKE :itemUnit OR "
            + "I.supCode = :itemSupplier";
    private static final String GET_ALL_ITEMS_ERROR = "Error while getting all items: ";

    @Override
    public boolean add(ItemsBLO blo) throws IllegalArgumentException {
        super.addEntityToDB(blo);
        return true;
    }

    @Override
    public ItemsBLO get(String v) throws IllegalArgumentException {
        return super.findEntityFromDB(v);
    }

    @Override
    public boolean update(ItemsBLO k) throws IllegalArgumentException {
        super.updateEntityToDB(k);
        return true;
    }

    @Override
    public boolean delete(ItemsBLO k) throws IllegalArgumentException {
        super.deleteEntityFromDB(k);
        return true;
    }

    public final List<ItemsBLO> getAllItems() throws IllegalArgumentException {
        EntityManager em = getEntityManager();
        List<ItemsBLO> list = null;
        try {
            em.getTransaction().begin();
            list = em.createNamedQuery("ItemsBLO.findAll").getResultList();
            em.getTransaction().commit();
        } catch (IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(GET_ALL_ITEMS_ERROR + e.getMessage());
        } finally {
            attemptToCloseEntityManager(em);
        }
        return list;
    }

    public final List<ItemsBLO> getItemsByAnyChar(final String search, final SuppliersBLO supplier) throws IllegalArgumentException {
        EntityManager em = getEntityManager();
        List<ItemsBLO> list = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(SEARCH_ITEMS_BY_ANY_WORD)
                    .setParameter("itemCode", "%" + search + "%")
                    .setParameter("itemName", "%" + search + "%")
                    .setParameter("itemUnit", "%" + search + "%")
                    .setParameter("itemSupplier", supplier);
            
            try {
                query = query.setParameter("itemPrice", Double.parseDouble(search));
            } catch (NumberFormatException e) {
                query = query.setParameter("itemPrice", null);
            }
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (IllegalStateException | PersistenceException e) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException(GET_ALL_ITEMS_ERROR + e.getMessage());
        } finally {
            attemptToCloseEntityManager(em);
        }
        return list;
    }

}
