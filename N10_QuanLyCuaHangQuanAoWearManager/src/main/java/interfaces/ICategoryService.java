package interfaces;

import dto.Category;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICategoryService extends Remote {
    List<Category> getAllCategories() throws RemoteException;

    List<Category> getCategoryByName(String name) throws RemoteException;

    Category getCategoryById(String id) throws RemoteException;

    boolean addCategory(Category category) throws RemoteException;

    boolean updateCategory(Category category) throws RemoteException;
}
