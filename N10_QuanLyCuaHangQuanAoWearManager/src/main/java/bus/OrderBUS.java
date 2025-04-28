package bus;

import dao.OrderDAO;
import dto.Order;
import interfaces.IOrderService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBUS extends UnicastRemoteObject implements IOrderService {
    private OrderDAO orderDAO;

    public OrderBUS() throws RemoteException {
        orderDAO = new OrderDAO();
    }

    @Override
    public List<Order> getAllOrders() throws RemoteException {
        return new ArrayList<>(orderDAO.getAllOrders());
    }

    @Override
    public List<Order> getOrderByOrderDate(LocalDate orderDate) throws RemoteException {
        return new ArrayList<>(orderDAO.getOrderByOrderDate(orderDate));
    }

    @Override
    public Order getOrderById(int id) throws RemoteException {
        return orderDAO.getOrderById(id);
    }

    @Override
    public boolean addOrder(Order order) throws RemoteException {
        return orderDAO.addOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) throws RemoteException {
        return orderDAO.updateOrder(order);
    }

    @Override
    public List<Order> getOrdersByCustomerName(String customerName) throws RemoteException {
        return new ArrayList<>(orderDAO.getOrdersByCustomerName(customerName));
    }

    @Override
    public List<Object[]> getOrderSummaries() throws RemoteException {
        return new ArrayList<>(orderDAO.getOrderSummaries());
    }

    @Override
    public double getTotalRevenue(String timePeriod) throws RemoteException{
        return orderDAO.getTotalRevenue(timePeriod);
    }

    @Override
    public double getAverageOrderValue(String timePeriod) throws RemoteException{
        return orderDAO.getAverageOrderValue(timePeriod);
    }

    @Override
    public double[] getAverageOrderValueAndGrowth(String timePeriod) throws RemoteException{
        return orderDAO.getAverageOrderValueAndGrowth(timePeriod);
    }

    @Override
    public List<Object[]> getOrderSummariesForYear(int year) throws RemoteException{
        return new ArrayList<>(orderDAO.getOrderSummariesForYear(year));
    }
}