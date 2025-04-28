package dao;

import dto.Account;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class AccountDAO {
    private EntityManager em;

    public AccountDAO() {
        em = JPAUtil.getEntityManager();
    }

    public List<Account> getAllAccounts() {
        return em.createQuery("SELECT a FROM Account a", Account.class)
                .getResultList();
    }

    public List<Account> getAllActiveAccounts() {
        return em.createQuery("SELECT a FROM Account a WHERE a.status = true", Account.class)
                .getResultList();
    }

    public List<Account> getAccountByUsername(String username) {
        return em.createQuery("SELECT a FROM Account a WHERE a.username LIKE :username", Account.class)
                .setParameter("username", "%" + username + "%")
                .getResultList();
    }


    public Account getAccountById(int id) {
        return em.find(Account.class, id);
    }

    public boolean addAccount(Account account) {
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

    public List<Account> getAccountsNotInEmployee() {
        String query = "select a from Account a where a.id not in (select e.account.id from Employee e)";
        return em.createQuery(query, Account.class)
                .getResultList();
    }

    public boolean checkAccountExists(String username, String password) {
        try {
            String query = "SELECT a FROM Account a WHERE a.username = :username AND a.password = :password";
            List<Account> accounts = em.createQuery(query, Account.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getResultList();
            return !accounts.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(int id) {
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

    public static void main(String[] args) throws Exception {
        AccountDAO accountDAO = new AccountDAO();

        Account account = accountDAO.getAccountById(1);

        account.setUsername("updatedUser");

        if (accountDAO.addAccount(account)) {
            System.out.println("Account updated successfully");
        } else {
            System.out.println("Failed to update account");
        }
    }
}
