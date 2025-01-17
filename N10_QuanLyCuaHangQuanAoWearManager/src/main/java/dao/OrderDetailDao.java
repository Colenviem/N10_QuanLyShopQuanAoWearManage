package dao;

import dto.OrderDetail;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderDetailDao {
    private EntityManager em;

    public List<OrderDetail> getAllOrderDetails() {
        return em.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class)
                .getResultList();
    }

    public OrderDetail getOrderDetailById(String id) {
        return em.find(OrderDetail.class, id);
    }

    public boolean saveOrderDetail(OrderDetail orderDetail) {
        try {
            em.getTransaction().begin();
            em.persist(orderDetail);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        try {
            em.getTransaction().begin();
            em.merge(orderDetail);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetail(String id) {
        try {
            OrderDetail orderDetail = em.find(OrderDetail.class, id);
            if (orderDetail == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(orderDetail);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
