package bus;

import dao.PurchaseOrderDetailDAO;
import dto.PurchaseOrderDetail;
import interfaces.IPurchaseOrderDetailService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDetailBUS extends UnicastRemoteObject implements IPurchaseOrderDetailService {
    private PurchaseOrderDetailDAO purchaseOrderDetailDAO;

    protected PurchaseOrderDetailBUS() throws RemoteException {
        purchaseOrderDetailDAO = new PurchaseOrderDetailDAO();
    }

    @Override
    public List<PurchaseOrderDetail> getAllPurchaseOrderDetails() throws RemoteException {
        return new ArrayList<>(purchaseOrderDetailDAO.getAllPurchaseOrderDetails());
    }

    @Override
    public PurchaseOrderDetail getPurchaseOrderDetailById(int id) throws RemoteException {
        return purchaseOrderDetailDAO.getPurchaseOrderDetailById(id);
    }

    @Override
    public boolean addPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) throws RemoteException {
        return purchaseOrderDetailDAO.addPurchaseOrderDetail(purchaseOrderDetail);
    }

    @Override
    public boolean updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) throws RemoteException {
        return purchaseOrderDetailDAO.updatePurchaseOrderDetail(purchaseOrderDetail);
    }
}
