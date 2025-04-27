package interfaces;

import dto.Employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEmployeeService extends Remote {
    List<Employee> getAllEmployees() throws RemoteException;

    List<Employee> getEmployeeByName(String name) throws RemoteException;

    List<Employee> getEmployeeByPhone(String phone) throws RemoteException;

    List<Employee> getEmployeeBySalary(double salary) throws RemoteException;

    List<Employee> getEmployeeByStatus(boolean status) throws RemoteException;

    Employee getEmployeeById(int id) throws RemoteException;

    boolean addEmployee(Employee employee) throws RemoteException;

    boolean updateEmployee(Employee employee) throws RemoteException;

    List<Object[]> getEmployeeTotalSales() throws RemoteException;

    List<Object[]> getEmployeeOrderCounts() throws RemoteException;

    List<Object[]> getEmployeeAverageOrderValue() throws RemoteException;

    List<Object[]> getEmployeeProductSales() throws RemoteException;

    List<Object[]> getEmployeeProductSalesAndCount() throws  RemoteException;
}
