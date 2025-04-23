package interfaces;

import dto.PurchaseOrder;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface IPurchaseOrderService extends Remote {
    List<PurchaseOrder> getAllPurchaseOrders() throws RemoteException;

    List<PurchaseOrder> getPurchaseOrderByOrderDate(LocalDate orderDate) throws RemoteException;

    List<PurchaseOrder> getPurchaseOrderByStatus(boolean status) throws RemoteException;

    PurchaseOrder getPurchaseOrderById(int id) throws RemoteException;

    boolean addPurchaseOrder(PurchaseOrder purchaseOrder) throws RemoteException;

    boolean updatePurchaseOrder(PurchaseOrder purchaseOrder) throws RemoteException;
}
