package bus;

import dao.ProductDAO;
import dto.Product;
import interfaces.IProductService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ProductBUS extends UnicastRemoteObject implements IProductService {
    private ProductDAO productDAO;

    public ProductBUS() throws RemoteException {
        productDAO = new ProductDAO();
    }

    @Override
    public List<Product> getProductByName(String name) throws RemoteException {
        List<Product> products = productDAO.getProductByName(name);
        if (products.isEmpty()) {
            return null;
        }
        return products;
    }

    @Override
    public List<Product> getProductByColor(String color) throws RemoteException {
        return new ArrayList<>(productDAO.getProductByColor(color));
    }

    @Override
    public List<Product> getProductBySize(String size) throws RemoteException {
        return new ArrayList<>(productDAO.getProductBySize(size));
    }

    @Override
    public List<Product> getProductByStatus(boolean status) throws RemoteException {
        return new ArrayList<>(productDAO.getProductByStatus(status));
    }

    @Override
    public List<Product> getAllProducts() throws RemoteException {
        return new ArrayList<>(productDAO.getAllProducts());
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
        return new ArrayList<>(productDAO.getProductDetails());
    }

    @Override
    public int getTotalProductsSold(String timePeriod) throws RemoteException{
        return productDAO.getTotalProductsSold(timePeriod);
    }

    @Override
    public List<Object[]> getProductDetailsDashboard() throws  RemoteException{
        return new ArrayList<>(productDAO.getProductDetailsDashboard());
    }
}
