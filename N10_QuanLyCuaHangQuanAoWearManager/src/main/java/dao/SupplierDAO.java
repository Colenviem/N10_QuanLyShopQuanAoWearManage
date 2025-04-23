package dao;

import dto.Supplier;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class SupplierDAO {
    private EntityManager em;

    public SupplierDAO(){
        em = JPAUtil.getEntityManager();
    }

    public List<Supplier> getAllSuppliers() {
        return em.createQuery("SELECT s FROM Supplier s", Supplier.class)
                .getResultList();
    }

    public List<Supplier> getSupplierBySupplierName(String supplierName) {
        return em.createQuery("SELECT s FROM Supplier s WHERE s.supplierName = :supplierName", Supplier.class)
                .setParameter("supplierName", supplierName)
                .getResultList();
    }

    public List<Supplier> getSupplierByPhone(String phone) {
        return em.createQuery("SELECT s FROM Supplier s WHERE s.phone = :phone", Supplier.class)
                .setParameter("phone", phone)
                .getResultList();
    }

    public List<Supplier> getSupplierByStatus(boolean status) {
        return em.createQuery("SELECT s FROM Supplier s WHERE s.status = :status", Supplier.class)
                .setParameter("status", status)
                .getResultList();
    }

    public Supplier getSupplierById(int id) {
        return em.find(Supplier.class, id);
    }

    public boolean addSupplier(Supplier supplier) {
        try {
            em.getTransaction().begin();
            em.persist(supplier);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(Supplier supplier) {
        try {
            em.getTransaction().begin();
            em.merge(supplier);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(String id) {
        try {
            Supplier supplier = em.find(Supplier.class, id);
            if (supplier == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(supplier);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
