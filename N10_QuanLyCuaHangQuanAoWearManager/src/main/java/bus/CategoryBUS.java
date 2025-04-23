package bus;

import dao.CategoryDAO;
import dto.Category;
import interfaces.ICategoryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CategoryBUS extends UnicastRemoteObject implements ICategoryService {
    private CategoryDAO categoryDAO;

    public CategoryBUS() throws RemoteException {
        categoryDAO = new CategoryDAO();
    }

    @Override
    public List<Category> getAllCategories() throws RemoteException {
        return List.of();
    }

    @Override
    public List<Category> getCategoryByName(String name) throws RemoteException {
        return List.of();
    }

    @Override
    public Category getCategoryById(String id) throws RemoteException {
        return null;
    }

    @Override
    public boolean addCategory(Category category) throws RemoteException {
        return false;
    }

    @Override
    public boolean updateCategory(Category category) throws RemoteException {
        return false;
    }
}
