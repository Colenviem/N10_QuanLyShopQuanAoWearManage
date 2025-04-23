package dao;

import dto.Order;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO {
    private EntityManager em;

    public OrderDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Order> getAllOrders() {
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public List<Order> getOrderByOrderDate(LocalDate orderDate) {
        return em.createQuery("SELECT o FROM Order o WHERE o.orderDate = :orderDate", Order.class)
                .setParameter("orderDate", orderDate)
                .getResultList();
    }

    public Order getOrderById(int id) {
        return em.find(Order.class, id);
    }

    public boolean addOrder(Order order) {
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

    public List<Order> getOrdersByCustomerName(String customerName) {
        String query = "SELECT o from Order o where o.customer.name = :customerName";
        return em.createQuery(query, Order.class)
                .setParameter("customerName", customerName)
                .getResultList()
                .stream()
                .collect(Collectors.toList());
    }
}
