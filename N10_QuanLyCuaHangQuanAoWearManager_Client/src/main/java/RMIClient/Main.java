package RMIClient;

import interfaces.ICustomerService;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static Context ctx;

    public static void main(String[] args) throws Exception {
        ctx = new InitialContext();
        ICustomerService customerService = (ICustomerService) ctx.lookup("rmi://" + HOST + ":" + PORT + "/CustomerBUS");
        customerService.getAllCustomers()
                .stream()
                .forEach(System.out::println);
    }
}
