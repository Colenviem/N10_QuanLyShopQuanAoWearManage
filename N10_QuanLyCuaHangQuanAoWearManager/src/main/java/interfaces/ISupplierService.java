package interfaces;

import dto.Supplier;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISupplierService extends Remote {
    List<Supplier> getAllSuppliers() throws RemoteException;

    List<Supplier> getSupplierBySupplierName(String supplierName) throws RemoteException;

    List<Supplier> getSupplierByPhone(String phone) throws RemoteException;

    List<Supplier> getSupplierByStatus(boolean status) throws RemoteException;

    Supplier getSupplierById(int id) throws RemoteException;

    boolean addSupplier(Supplier supplier) throws RemoteException;

    boolean updateSupplier(Supplier supplier) throws RemoteException;
}
