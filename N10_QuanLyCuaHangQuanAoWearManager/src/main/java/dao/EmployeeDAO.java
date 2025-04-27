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
        return em.createQuery("SELECT e FROM Employee e WHERE e.fullName = :name", Employee.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Employee> getEmployeeByPhone(String phone) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.phone = :phone", Employee.class)
                .setParameter("phone", phone)
                .getResultList();
    }

    public List<Employee> getEmployeeBySalary(double salary) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.salary = :salary", Employee.class)
                .setParameter("salary", salary)
                .getResultList();
    }

    public List<Employee> getEmployeeByStatus(boolean status) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.status = :status", Employee.class)
                .setParameter("status", status)
                .getResultList();
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
