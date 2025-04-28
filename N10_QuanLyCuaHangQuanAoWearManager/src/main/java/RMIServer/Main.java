package RMIServer;

import bus.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 9090;
    private static Context ctx;

    public static void main(String[] args) throws Exception {
        ctx = new InitialContext();
        LocateRegistry.createRegistry(PORT);
        CustomerBUS customerBUS = new CustomerBUS();
        AccountBUS accountBUS = new AccountBUS();
        ProductBUS productBUS = new ProductBUS();
        OrderBUS orderBUS = new OrderBUS();
        OrderDetailBUS orderDetailBUS = new OrderDetailBUS();
        ctx.bind("rmi://" + HOST + ":" + PORT + "/CustomerBUS", customerBUS);
        ctx.bind("rmi://" + HOST + ":" + PORT + "/AccountBUS", accountBUS);
        ctx.bind("rmi://" + HOST + ":" + PORT + "/ProductBUS", productBUS);
        ctx.bind("rmi://" + HOST + ":" + PORT + "/OrderBUS", orderBUS);
        ctx.bind("rmi://" + HOST + ":" + PORT + "/OrderDetailBUS", orderDetailBUS);
        System.out.println("Server is running...");
    }
}
