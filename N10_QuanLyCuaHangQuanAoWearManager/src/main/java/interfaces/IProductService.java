package interfaces;

import dto.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProductService extends Remote {
    List<Product> getProductByName(String name) throws RemoteException;

    List<Product> getProductByColor(String color) throws RemoteException;

    List<Product> getProductBySize(String size) throws RemoteException;

    List<Product> getProductByStatus(boolean status) throws RemoteException;

    List<Product> getAllProducts() throws RemoteException;

    Product getProductById(int id) throws RemoteException;

    boolean addProduct(Product product) throws RemoteException;

    boolean updateProduct(Product product) throws RemoteException;

    List<Object[]> getProductDetails() throws RemoteException;

    int getTotalProductsSold(String timePeriod) throws RemoteException;

    List<Object[]> getProductDetailsDashboard() throws  RemoteException;
}

