package interfaces;

import dto.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICustomerService extends Remote {
    List<Customer> getAllCustomers() throws RemoteException;

    List<Customer> getCustomerByName(String name) throws RemoteException;

    List<Customer> getCustomerByPhone(String phone) throws RemoteException;

    Customer getCustomerById(int id) throws RemoteException;

    boolean addCustomer(Customer customer) throws RemoteException;

    boolean updateCustomer(Customer customer) throws RemoteException;
}
