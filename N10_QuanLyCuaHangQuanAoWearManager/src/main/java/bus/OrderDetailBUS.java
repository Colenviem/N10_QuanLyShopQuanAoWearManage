package bus;

import dao.OrderDetailDAO;
import dto.OrderDetail;
import interfaces.IOrderDetailService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OrderDetailBUS extends UnicastRemoteObject implements IOrderDetailService {
    private OrderDetailDAO orderDetailDAO;

    public OrderDetailBUS() throws RemoteException {
        orderDetailDAO = new OrderDetailDAO();
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() throws RemoteException {
        return orderDetailDAO.getAllOrderDetails();
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws RemoteException {
        return orderDetailDAO.getOrderDetailById(id);
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
