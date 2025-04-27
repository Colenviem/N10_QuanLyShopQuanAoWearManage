package interfaces;

import dto.OrderDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IOrderDetailService extends Remote {
    List<OrderDetail> getAllOrderDetails() throws RemoteException;

    OrderDetail getOrderDetailById(int id) throws RemoteException;

    Map<OrderDetail, Double> getOrderDetailAndTotalById(int orderId) throws RemoteException;

    boolean addOrderDetail(OrderDetail orderDetail) throws RemoteException;

    boolean updateOrderDetail(OrderDetail orderDetail) throws RemoteException;
}
