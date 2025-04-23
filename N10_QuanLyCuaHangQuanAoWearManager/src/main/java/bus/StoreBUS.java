package bus;

import dao.StoreDAO;
import dto.Store;
import interfaces.IStoreService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class StoreBUS extends UnicastRemoteObject implements IStoreService {
    private StoreDAO storeDAO;

    public StoreBUS() throws RemoteException {
        storeDAO = new StoreDAO();
    }

    @Override
    public List<Store> getAllStores() throws RemoteException {
        return storeDAO.getAllStores();
    }

    @Override
    public List<Store> getStoreByStoreName(String storeName) throws RemoteException {
        return storeDAO.getStoreByStoreName(storeName);
    }

    @Override
    public Store getStoreById(int id) throws RemoteException {
        return storeDAO.getStoreById(id);
    }

    @Override
    public boolean addStore(Store store) throws RemoteException {
        return storeDAO.addStore(store);
    }

    @Override
    public boolean updateStore(Store store) throws RemoteException {
        return storeDAO.updateStore(store);
    }
}
