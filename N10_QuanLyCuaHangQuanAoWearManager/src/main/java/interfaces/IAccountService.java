package interfaces;

import dto.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAccountService extends Remote {
    List<Account> getAllAccounts() throws RemoteException;

    Account getAccountById(int id) throws RemoteException;

    boolean addAccount(Account account) throws RemoteException;

    boolean updateAccount(Account account) throws RemoteException;

    List<Account> getAccountsNotInEmployee() throws RemoteException;

    List<Account> getAccountByUsername(String username) throws RemoteException;

    List<Account> getAllActiveAccounts() throws RemoteException;
}
