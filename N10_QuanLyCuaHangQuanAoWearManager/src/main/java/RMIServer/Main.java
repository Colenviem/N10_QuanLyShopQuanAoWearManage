package RMIServer;

import bus.CustomerBUS;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static Context ctx;

    public static void main(String[] args) throws Exception {
        ctx = new InitialContext();
        LocateRegistry.createRegistry(PORT);
        CustomerBUS customerBUS = new CustomerBUS();
        ctx.bind("rmi://" + HOST + ":" + PORT + "/CustomerBUS", customerBUS);
        System.out.println("Server is running...");
    }
}
