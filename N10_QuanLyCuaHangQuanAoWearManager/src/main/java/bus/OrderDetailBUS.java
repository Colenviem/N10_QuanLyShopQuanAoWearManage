package bus;

import dao.OrderDetailDAO;
import dto.OrderDetail;
import interfaces.IOrderDetailService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailBUS extends UnicastRemoteObject implements IOrderDetailService {
    private OrderDetailDAO orderDetailDAO;

    public OrderDetailBUS() throws RemoteException {
        orderDetailDAO = new OrderDetailDAO();
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() throws RemoteException {
        return new ArrayList<>(orderDetailDAO.getAllOrderDetails());
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws RemoteException {
        return orderDetailDAO.getOrderDetailById(id);
    }

    @Override
    public Map<OrderDetail, Double> getOrderDetailAndTotalById(int orderId) throws RemoteException{
        return new HashMap<>(orderDetailDAO.getOrderDetailAndTotalById(orderId));
    }

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) throws RemoteException {
        return orderDetailDAO.addOrderDetail(orderDetail);
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) throws RemoteException {
        return orderDetailDAO.updateOrderDetail(orderDetail);
    }
}
