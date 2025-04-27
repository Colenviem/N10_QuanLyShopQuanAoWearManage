package bus;

import dao.ProductDAO;
import dto.Product;
import interfaces.IProductService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProductBUS extends UnicastRemoteObject implements IProductService {
    private ProductDAO productDAO;

    public ProductBUS() throws RemoteException {
        productDAO = new ProductDAO();
    }

    @Override
    public List<Product> getProductByName(String name) throws RemoteException {
        return productDAO.getProductByName(name);
    }

    @Override
    public List<Product> getProductByColor(String color) throws RemoteException {
        return productDAO.getProductByColor(color);
    }

    @Override
    public List<Product> getProductBySize(String size) throws RemoteException {
        return productDAO.getProductBySize(size);
    }

    @Override
    public List<Product> getProductByStatus(boolean status) throws RemoteException {
        return productDAO.getProductByStatus(status);
    }

    @Override
    public List<Product> getAllProducts() throws RemoteException {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) throws RemoteException {
        return productDAO.getProductById(id);
    }

    @Override
    public boolean addProduct(Product product) throws RemoteException {
        return productDAO.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) throws RemoteException {
        return productDAO.updateProduct(product);
    }

    @Override
    public List<Object[]> getProductDetails() throws RemoteException {
        return productDAO.getProductDetails();
    }

    @Override
    public int getTotalProductsSold(String timePeriod) throws RemoteException{
        return productDAO.getTotalProductsSold(timePeriod);
    }

    @Override
    public List<Object[]> getProductDetailsDashboard() throws  RemoteException{
        return productDAO.getProductDetailsDashboard();
    }
}
