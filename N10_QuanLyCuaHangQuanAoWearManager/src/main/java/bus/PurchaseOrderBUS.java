package bus;

import dao.PurchaseOrderDAO;
import dto.PurchaseOrder;
import interfaces.IPurchaseOrderService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderBUS extends UnicastRemoteObject implements IPurchaseOrderService {
    private PurchaseOrderDAO purchaseOrderDAO;

    public PurchaseOrderBUS() throws RemoteException {
        purchaseOrderDAO = new PurchaseOrderDAO();
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() throws RemoteException {
        return purchaseOrderDAO.getAllPurchaseOrders();
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderByOrderDate(LocalDate orderDate) throws RemoteException {
        return purchaseOrderDAO.getPurchaseOrderByOrderDate(orderDate);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderByStatus(boolean status) throws RemoteException {
        return purchaseOrderDAO.getPurchaseOrderByStatus(status);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(int id) throws RemoteException {
        return purchaseOrderDAO.getPurchaseOrderById(id);
    }

    @Override
    public boolean addPurchaseOrder(PurchaseOrder purchaseOrder) throws RemoteException {
        return purchaseOrderDAO.addPurchaseOrder(purchaseOrder);
    }

    @Override
    public boolean updatePurchaseOrder(PurchaseOrder purchaseOrder) throws RemoteException {
        return purchaseOrderDAO.updatePurchaseOrder(purchaseOrder);
    }
}
