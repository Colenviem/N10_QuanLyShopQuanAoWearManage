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

    List<Object[]> getOrderSummaries() throws RemoteException;

    double getTotalRevenue(String timePeriod) throws RemoteException;

    double getAverageOrderValue(String timePeriod) throws RemoteException;

    double[] getAverageOrderValueAndGrowth(String timePeriod) throws RemoteException;

    List<Object[]> getOrderSummariesForYear(int year) throws RemoteException;

    List<Object[]> getStoreRevenueByDayOfWeek(LocalDate startDate, LocalDate endDate) throws RemoteException;

    List<Object[]> getTop5BestSellingProductsThisWeek(LocalDate startDate, LocalDate endDate) throws RemoteException;

    List<Object[]> getAllProductRevenueThisWeek(LocalDate startDate, LocalDate endDate) throws RemoteException;
}
