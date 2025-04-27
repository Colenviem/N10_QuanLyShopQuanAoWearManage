package dao;

import dto.Category;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class CategoryDAO {
    private EntityManager em;

    public CategoryDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Category> getAllCategories() {
        return em.createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }
    public List<Category> getCategoryByName(String name) {
        return em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :name", Category.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }


    public Category getCategoryById(String id) {
        return em.find(Category.class, id);
    }

    public boolean addCategory(Category category) {
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(Category category) {
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(String id) {
        try {
            Category category = em.find(Category.class, id);
            if (category == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
