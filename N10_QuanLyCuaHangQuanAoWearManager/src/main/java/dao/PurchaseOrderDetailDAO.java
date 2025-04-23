package dao;

import dto.PurchaseOrderDetail;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class PurchaseOrderDetailDAO {
    private EntityManager em;

    public PurchaseOrderDetailDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<PurchaseOrderDetail> getAllPurchaseOrderDetails() {
        return em.createQuery("SELECT pod FROM PurchaseOrderDetail pod", PurchaseOrderDetail.class)
                .getResultList();
    }

    public PurchaseOrderDetail getPurchaseOrderDetailById(int id) {
        return em.find(PurchaseOrderDetail.class, id);
    }

    public boolean addPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        try {
            em.getTransaction().begin();
            em.persist(purchaseOrderDetail);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
        try {
            em.getTransaction().begin();
            em.merge(purchaseOrderDetail);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePurchaseOrderDetail(String id) {
        try {
            PurchaseOrderDetail purchaseOrderDetail = em.find(PurchaseOrderDetail.class, id);
            if (purchaseOrderDetail == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(purchaseOrderDetail);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
