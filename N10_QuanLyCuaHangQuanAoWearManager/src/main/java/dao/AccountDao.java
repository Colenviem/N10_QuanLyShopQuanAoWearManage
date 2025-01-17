package dao;

import dto.Account;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

// Thuộc tính của Lombok, tự động sinh constructor có tham số cho tất cả các trường trong lớp.
@AllArgsConstructor
public class AccountDao {
    private EntityManager em;

    public List<Account> getAllAccounts() {
        return em.createQuery("SELECT a FROM Account a", Account.class)
                .getResultList();
    }

    public List<Account> getAccountByUsername(String username) {
        return em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
                .setParameter("username", username)
                .getResultList();
    }

    public Account getAccountById(String id) {
        return em.find(Account.class, id);
    }

    public boolean saveAccount(Account account) {
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(Account account) {
        try {
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(String id) {
        try {
            Account account = em.find(Account.class, id);
            if (account == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
