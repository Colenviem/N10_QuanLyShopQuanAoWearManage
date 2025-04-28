package dao;

import dto.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.JPAUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO {

    private EntityManager em;

    public OrderDAO() {
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

    public List<Object[]> getOrderSummaries() {
        String query = "SELECT " +
                "o.id, o.orderDate, c.phone, c.name, o.status, " +
                "p.productName, od.price, od.quantity, o.totalAmount, od.subTotal " +
                "FROM Order o " +
                "JOIN o.orderDetails od " +
                "JOIN od.product p " +
                "JOIN o.customer c " +
                "ORDER BY o.orderDate ASC"; // Sắp xếp theo ngày tạo hóa đơn (tăng dần)

        return em.createQuery(query, Object[].class)
                .getResultList();
    }

    public List<Object[]> getOrderSummariesForYear(int year) {
        String jpql = "SELECT o.id, o.orderDate, c.phone, c.name, o.status, " +
                "p.productName, od.price, od.quantity, o.totalAmount, od.subTotal " +
                "FROM Order o JOIN OrderDetail od on o.id = od.id " +
                "JOIN Product p on p.id = od.id " +
                "JOIN Customer c on c.id = o.id " +
                "WHERE EXTRACT(YEAR FROM o.orderDate) = :year " +
                "ORDER BY o.orderDate ASC";

        return em.createQuery(jpql, Object[].class)
                .setParameter("year", year)
                .setMaxResults(7)
                .getResultList();
    }

    public double getAverageOrderValue(String timePeriod) {
        try {
            String jpql;
            LocalDate startDate;
            LocalDate endDate;

            // Xác định khoảng thời gian
            switch (timePeriod.toLowerCase()) {
                case "tháng":
                    YearMonth currentMonth = YearMonth.now();
                    startDate = currentMonth.atDay(1);
                    endDate = currentMonth.atEndOfMonth();
                    break;
                case "quý":
                    int quarter = (LocalDate.now().getMonthValue() - 1) / 3 + 1;
                    startDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3) - 2, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3), LocalDate.of(LocalDate.now().getYear(), (quarter * 3), 1).lengthOfMonth());
                    break;
                case "năm":
                    startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
                    break;
                default:
                    return 0; // Trả về 0 nếu không hợp lệ
            }

            // Truy vấn JPQL để tính giá trị đơn hàng trung bình
            jpql = "SELECT AVG(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate";
            Query query = em.createQuery(jpql);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            // Kết quả có thể là null, cần kiểm tra
            Double result = (Double) query.getSingleResult();
            return (result != null) ? result : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Phương thức lấy giá trị đơn hàng trung bình và tính tăng trưởng
    public double[] getAverageOrderValueAndGrowth(String timePeriod) {
        try {
            String jpql;
            LocalDate startDate;
            LocalDate endDate;
            LocalDate previousStartDate;
            LocalDate previousEndDate;

            // Xác định khoảng thời gian
            switch (timePeriod.toLowerCase()) {
                case "tháng":
                    YearMonth currentMonth = YearMonth.now();
                    startDate = currentMonth.atDay(1);
                    endDate = currentMonth.atEndOfMonth();
                    previousEndDate = startDate.minusDays(1);
                    previousStartDate = previousEndDate.withDayOfMonth(1);
                    break;
                case "quý":
                    int quarter = (LocalDate.now().getMonthValue() - 1) / 3 + 1;
                    startDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3) - 2, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3), LocalDate.of(LocalDate.now().getYear(), (quarter * 3), 1).lengthOfMonth());
                    previousEndDate = startDate.minusDays(1);
                    previousStartDate = LocalDate.of(previousEndDate.getYear(), (quarter * 3) - 5, 1);
                    break;
                case "năm":
                    startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
                    previousEndDate = startDate.minusDays(1);
                    previousStartDate = LocalDate.of(previousEndDate.getYear(), 1, 1);
                    break;
                default:
                    return new double[]{0, 0}; // Trả về 0 nếu không hợp lệ
            }

            // Truy vấn JPQL để tính giá trị đơn hàng trung bình hiện tại
            jpql = "SELECT AVG(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate";
            Query query = em.createQuery(jpql);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            Double currentAverage = (Double) query.getSingleResult();
            currentAverage = (currentAverage != null) ? currentAverage : 0;

            // Truy vấn JPQL để tính giá trị đơn hàng trung bình trước đó
            jpql = "SELECT AVG(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :previousStartDate AND :previousEndDate";
            Query previousQuery = em.createQuery(jpql);
            previousQuery.setParameter("previousStartDate", previousStartDate);
            previousQuery.setParameter("previousEndDate", previousEndDate);
            Double previousAverage = (Double) previousQuery.getSingleResult();
            previousAverage = (previousAverage != null) ? previousAverage : 0;

            // Tính phần trăm tăng trưởng
            double growthPercentage = (previousAverage != 0) ? ((currentAverage - previousAverage) / previousAverage) * 100 : 0;

            return new double[]{currentAverage, growthPercentage};

        } catch (Exception e) {
            e.printStackTrace();
            return new double[]{0, 0};
        }
    }

    public double getTotalRevenue(String timePeriod) {
        try {
            String jpql;
            LocalDate startDate;
            LocalDate endDate;

            // Xác định khoảng thời gian
            switch (timePeriod.toLowerCase()) {
                case "tháng":
                    YearMonth currentMonth = YearMonth.now();
                    startDate = currentMonth.atDay(1);
                    endDate = currentMonth.atEndOfMonth();
                    break;
                case "quý":
                    int quarter = (LocalDate.now().getMonthValue() - 1) / 3 + 1;
                    startDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3) - 2, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), (quarter * 3), LocalDate.of(LocalDate.now().getYear(), (quarter * 3), 1).lengthOfMonth());
                    break;
                case "năm":
                    startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
                    break;
                default:
                    return 0; // Trả về 0 nếu không hợp lệ
            }

            // Truy vấn JPQL để tính tổng doanh thu
            jpql = "SELECT SUM(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate";
            Query query = em.createQuery(jpql);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            // Kết quả có thể là null, cần kiểm tra
            Double result = (Double) query.getSingleResult();
            return (result != null) ? result : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        String query = "SELECT o FROM Order o WHERE o.customer.name LIKE :customerName";
        return em.createQuery(query, Order.class)
                .setParameter("customerName", "%" + customerName + "%")
                .getResultList()
                .stream()
                .collect(Collectors.toList());
    }

}