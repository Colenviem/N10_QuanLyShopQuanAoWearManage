package dao;

import dto.Employee;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAO {
    private EntityManager em;

    public EmployeeDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Employee> getAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }

    public List<Employee> getEmployeeByName(String name) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.fullName LIKE :name", Employee.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<Employee> getEmployeeByPhone(String phone) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.phone LIKE :phone", Employee.class)
                .setParameter("phone", "%" + phone + "%")
                .getResultList();
    }


    public List<Employee> getEmployeeBySalary(double salary) {
        double range = 1000.0; // khoảng chênh lệch cho phép, ví dụ 1000
        return em.createQuery("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary", Employee.class)
                .setParameter("minSalary", salary - range)
                .setParameter("maxSalary", salary + range)
                .getResultList();
    }


    public List<Employee> getEmployeeByStatus(boolean status) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.status = :status", Employee.class)
                .setParameter("status", status)
                .getResultList();
    }

    // Thống kê tổng giá trị bán hàng của mỗi nhân viên
    public List<Object[]> getEmployeeTotalSales() {
        return em.createQuery("SELECT e.id, e.fullName, SUM(o.totalAmount) " +
                "FROM Employee e LEFT JOIN Order o ON e.id = o.id " +
                "GROUP BY e.id, e.fullName " +
                "ORDER BY SUM(o.totalAmount) DESC", Object[].class).setMaxResults(7).getResultList();
    }

    // Thống kê số lượng đơn hàng bán được của mỗi nhân viên
    public List<Object[]> getEmployeeOrderCounts() {
        return em.createQuery("SELECT e.id, e.fullName, COUNT(o.id) " +
                        "FROM Employee e LEFT JOIN Order o ON e.id = o.id " +
                        "GROUP BY e.id, e.fullName " +
                        "ORDER BY COUNT(o.id) DESC", Object[].class)
                .setMaxResults(7)
                .getResultList();
    }

    public List<Object[]> getEmployeeProductSales() {
        return em.createQuery("SELECT e.id, e.fullName, SUM(oi.quantity * oi.price), SUM(oi.quantity) " +
                "FROM Employee e JOIN Order o ON e.id = o.id " +
                "JOIN OrderDetail oi ON o.id = oi.id " +
                "GROUP BY e.id, e.fullName " +
                "ORDER BY e.id", Object[].class).setMaxResults(7).getResultList();
    }

    // Thống kê giá trị trung bình mỗi đơn hàng của mỗi nhân viên
    public List<Object[]> getEmployeeAverageOrderValue() {
        return em.createQuery("SELECT e.id, e.fullName, AVG(o.totalAmount) " +
                "FROM Employee e LEFT JOIN Order o ON e.id = o.id " +
                "GROUP BY e.id, e.fullName " +
                "ORDER BY AVG(o.totalAmount) DESC", Object[].class).setMaxResults(7).getResultList();
    }

    public Employee getEmployeeByAccount(String user, String pass) {
        String query = "select e from Employee e " +
                "where e.account.username = :user " +
                "and e.account.password = :pass";
        return em.createQuery(query, Employee.class)
                .setParameter("user", user)
                .setParameter("pass", pass)
                .getSingleResult();
    }

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        System.out.println(employeeDAO.getEmployeeByAccount("lino.cassin", "r6me33hyp47cm"));
    }

    public List<Object[]> getEmployeeProductSalesAndCount() {
        return em.createQuery("SELECT e.id, e.fullName, SUM(oi.quantity * oi.price), SUM(oi.quantity) " +
                "FROM Employee e JOIN Order o ON e.id = o.id " +
                "JOIN OrderDetail oi ON o.id = oi.id " +
                "GROUP BY e.id, e.fullName " +
                "ORDER BY e.id", Object[].class).setMaxResults(7).getResultList();
    }

    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id);
    }

    public boolean addEmployee(Employee employee) {
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) {
        try {
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        try {
            Employee employee = em.find(Employee.class, id);
            if (employee == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
    // Lấy tổng số lượng và tổng giá trị sản phẩm bán của từng nhân viên (cho bảng)
    public List<Object[]> getEmployeeTotalProductStatsThisWeek(LocalDate startDate, LocalDate endDate) {
        return em.createQuery("SELECT e.id, e.fullName, SUM(od.quantity), SUM(od.quantity * od.price) " +
                        "FROM Employee e " +
                        "JOIN Order o ON e.id = o.employee.id " +
                        "JOIN OrderDetail od ON o.id = od.order.id " +
                        "WHERE o.orderDate >= :startDate AND o.orderDate <= :endDate " +
                        "GROUP BY e.id, e.fullName " +
                        "ORDER BY SUM(od.quantity) DESC", Object[].class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();


    }

    public List<Object[]> getTop5BestSellingProductsThisWeek(LocalDate startDate, LocalDate endDate) {
        return em.createQuery("SELECT p.id, p.productName, SUM(od.quantity) " +
                        "FROM Product p " +
                        "JOIN OrderDetail od ON p.id = od.product.id " +
                        "JOIN Order o ON od.order.id = o.id " +
                        "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
                        "GROUP BY p.id, p.productName " +
                        "ORDER BY SUM(od.quantity) DESC", Object[].class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setMaxResults(5)
                .getResultList();
    }



    // Lấy số lượng sản phẩm bán theo ngày trong tuần của mỗi nhân viên (cho line chart aggregation)
    public List<Object[]> getEmployeeProductSalesCountByDayOfWeek(LocalDate startDate, LocalDate endDate) {
        List<Object[]> queryResult = em.createQuery(
                        "SELECT FUNCTION('DAYOFWEEK', o.orderDate), SUM(od.quantity) " +
                                "FROM Employee e " +
                                "JOIN Order o ON e.id = o.employee.id " +
                                "JOIN OrderDetail od ON o.id = od.order.id " +
                                "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
                                "GROUP BY FUNCTION('DAYOFWEEK', o.orderDate) " +
                                "ORDER BY FUNCTION('DAYOFWEEK', o.orderDate)",
                        Object[].class
                )
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();

        Map<Integer, Long> employeeSalesByDay = new HashMap<>();
        for (Object[] row : queryResult) {
            Integer dayOfWeek = ((Number) row[0]).intValue();
            Long totalQuantity = (row[1] != null) ? ((Number) row[1]).longValue() : 0L;
            employeeSalesByDay.put(dayOfWeek, totalQuantity);
        }
        List<Object[]> resultList = new ArrayList<>();
        for (int day = 2; day <= 7; day++) {
            Long quantity = employeeSalesByDay.getOrDefault(day, 0L);
            resultList.add(new Object[]{day, quantity});
        }
        Long sunday= employeeSalesByDay.getOrDefault(1, 0L);
        resultList.add(new Object[]{1, sunday});
        return resultList;

    }
}
