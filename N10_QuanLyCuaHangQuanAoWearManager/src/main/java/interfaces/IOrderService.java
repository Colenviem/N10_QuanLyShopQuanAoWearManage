package interfaces;

import dto.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface IOrderService extends Remote {
    List<Order> getAllOrders() throws RemoteException;

    List<Order> getOrderByOrderDate(LocalDate orderDate) throws RemoteException;

    Order getOrderById(int id) throws RemoteException;

    boolean addOrder(Order order) throws RemoteException;

    boolean updateOrder(Order order) throws RemoteException;

    List<Order> getOrdersByCustomerName(String customerName) throws RemoteException;
}
