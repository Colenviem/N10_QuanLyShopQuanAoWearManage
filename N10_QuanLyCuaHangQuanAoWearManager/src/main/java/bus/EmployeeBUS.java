package bus;

import dao.EmployeeDAO;
import dto.Employee;
import interfaces.IEmployeeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBUS extends UnicastRemoteObject implements IEmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeBUS() throws RemoteException {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        return new ArrayList<>(employeeDAO.getAllEmployees());
    }

    @Override
    public List<Employee> getEmployeeByName(String name) throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeByName(name));
    }

    @Override
    public List<Employee> getEmployeeByPhone(String phone) throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeByPhone(phone));
    }

    @Override
    public List<Employee> getEmployeeBySalary(double salary) throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeBySalary(salary));
    }

    @Override
    public List<Employee> getEmployeeByStatus(boolean status) throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeByStatus(status));
    }

    @Override
    public Employee getEmployeeById(int id) throws RemoteException {
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public boolean addEmployee(Employee employee) throws RemoteException {
        return employeeDAO.addEmployee(employee);
    }



    @Override
    public boolean updateEmployee(Employee employee) throws RemoteException {
        return employeeDAO.updateEmployee(employee);
    }

    @Override
    public List<Object[]> getEmployeeTotalSales() throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeTotalSales());
    }

    @Override
    public List<Object[]> getEmployeeOrderCounts() throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeOrderCounts());
    }

    @Override
    public List<Object[]> getEmployeeAverageOrderValue() throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeAverageOrderValue());
    }

    @Override
    public List<Object[]> getEmployeeProductSales() throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeProductSales());
    }

    @Override
    public List<Object[]> getEmployeeProductSalesAndCount() throws RemoteException {
        return new ArrayList<>(employeeDAO.getEmployeeProductSalesAndCount());
    }

    @Override
    public Employee getEmployeeByAccount(String user, String pass) throws RemoteException {
        return employeeDAO.getEmployeeByAccount(user, pass);
    }

    @Override
    public List<Object[]> getEmployeeTotalProductStatsThisWeek(LocalDate startDate, LocalDate endDate) throws RemoteException{
        return employeeDAO.getEmployeeTotalProductStatsThisWeek(startDate, endDate);
    }



    @Override
    public List<Object[]> getTop5BestSellingProductsThisWeek(LocalDate startDate, LocalDate endDate) throws RemoteException{
        return employeeDAO.getTop5BestSellingProductsThisWeek(startDate, endDate);
    }

    @Override
    public List<Object[]> getEmployeeProductSalesCountByDayOfWeek(LocalDate startDate, LocalDate endDate) throws RemoteException{
        return employeeDAO.getEmployeeProductSalesCountByDayOfWeek(startDate, endDate);
    }
}
