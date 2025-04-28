package RMIClient;

import bus.CustomerBUS;
import interfaces.ICustomerService;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static Context ctx;

    public static void main(String[] args) throws Exception {
        ctx = new InitialContext();
        ICustomerService customerBUS = (ICustomerService) ctx.lookup("rmi://" + HOST + ":" + PORT + "/CustomerBUS");
        customerBUS.getAllCustomers()
                .stream()
                .forEach(System.out::println);
    }
}
