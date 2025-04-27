package dao;

import dto.OrderDetail;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailDAO {
    private EntityManager em;

    public OrderDetailDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<OrderDetail> getAllOrderDetails() {
        return em.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class)
                .getResultList();
    }

    public OrderDetail getOrderDetailById(int id) {
        return em.find(OrderDetail.class, id);
    }


    public Map<OrderDetail, Double> getOrderDetailAndTotalById(int orderId) {
        try {
            // Truy vấn JPQL để lấy danh sách OrderDetail và tính Subtotal.
            String jpql = "SELECT od, (od.quantity * od.price) AS subtotal " +
                    "FROM OrderDetail od " +
                    "WHERE od.order.id = :orderId";

            List<Object[]> results = em.createQuery(jpql)
                    .setParameter("orderId", orderId)
                    .getResultList();

            // Sử dụng HashMap để lưu trữ kết quả.
            Map<OrderDetail, Double> orderDetailSubtotalMap = new HashMap<>();
            for (Object[] result : results) {
                OrderDetail orderDetail = (OrderDetail) result[0];
                Double subtotal = (Double) result[1];
                orderDetailSubtotalMap.put(orderDetail, subtotal);
            }
            return orderDetailSubtotalMap;
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi
            return null; // hoặc throw exception nếu cần
        }
    }

    public boolean addOrderDetail(OrderDetail orderDetail) {
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
