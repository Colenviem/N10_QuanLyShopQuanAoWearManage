package bus;

import dao.EmployeeDAO;
import dto.Employee;
import interfaces.IEmployeeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class EmployeeBUS extends UnicastRemoteObject implements IEmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeBUS() throws RemoteException {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public List<Employee> getEmployeeByName(String name) throws RemoteException {
        return employeeDAO.getEmployeeByName(name);
    }

    @Override
    public List<Employee> getEmployeeByPhone(String phone) throws RemoteException {
        return employeeDAO.getEmployeeByPhone(phone);
    }

    @Override
    public List<Employee> getEmployeeBySalary(double salary) throws RemoteException {
        return employeeDAO.getEmployeeBySalary(salary);
    }

    @Override
    public List<Employee> getEmployeeByStatus(boolean status) throws RemoteException {
        return employeeDAO.getEmployeeByStatus(status);
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
}
