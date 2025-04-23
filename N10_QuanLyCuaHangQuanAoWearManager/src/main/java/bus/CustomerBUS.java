package bus;

import dao.CustomerDAO;
import dto.Customer;
import interfaces.ICustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CustomerBUS extends UnicastRemoteObject implements ICustomerService {
    private CustomerDAO customerDAO;

    public CustomerBUS() throws RemoteException {
        customerDAO = new CustomerDAO();
    }

    @Override
    public List<Customer> getAllCustomers() throws RemoteException {
        return customerDAO.getAllCustomers();
    }

    @Override
    public List<Customer> getCustomerByName(String name) throws RemoteException {
        return customerDAO.getCustomerByName(name);
    }

    @Override
    public List<Customer> getCustomerByPhone(String phone) throws RemoteException {
        return customerDAO.getCustomerByPhone(phone);
    }

    @Override
    public Customer getCustomerById(int id) throws RemoteException {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public boolean addCustomer(Customer customer) throws RemoteException {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws RemoteException {
        return customerDAO.updateCustomer(customer);
    }
}
