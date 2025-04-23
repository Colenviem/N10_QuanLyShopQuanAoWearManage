package interfaces;

import dto.PurchaseOrderDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPurchaseOrderDetailService extends Remote {
    List<PurchaseOrderDetail> getAllPurchaseOrderDetails() throws RemoteException;

    PurchaseOrderDetail getPurchaseOrderDetailById(int id) throws RemoteException;

    boolean addPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) throws RemoteException;

    boolean updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) throws RemoteException;
}
