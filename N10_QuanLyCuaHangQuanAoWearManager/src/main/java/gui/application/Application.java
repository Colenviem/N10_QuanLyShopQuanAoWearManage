package gui.application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.naming.InitialContext;
import javax.swing.*;

import bus.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import gui.application.form.LoginForm;
import gui.application.form.MainForm;
import gui.splashScreen.SplashScreen;
import raven.toast.Notifications;

public class Application extends javax.swing.JFrame {
    private static Application app;
    private final MainForm mainForm;
    private final LoginForm loginForm;
    private AccountBUS accountBUS;
    private static final String HOST = "localhost";
    private static final int PORT = 9090;
    private InitialContext ctx;

    public Application() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        loginForm = new LoginForm();
        mainForm = new MainForm();
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Notifications.getInstance().setJFrame(this);

        try {
            ctx = new InitialContext();
            LocateRegistry.createRegistry(PORT);
            CustomerBUS customerBUS = new CustomerBUS();
            AccountBUS accountBUS = new AccountBUS();
            ProductBUS productBUS = new ProductBUS();
            OrderBUS orderBUS = new OrderBUS();
            OrderDetailBUS orderDetailBUS = new OrderDetailBUS();
            EmployeeBUS employeeBUS = new EmployeeBUS();
            ctx.bind("rmi://" + HOST + ":" + PORT + "/CustomerBUS", customerBUS);
            ctx.bind("rmi://" + HOST + ":" + PORT + "/AccountBUS", accountBUS);
            ctx.bind("rmi://" + HOST + ":" + PORT + "/ProductBUS", productBUS);
            ctx.bind("rmi://" + HOST + ":" + PORT + "/OrderBUS", orderBUS);
            ctx.bind("rmi://" + HOST + ":" + PORT + "/OrderDetailBUS", orderDetailBUS);
            ctx.bind("rmi://" + HOST + ":" + PORT + "/EmployeeBUS", employeeBUS);
            System.out.println("Server is running...");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() throws RemoteException {
        FlatAnimatedLafChange.showSnapshot();
        app.loginForm.setVisible(false); // Ẩn dialog đăng nhập
        app.setVisible(true); // Hiển thị cửa sổ chính
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.setVisible(false); // Ẩn cửa sổ chính
        app.loginForm.setLocationRelativeTo(null);
        app.loginForm.setVisible(true); // Hiển thị hộp thoại đăng nhập
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void setSelectedMenu(int index, int subIndex) throws RemoteException {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(() -> {
            FlatLaf.updateUI(); // Cập nhật giao diện trước
            new SplashScreen(null, true).setVisible(true);
            app = new Application();
            SwingUtilities.updateComponentTreeUI(app.loginForm); // Cập nhật UI cho LoginForm
            app.loginForm.setVisible(true);
        });
    }
}
