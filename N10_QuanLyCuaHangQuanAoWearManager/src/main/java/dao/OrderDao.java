package dao;

import dto.Order;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderDao {
    private EntityManager em;

    public List<Order> getAllOrders() {
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public List<Order> getOrderByOrderDate(String orderDate) {
        return em.createQuery("SELECT o FROM Order o WHERE o.orderDate = :orderDate", Order.class)
                .setParameter("orderDate", orderDate)
                .getResultList();
    }

    public Order getOrderById(String id) {
        return em.find(Order.class, id);
    }

    public boolean saveOrder(Order order) {
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrder(Order order) {
        try {
            em.getTransaction().begin();
            em.merge(order);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(String id) {
        try {
            Order order = em.find(Order.class, id);
            if (order == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(order);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
