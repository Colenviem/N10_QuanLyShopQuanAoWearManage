package dao;

import dto.PurchaseOrder;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderDAO {
    private EntityManager em;

    public PurchaseOrderDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return em.createQuery("SELECT po FROM PurchaseOrder po", PurchaseOrder.class)
                .getResultList();
    }

    public List<PurchaseOrder> getPurchaseOrderByOrderDate(LocalDate orderDate) {
        return em.createQuery("SELECT po FROM PurchaseOrder po WHERE po.orderDate = :orderDate", PurchaseOrder.class)
                .setParameter("orderDate", orderDate)
                .getResultList();
    }

    public List<PurchaseOrder> getPurchaseOrderByStatus(boolean status) {
        return em.createQuery("SELECT po FROM PurchaseOrder po WHERE po.status = :status", PurchaseOrder.class)
                .setParameter("status", status)
                .getResultList();
    }

    public PurchaseOrder getPurchaseOrderById(int id) {
        return em.find(PurchaseOrder.class, id);
    }

    public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            em.getTransaction().begin();
            em.persist(purchaseOrder);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            em.getTransaction().begin();
            em.merge(purchaseOrder);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePurchaseOrder(String id) {
        try {
            PurchaseOrder purchaseOrder = em.find(PurchaseOrder.class, id);
            if (purchaseOrder == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(purchaseOrder);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
