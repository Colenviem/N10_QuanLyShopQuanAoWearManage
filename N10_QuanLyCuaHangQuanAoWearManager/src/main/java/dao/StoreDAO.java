package dao;

import dto.Store;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class StoreDAO {
    private EntityManager em;

    public StoreDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Store> getAllStores() {
        return em.createQuery("SELECT s FROM Store s", Store.class)
                .getResultList();
    }

    public List<Store> getStoreByStoreName(String storeName) {
        return em.createQuery("SELECT s FROM Store s WHERE s.name = :storeName", Store.class)
                .setParameter("storeName", storeName)
                .getResultList();
    }

    public Store getStoreById(int id) {
        return em.find(Store.class, id);
    }

    public boolean addStore(Store store) {
        try {
            em.getTransaction().begin();
            em.persist(store);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStore(Store store) {
        try {
            em.getTransaction().begin();
            em.merge(store);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStore(String id) {
        try {
            Store store = em.find(Store.class, id);
            if (store == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(store);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
