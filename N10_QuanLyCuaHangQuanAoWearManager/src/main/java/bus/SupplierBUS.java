package bus;

import dao.SupplierDAO;
import dto.Supplier;
import interfaces.ISupplierService;

import java.io.IOException;
import java.nio.CharBuffer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class SupplierBUS extends UnicastRemoteObject implements ISupplierService {
    private SupplierDAO supplierDAO;

    public SupplierBUS() throws RemoteException {
        supplierDAO = new SupplierDAO();
    }

    @Override
    public List<Supplier> getAllSuppliers() throws RemoteException {
        return supplierDAO.getAllSuppliers();
    }

    @Override
    public List<Supplier> getSupplierBySupplierName(String supplierName) throws RemoteException {
        return supplierDAO.getSupplierBySupplierName(supplierName);
    }

    @Override
    public List<Supplier> getSupplierByPhone(String phone) throws RemoteException {
        return supplierDAO.getSupplierByPhone(phone);
    }

    @Override
    public List<Supplier> getSupplierByStatus(boolean status) throws RemoteException {
        return supplierDAO.getSupplierByStatus(status);
    }

    @Override
    public Supplier getSupplierById(int id) throws RemoteException {
        return supplierDAO.getSupplierById(id);
    }

    @Override
    public boolean addSupplier(Supplier supplier) throws RemoteException {
        return supplierDAO.addSupplier(supplier);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) throws RemoteException {
        return supplierDAO.updateSupplier(supplier);
    }
}
