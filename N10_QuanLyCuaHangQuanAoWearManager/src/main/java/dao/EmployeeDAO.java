package dao;

import dto.Employee;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

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
}
