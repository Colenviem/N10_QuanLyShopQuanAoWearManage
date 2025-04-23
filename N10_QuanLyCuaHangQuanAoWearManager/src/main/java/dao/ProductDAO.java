package dao;

import dto.Product;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class ProductDAO {
    private EntityManager em;

    public ProductDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Product> getProductByName(String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.productName = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> getProductByColor(String color) {
        return em.createQuery("SELECT p FROM Product p WHERE p.color = :color", Product.class)
                .setParameter("color", color)
                .getResultList();
    }

    public List<Product> getProductBySize(String size) {
        return em.createQuery("SELECT p FROM Product p WHERE p.size = :size", Product.class)
                .setParameter("size", size)
                .getResultList();
    }

    public List<Product> getProductByStatus(boolean status) {
        return em.createQuery("SELECT p FROM Product p WHERE p.status = :status", Product.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Product> getAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public Product getProductById(int id) {
        return em.find(Product.class, id);
    }

    public boolean addProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(String id) {
        try {
            Product product = em.find(Product.class, id);
            if (product == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
