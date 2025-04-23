package interfaces;

import dto.Store;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStoreService extends Remote {
    List<Store> getAllStores() throws RemoteException;

    List<Store> getStoreByStoreName(String storeName) throws RemoteException;

    Store getStoreById(int id) throws RemoteException;

    boolean addStore(Store store) throws RemoteException;

    boolean updateStore(Store store) throws RemoteException;
}
