package dao;

import dto.Customer;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class CustomerDAO {
    private EntityManager em;

    public CustomerDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    public List<Customer> getCustomerByName(String name) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.name = :name", Customer.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Customer> getCustomerByPhone(String phone) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.phone = :phone", Customer.class)
                .setParameter("phone", phone)
                .getResultList();
    }

    public Customer getCustomerById(int id) {
        return em.find(Customer.class, id);
    }

    public boolean addCustomer(Customer customer) {
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customer customer) {
        try {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int id) {
        try {
            Customer customer = em.find(Customer.class, id);
            if (customer == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
