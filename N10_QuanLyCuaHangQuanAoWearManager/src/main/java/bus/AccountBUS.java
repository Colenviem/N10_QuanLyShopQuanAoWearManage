package bus;

import dao.AccountDAO;
import dto.Account;
import interfaces.IAccountService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AccountBUS extends UnicastRemoteObject implements IAccountService {
    private AccountDAO accountDao;

    public AccountBUS() throws RemoteException {
        accountDao = new AccountDAO();
    }

    @Override
    public List<Account> getAllAccounts() throws RemoteException {
        return new ArrayList<>(accountDao.getAllAccounts());
    }

    @Override
    public Account getAccountById(int id) throws RemoteException {
        return accountDao.getAccountById(id);
    }

    @Override
    public boolean addAccount(Account account) throws RemoteException {
        return accountDao.addAccount(account);
    }

    @Override
    public boolean updateAccount(Account account) throws RemoteException {
        return accountDao.updateAccount(account);
    }

    @Override
    public List<Account> getAccountsNotInEmployee() throws RemoteException {
        return new ArrayList<>(accountDao.getAccountsNotInEmployee());
    }

    @Override
    public List<Account> getAccountByUsername(String username) throws RemoteException {
        return new ArrayList<>(accountDao.getAccountByUsername(username));
    }

    @Override
    public List<Account> getAllActiveAccounts() throws RemoteException {
        return new ArrayList<>(accountDao.getAllActiveAccounts());
    }

    @Override
    public boolean checkAccountExists(String username, String password) throws RemoteException {
        return accountDao.checkAccountExists(username, password);
    }
}
