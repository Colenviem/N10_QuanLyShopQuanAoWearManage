package gui.application.form.other;

import bus.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.toedter.calendar.JCalendar;

import dto.*;
import gui.combobox.ComboBoxSuggestion;
import gui.table.TableCustom;
import gui.table.TableProductCellRender;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class FormLookup extends JPanel implements ActionListener, MouseListener{

    private AccountBUS acc_bus;
    private CustomerBUS cus_bus;
    private OrderBUS order_bus;
    private SupplierBUS sup_bus;
    private EmployeeBUS em_bus;
    private ProductBUS pro_bus;
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DecimalFormat dfm = new DecimalFormat("#,##0 VNĐ");

    public FormLookup() {
        try {
            acc_bus = new AccountBUS();
            cus_bus = new CustomerBUS();
            order_bus = new OrderBUS();
            sup_bus = new SupplierBUS();
            em_bus = new EmployeeBUS();
            pro_bus = new ProductBUS();
            initComponents();
            init();
            DocDuLieu();
            addActionListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        addActionListener();
    }

    private void DocDuLieu() {
        try {
            DocDuLieuDatabaseVaoTableEmp();
            DocDuLieuDatabaseVaoTableCus();
            DocDuLieuDatabaseVaoTableVen();
            DocDuLieuDatabaseVaoTableAcc();
            DocDuLieuDatabaseVaoTableOrder();
            DocDuLieuDatabaseVaoTableMe();
        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DocDuLieuDatabaseVaoTableEmp() {
        try {
            List<Employee> list = em_bus.getEmployeeByStatus(true); // Gọi phương thức từ BUS
            DefaultTableModel modelEmp = (DefaultTableModel) tableEmp.getModel();
            modelEmp.setRowCount(0);
            for (Employee nv : list) {
                String ngaySinh = nv.getHireDate().format(df);
                String luong = dfm.format(nv.getSalary());
                String trangThai = nv.isStatus() ? "Còn" : "Ngưng";
                modelEmp.addRow(new Object[]{
                    nv.getId(), nv.getFullName(), ngaySinh, nv.getPhone(), luong, trangThai, nv.getRole()
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DocDuLieuDatabaseVaoTableVen() throws RemoteException {
        List<Supplier> list = sup_bus.getAllSuppliers();
        DefaultTableModel model = (DefaultTableModel) tableVender.getModel();
        model.setRowCount(0);
        for (Supplier ncc : list) {
            String trangThai = ncc.isStatus() ? "Còn" : "Ngưng";
            model.addRow(new Object[]{
                ncc.getId(), ncc.getSupplierName(), ncc.getPhone(), ncc.getAddress(), trangThai
            });
        }

    }

    public void DocDuLieuDatabaseVaoTableAcc() throws RemoteException {
        List<Account> list = acc_bus.getAllActiveAccounts();
        DefaultTableModel model = (DefaultTableModel) tableAcc.getModel();
        model.setRowCount(0);
        for (Account tk : list) {
            String trangThai = tk.isStatus() ? "Còn" : "Ngưng";
            model.addRow(new Object[]{
                tk.getUsername(), tk.getPassword(), tk.getCreatedDate(), trangThai
            });
        }
    }

    public void DocDuLieuDatabaseVaoTableCus() throws RemoteException {
        List<Customer> list = cus_bus.getAllCustomers();
        DefaultTableModel model = (DefaultTableModel) tableCus.getModel();
        model.setRowCount(0);
        for (Customer kh : list) {
            model.addRow(new Object[]{
                kh.getId(), kh.getName(), kh.getPhone(), kh.getPoint()
            });
        }
    }

    public void DocDuLieuDatabaseVaoTableOrder() {
        try {
            // Lấy dữ liệu từ getOrderSummaries()
            List<Object[]> list = order_bus.getOrderSummaries();
            DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

            // Định dạng ngày và số tiền
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DecimalFormat decimalFormatter = new DecimalFormat("#,###.##");

            for (Object[] row : list) {
                // Định dạng các giá trị trước khi thêm vào bảng
                String orderDate = ((LocalDate) row[1]).format(dateFormatter); // Định dạng ngày
                String status = row[4].toString(); // Chuyển enum thành chuỗi
                String totalAmount = decimalFormatter.format((double) row[8]); // Định dạng tổng tiền hóa đơn
                String productInfo = row[5] + " (Số lượng: " + row[7] + ", Đơn giá: " + decimalFormatter.format((double) row[6]) + ")";
                String subTotal = decimalFormatter.format((double) row[9]); // Định dạng tổng tiền từng sản phẩm

                model.addRow(new Object[]{
                    row[0], // Mã hóa đơn (orderId)
                    orderDate, // Ngày tạo hóa đơn (orderDate)
                    row[2], // Số điện thoại khách hàng (phone)
                    row[3], // Tên khách hàng (name)
                    row[5],
                    row[7], // Trạng thái hóa đơn (status)
                    totalAmount, // Tổng tiền hóa đơn (totalAmount)
                    productInfo, // Tên sản phẩm (kèm số lượng và đơn giá)
                    subTotal,
                    status
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DocDuLieuDatabaseVaoTableMe() {
        try {
            // Lấy dữ liệu từ ProductDAO
            List<Object[]> list = pro_bus.getProductDetails(); // Sử dụng phương thức getProductDetails() đã tạo

            // Lấy model của bảng
            DefaultTableModel model = (DefaultTableModel) tableMe.getModel(); // Giả sử bạn có một JTable tên là tableProduct
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

            // Định dạng số tiền
            DecimalFormat decimalFormatter = new DecimalFormat("#,###.##");

            // Duyệt qua danh sách sản phẩm và thêm vào bảng
            for (Object[] row : list) {
                String priceAfterDiscount = decimalFormatter.format((double) row[4]);
                String status = (boolean) row[6] ? "Còn hàng" : "Hết hàng"; // Chuyển đổi trạng thái boolean sang chuỗi

                model.addRow(new Object[]{
                    row[3],
                    row[0], // Mã sản phẩm (product_id)
                    row[1], // Tên sản phẩm (productName)
                    row[2], // Danh mục (category - name)
                    row[8],
                    row[7],
                    row[9],
                    priceAfterDiscount, // Giá sau chiết khấu
                    row[5], // Số lượng tồn kho (stockQuantity)
                    status // Trạng thái
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        tabbedPane = new gui.tabbedpane.TabbedPaneCustom();
        pnlEmp = new javax.swing.JPanel();
        pnlNavEmp = new javax.swing.JPanel();
        navEmp = new gui.navbar.NavbarSearch();
        pnlTableCon = new javax.swing.JPanel();
        pnlTableEmp = new javax.swing.JPanel();
        lblNameEmp = new javax.swing.JLabel();
        scrollPaneEmp = new javax.swing.JScrollPane();
        tableEmp = new gui.table.TableCustom();
        pnlCus = new javax.swing.JPanel();
        pnlNavCus = new javax.swing.JPanel();
        navCus = new gui.navbar.NavbarSearch();
        pnlTableConCus = new javax.swing.JPanel();
        pnlTableCus = new javax.swing.JPanel();
        lblNameCus = new javax.swing.JLabel();
        scrollPaneCus = new javax.swing.JScrollPane();
        tableCus = new gui.table.TableCustom();
        pnlOrder = new javax.swing.JPanel();
        pnlNavOrder = new javax.swing.JPanel();
        navOrder = new gui.navbar.NavbarSearch();
        pnlTableConOrder = new javax.swing.JPanel();
        pnlTableOrder = new javax.swing.JPanel();
        lblNameOrder = new javax.swing.JLabel();
        scrollPaneOrder = new javax.swing.JScrollPane();
        tableOrder = new gui.table.TableCustom();
        pnlVender = new javax.swing.JPanel();
        pnlNavVender = new javax.swing.JPanel();
        navVender = new gui.navbar.NavbarSearch();
        pnlTableConVender = new javax.swing.JPanel();
        pnlTableVender = new javax.swing.JPanel();
        lblNameVender = new javax.swing.JLabel();
        scrollPaneVender = new javax.swing.JScrollPane();
        tableVender = new gui.table.TableCustom();
        pnlAcc = new javax.swing.JPanel();
        pnlNavAcc = new javax.swing.JPanel();
        navAcc = new gui.navbar.NavbarSearch();
        pnlTableConAcc = new javax.swing.JPanel();
        pnlTableAcc = new javax.swing.JPanel();
        lblNameAcc = new javax.swing.JLabel();
        scrollPaneAcc = new javax.swing.JScrollPane();
        tableAcc = new gui.table.TableCustom();
        pnlMe = new javax.swing.JPanel();
        pnlNavMe = new javax.swing.JPanel();
        navPro = new gui.navbar.NavbarSearch();
        pnlTableConMe = new javax.swing.JPanel();
        pnlTableMe = new javax.swing.JPanel();
        lblNameMe = new javax.swing.JLabel();
        scrollPaneMe = new javax.swing.JScrollPane();
        tableMe = new gui.table.TableCustom();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        pnlEmp.setOpaque(false);
        pnlEmp.setLayout(new java.awt.BorderLayout());

        pnlNavEmp.setOpaque(false);
        pnlNavEmp.setLayout(new java.awt.BorderLayout());
        pnlNavEmp.add(navEmp, java.awt.BorderLayout.PAGE_END);

        pnlEmp.add(pnlNavEmp, java.awt.BorderLayout.PAGE_START);

        pnlTableCon.setOpaque(false);
        pnlTableCon.setLayout(new java.awt.BorderLayout());

        pnlTableEmp.setLayout(new java.awt.BorderLayout());

        lblNameEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameEmp.setText("Danh sách nhân viên");
        pnlTableEmp.add(lblNameEmp, java.awt.BorderLayout.PAGE_START);

        tableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Lương", "Trạng thái", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneEmp.setViewportView(tableEmp);
        if (tableEmp.getColumnModel().getColumnCount() > 0) {
            tableEmp.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableEmp.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableEmp.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableEmp.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableEmp.getColumnModel().getColumn(4).setPreferredWidth(150);
            tableEmp.getColumnModel().getColumn(5).setPreferredWidth(150);
            tableEmp.getColumnModel().getColumn(6).setPreferredWidth(220);
        }

        pnlTableEmp.add(scrollPaneEmp, java.awt.BorderLayout.CENTER);

        pnlTableCon.add(pnlTableEmp, java.awt.BorderLayout.CENTER);

        pnlEmp.add(pnlTableCon, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Nhân viên", pnlEmp);

        pnlCus.setOpaque(false);
        pnlCus.setLayout(new java.awt.BorderLayout());

        pnlNavCus.setOpaque(false);
        pnlNavCus.setLayout(new java.awt.BorderLayout());
        pnlNavCus.add(navCus, java.awt.BorderLayout.PAGE_START);

        pnlCus.add(pnlNavCus, java.awt.BorderLayout.PAGE_START);

        pnlTableConCus.setOpaque(false);
        pnlTableConCus.setLayout(new java.awt.BorderLayout());

        pnlTableCus.setLayout(new java.awt.BorderLayout());

        lblNameCus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameCus.setText("Danh sách khách hàng");
        pnlTableCus.add(lblNameCus, java.awt.BorderLayout.PAGE_START);

        tableCus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Điểm tích lũy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneCus.setViewportView(tableCus);
        if (tableCus.getColumnModel().getColumnCount() > 0) {
            tableCus.getColumnModel().getColumn(0).setPreferredWidth(376);
            tableCus.getColumnModel().getColumn(1).setPreferredWidth(330);
            tableCus.getColumnModel().getColumn(2).setPreferredWidth(330);
            tableCus.getColumnModel().getColumn(3).setPreferredWidth(310);
        }

        pnlTableCus.add(scrollPaneCus, java.awt.BorderLayout.CENTER);

        pnlTableConCus.add(pnlTableCus, java.awt.BorderLayout.CENTER);

        pnlCus.add(pnlTableConCus, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Khách hàng", pnlCus);

        pnlOrder.setOpaque(false);
        pnlOrder.setLayout(new java.awt.BorderLayout());

        pnlNavOrder.setOpaque(false);
        pnlNavOrder.setLayout(new java.awt.BorderLayout());
        pnlNavOrder.add(navOrder, java.awt.BorderLayout.PAGE_START);

        pnlOrder.add(pnlNavOrder, java.awt.BorderLayout.PAGE_START);

        pnlTableConOrder.setOpaque(false);
        pnlTableConOrder.setLayout(new java.awt.BorderLayout());

        pnlTableOrder.setLayout(new java.awt.BorderLayout());

        lblNameOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameOrder.setText("Danh sách hóa đơn");
        pnlTableOrder.add(lblNameOrder, java.awt.BorderLayout.PAGE_START);

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo hóa đơn", "Số điện thoại KH", "Tên khách hàng", "Tên sản phẩm", "Đơn giá", "Số lượng", "Tổng tiền sản phẩm", "Tổng tiền hóa đơn", "Trạng thái hóa đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneOrder.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(1).setPreferredWidth(350);
            tableOrder.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(4).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(5).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(6).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(7).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(8).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(9).setPreferredWidth(250);
        }

        pnlTableOrder.add(scrollPaneOrder, java.awt.BorderLayout.CENTER);

        pnlTableConOrder.add(pnlTableOrder, java.awt.BorderLayout.CENTER);

        pnlOrder.add(pnlTableConOrder, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Hóa đơn", pnlOrder);

        pnlVender.setOpaque(false);
        pnlVender.setLayout(new java.awt.BorderLayout());

        pnlNavVender.setOpaque(false);
        pnlNavVender.setLayout(new java.awt.BorderLayout());
        pnlNavVender.add(navVender, java.awt.BorderLayout.PAGE_START);

        pnlVender.add(pnlNavVender, java.awt.BorderLayout.PAGE_START);

        pnlTableConVender.setOpaque(false);
        pnlTableConVender.setLayout(new java.awt.BorderLayout());

        pnlTableVender.setLayout(new java.awt.BorderLayout());

        lblNameVender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameVender.setText("Danh sách nhà cung cấp");
        pnlTableVender.add(lblNameVender, java.awt.BorderLayout.PAGE_START);

        tableVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số tài khoản", "Địa chỉ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneVender.setViewportView(tableVender);
        if (tableVender.getColumnModel().getColumnCount() > 0) {
            tableVender.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableVender.getColumnModel().getColumn(1).setPreferredWidth(270);
            tableVender.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableVender.getColumnModel().getColumn(3).setPreferredWidth(400);
            tableVender.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        pnlTableVender.add(scrollPaneVender, java.awt.BorderLayout.CENTER);

        pnlTableConVender.add(pnlTableVender, java.awt.BorderLayout.CENTER);

        pnlVender.add(pnlTableConVender, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Nhà cung cấp", pnlVender);

        pnlAcc.setOpaque(false);
        pnlAcc.setLayout(new java.awt.BorderLayout());

        pnlNavAcc.setOpaque(false);
        pnlNavAcc.setLayout(new java.awt.BorderLayout());
        pnlNavAcc.add(navAcc, java.awt.BorderLayout.PAGE_START);

        pnlAcc.add(pnlNavAcc, java.awt.BorderLayout.PAGE_START);

        pnlTableConAcc.setOpaque(false);
        pnlTableConAcc.setLayout(new java.awt.BorderLayout());

        pnlTableAcc.setLayout(new java.awt.BorderLayout());

        lblNameAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameAcc.setText("Danh sách tài khoản");
        pnlTableAcc.add(lblNameAcc, java.awt.BorderLayout.PAGE_START);

        tableAcc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tài khoản", "Mật khẩu", "Ngày đăng ký", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneAcc.setViewportView(tableAcc);
        if (tableAcc.getColumnModel().getColumnCount() > 0) {
            tableAcc.getColumnModel().getColumn(0).setPreferredWidth(330);
            tableAcc.getColumnModel().getColumn(1).setPreferredWidth(330);
            tableAcc.getColumnModel().getColumn(2).setPreferredWidth(330);
            tableAcc.getColumnModel().getColumn(3).setPreferredWidth(330);
        }

        pnlTableAcc.add(scrollPaneAcc, java.awt.BorderLayout.CENTER);

        pnlTableConAcc.add(pnlTableAcc, java.awt.BorderLayout.CENTER);

        pnlAcc.add(pnlTableConAcc, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Tài khoản", pnlAcc);

        pnlMe.setOpaque(false);
        pnlMe.setLayout(new java.awt.BorderLayout());

        pnlNavMe.setOpaque(false);
        pnlNavMe.setLayout(new java.awt.BorderLayout());
        pnlNavMe.add(navPro, java.awt.BorderLayout.PAGE_START);

        pnlMe.add(pnlNavMe, java.awt.BorderLayout.PAGE_START);

        pnlTableConMe.setOpaque(false);
        pnlTableConMe.setLayout(new java.awt.BorderLayout());

        pnlTableMe.setLayout(new java.awt.BorderLayout());

        lblNameMe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameMe.setText("Danh sách sản phẩm");
        pnlTableMe.add(lblNameMe, java.awt.BorderLayout.PAGE_START);

        tableMe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ảnh", "Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Mô tả", "Color", "Khuyến mãi", "Giá sau chiết khấu", "Số lượng tồn kho", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneMe.setViewportView(tableMe);
        if (tableMe.getColumnModel().getColumnCount() > 0) {
            tableMe.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableMe.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableMe.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableMe.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableMe.getColumnModel().getColumn(4).setPreferredWidth(300);
            tableMe.getColumnModel().getColumn(5).setPreferredWidth(100);
            tableMe.getColumnModel().getColumn(6).setPreferredWidth(150);
            tableMe.getColumnModel().getColumn(7).setPreferredWidth(150);
            tableMe.getColumnModel().getColumn(8).setPreferredWidth(150);
            tableMe.getColumnModel().getColumn(9).setPreferredWidth(150);
        }

        pnlTableMe.add(scrollPaneMe, java.awt.BorderLayout.CENTER);

        pnlTableConMe.add(pnlTableMe, java.awt.BorderLayout.CENTER);

        pnlMe.add(pnlTableConMe, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Sản phẩm", pnlMe);

        add(tabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel lblNameAcc;
    private javax.swing.JLabel lblNameCus;
    private javax.swing.JLabel lblNameEmp;
    private javax.swing.JLabel lblNameMe;
    private javax.swing.JLabel lblNameOrder;
    private javax.swing.JLabel lblNameVender;
    private gui.navbar.NavbarSearch navAcc;
    private gui.navbar.NavbarSearch navCus;
    private gui.navbar.NavbarSearch navEmp;
    private gui.navbar.NavbarSearch navOrder;
    private gui.navbar.NavbarSearch navPro;
    private gui.navbar.NavbarSearch navVender;
    private javax.swing.JPanel pnlAcc;
    private javax.swing.JPanel pnlCus;
    private javax.swing.JPanel pnlEmp;
    private javax.swing.JPanel pnlMe;
    private javax.swing.JPanel pnlNavAcc;
    private javax.swing.JPanel pnlNavCus;
    private javax.swing.JPanel pnlNavEmp;
    private javax.swing.JPanel pnlNavMe;
    private javax.swing.JPanel pnlNavOrder;
    private javax.swing.JPanel pnlNavVender;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlTableAcc;
    private javax.swing.JPanel pnlTableCon;
    private javax.swing.JPanel pnlTableConAcc;
    private javax.swing.JPanel pnlTableConCus;
    private javax.swing.JPanel pnlTableConMe;
    private javax.swing.JPanel pnlTableConOrder;
    private javax.swing.JPanel pnlTableConVender;
    private javax.swing.JPanel pnlTableCus;
    private javax.swing.JPanel pnlTableEmp;
    private javax.swing.JPanel pnlTableMe;
    private javax.swing.JPanel pnlTableOrder;
    private javax.swing.JPanel pnlTableVender;
    private javax.swing.JPanel pnlVender;
    private javax.swing.JScrollPane scrollPaneAcc;
    private javax.swing.JScrollPane scrollPaneCus;
    private javax.swing.JScrollPane scrollPaneEmp;
    private javax.swing.JScrollPane scrollPaneMe;
    private javax.swing.JScrollPane scrollPaneOrder;
    private javax.swing.JScrollPane scrollPaneVender;
    private gui.tabbedpane.TabbedPaneCustom tabbedPane;
    private gui.table.TableCustom tableAcc;
    private gui.table.TableCustom tableCus;
    private gui.table.TableCustom tableEmp;
    private gui.table.TableCustom tableMe;
    private gui.table.TableCustom tableOrder;
    private gui.table.TableCustom tableVender;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormLookup card = new FormLookup();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void init() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setOpaque(false);

        //Tabbed Pane nhân viên
        pnlEmp.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableCon.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableEmp.setBackground(new Color(255, 255, 255));
        pnlTableEmp.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navEmp.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên", "Số điện thoại"}));
        navEmp.hideComponentDate();
        tableEmp.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
        tableEmp.ScrollPaneTable(scrollPaneEmp);
        lblNameEmp.setPreferredSize(new Dimension(100, 40));
        lblNameEmp.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameEmp.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameEmp.setForeground(new Color(148, 148, 148));
        lblNameEmp.setOpaque(false);
        lblNameEmp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));

        //Tabbed Pane khách hàng
        pnlCus.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConCus.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableCus.setBackground(new Color(255, 255, 255));
        pnlTableCus.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navCus.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã khách hàng", "Tên khách hàng", "Số điện thoại"}));
        navCus.hideComponentDate();
        tableCus.ScrollPaneTable(scrollPaneCus);
        tableCus.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
        lblNameCus.setPreferredSize(new Dimension(100, 40));
        lblNameCus.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameCus.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameCus.setForeground(new Color(148, 148, 148));
        lblNameCus.setOpaque(false);
        lblNameCus.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));

        //Tabbed Pane hóa đơn
        pnlOrder.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConOrder.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableOrder.setBackground(new Color(255, 255, 255));
        pnlTableOrder.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navOrder.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã hoá đơn", "Tên khách hàng", "SDT khách hàng", "Mã nhân viên", "Tên nhân viên", "Ngày thanh toán"}));
        navOrder.hideComponentDate();
        tableOrder.ScrollPaneTable(scrollPaneOrder);
        lblNameOrder.setPreferredSize(new Dimension(100, 40));
        lblNameOrder.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameOrder.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameOrder.setForeground(new Color(148, 148, 148));
        lblNameOrder.setOpaque(false);
        lblNameOrder.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));

        //Tabbed Pane nhà cung cấp
        pnlVender.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConVender.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableVender.setBackground(new Color(255, 255, 255));
        pnlTableVender.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navVender.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "Uy tín"}));
       navVender.hideComponentDate();
        tableVender.ScrollPaneTable(scrollPaneVender);
        tableVender.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
        lblNameVender.setPreferredSize(new Dimension(100, 40));
        lblNameVender.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameVender.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameVender.setForeground(new Color(148, 148, 148));
        lblNameVender.setOpaque(false);
        lblNameVender.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));

        //Tabbed Pane khuyến mãi
//        pnlDis.setBorder(new EmptyBorder(10, 0, 0, 0));
//        pnlTableConDis.setBorder(new EmptyBorder(10, 0, 0, 0));
//        pnlTableDis.setBackground(new Color(255, 255, 255));
//        pnlTableDis.setBorder(new LineBorder(new Color(206, 212, 218), 2));
//        navDis.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã khuyến mãi", "Tên khuyến mãi", "Theo thời gian", "Tên thuốc", "Tên thiết bị y tế"}));
//        tableDis.ScrollPaneTable(scrollPaneDis);
//        tableDis.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
//        lblNameDis.setPreferredSize(new Dimension(100, 40));
//        lblNameDis.setBorder(new EmptyBorder(0, 20, 0, 20));
//        lblNameDis.setFont(new Font("Regular", Font.BOLD, 18));
//        lblNameDis.setForeground(new Color(148, 148, 148));
//        lblNameDis.setOpaque(false);
//        lblNameDis.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        //Tabbed Pane tải khoản
        pnlAcc.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConAcc.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableAcc.setBackground(new Color(255, 255, 255));
        pnlTableAcc.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navAcc.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Tài khoản"}));
        navAcc.hideComponentDate();
        tableAcc.ScrollPaneTable(scrollPaneAcc);
        tableAcc.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
        lblNameAcc.setPreferredSize(new Dimension(100, 40));
        lblNameAcc.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameAcc.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameAcc.setOpaque(false);
        lblNameAcc.setForeground(new Color(148, 148, 148));
        lblNameAcc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));

        //Tabbed Pane thuốc
        pnlMe.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConMe.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableMe.setBackground(new Color(255, 255, 255));
        pnlTableMe.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navPro.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã thuốc", "Tên thuốc", "Số đăng ký"}));
        tableMe.ScrollPaneTable(scrollPaneMe);
        navPro.hideComponentDate();
        tableMe.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
        tableMe.getColumnModel().getColumn(0).setCellRenderer(new TableProductCellRender(tableMe));
        tableMe.setRowHeight(80);
        lblNameMe.setPreferredSize(new Dimension(100, 40));
        lblNameMe.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameMe.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameMe.setForeground(new Color(148, 148, 148));
        lblNameMe.setOpaque(false);
        lblNameMe.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));

        //Tabbed Pane thiết bị y tế
//        pnlMeE.setBorder(new EmptyBorder(10, 0, 0, 0));
//        pnlTableConMeE.setBorder(new EmptyBorder(10, 0, 0, 0));
//        pnlTableMeE.setBackground(new Color(255, 255, 255));
//        pnlTableMeE.setBorder(new LineBorder(new Color(206, 212, 218), 2));
//        navMeE.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã thiết bị", "Tên thiết bị"}));
//        navMeE.hideDateComponents();
//        tableMeE.setRowHeight(80);
//        tableMeE.ScrollPaneTable(scrollPaneMeE);
//        tableMeE.getColumnModel().getColumn(0).setCellRenderer(new TableProductCellRender(tableMeE));
//        tableMeE.setAutoResizeMode(TableCustom.AUTO_RESIZE_OFF);
//        lblNameMeE.setPreferredSize(new Dimension(100, 40));
//        lblNameMeE.setBorder(new EmptyBorder(0, 20, 0, 20));
//        lblNameMeE.setFont(new Font("Regular", Font.BOLD, 18));
//        lblNameMeE.setForeground(new Color(148, 148, 148));
//        lblNameMeE.setOpaque(false);
//        lblNameMeE.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        // Vẽ viền
        g2.setColor(new Color(206, 212, 218));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 16, 16);
        super.paintComponent(g);
    }



private void searchEmp() {
    ComboBoxSuggestion<String> comboBoxEmp = navEmp.getComboBox();
    String selectedValueEmp = (String) comboBoxEmp.getSelectedItem();
    String searchText = navEmp.getTxtSearch().getText().trim();
    DefaultTableModel modelEmp = (DefaultTableModel) tableEmp.getModel();
    modelEmp.setRowCount(0); // Xóa dữ liệu cũ

    List<Employee> empList = new ArrayList<>();
    try {
        if (!searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
            switch (selectedValueEmp) {
                case "Mã nhân viên":
                    int id = Integer.parseInt(searchText); // Chuyển đổi searchText thành int
                    Employee employee = em_bus.getEmployeeById(id);
                    if (employee != null) {
                        empList.add(employee);
                    }
                    break;
                case "Tên nhân viên":
                    empList = em_bus.getEmployeeByName(searchText);
                    break;
                case "Số điện thoại":
                    empList = em_bus.getEmployeeByPhone(searchText);
                    break;
            }
        } else {
            empList = em_bus.getEmployeeByStatus(true); // Tải lại toàn bộ dữ liệu
        }

        if (empList.isEmpty() && !searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy: " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
            empList = em_bus.getEmployeeByStatus(true); // Tải lại toàn bộ nếu không tìm thấy
        }

        // Hiển thị kết quả lên bảng
        for (Employee nv : empList) {
            String ngaySinh = nv.getHireDate().format(df);
            String luong = dfm.format(nv.getSalary());
            String trangThai = nv.isStatus() ? "Còn" : "Ngưng";
            modelEmp.addRow(new Object[]{
                    nv.getId(), nv.getFullName(), ngaySinh, nv.getPhone(), luong, trangThai, nv.getRole()
            });
        }

        SwingUtilities.invokeLater(() -> {
            modelEmp.fireTableDataChanged();
            tableEmp.revalidate();
            tableEmp.repaint();
            scrollPaneEmp.revalidate();
            scrollPaneEmp.repaint();
        });

    } catch (Exception ex) {
        Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    private void searchCus() {
        ComboBoxSuggestion<String> comboBoxCus = navCus.getComboBox();
        String selectedValueCus = (String) comboBoxCus.getSelectedItem();
        String searchText = navCus.getTxtSearch().getText().trim();
        DefaultTableModel modelCus = (DefaultTableModel) tableCus.getModel();
        modelCus.setRowCount(0); // Xóa dữ liệu cũ

        List<Customer> cusList = new ArrayList<>();
        try {
            if (!searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                switch (selectedValueCus) {
                    case "Mã khách hàng":
                        int id = Integer.parseInt(searchText); // Chuyển đổi searchText thành int
                        Customer customer = cus_bus.getCustomerById(id);
                        if (customer != null) {
                            cusList.add(customer);
                        }
                        break;
                    case "Tên khách hàng":
                        cusList = cus_bus.getCustomerByName(searchText);
                        break;
                    case "Số điện thoại":
                        cusList = cus_bus.getCustomerByPhone(searchText);
                        break;
                }
            } else {
                cusList = cus_bus.getAllCustomers(); // Tải lại toàn bộ dữ liệu
            }

            if (cusList.isEmpty() && !searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy: " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
                cusList = cus_bus.getAllCustomers(); // Tải lại toàn bộ nếu không tìm thấy
            }

            // Hiển thị kết quả lên bảng
            for (Customer kh : cusList) {
                modelCus.addRow(new Object[]{
                        kh.getId(), kh.getName(), kh.getPhone(), kh.getPoint()
                });
            }

            SwingUtilities.invokeLater(() -> {
                modelCus.fireTableDataChanged();
                tableCus.revalidate();
                tableCus.repaint();
                scrollPaneCus.revalidate();
                scrollPaneCus.repaint();
            });

        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchVen() {
        ComboBoxSuggestion<String> comboBoxVen = navVender.getComboBox();
        String selectedValueVen = (String) comboBoxVen.getSelectedItem();
        String searchText = navVender.getTxtSearch().getText().trim();
        DefaultTableModel modelVen = (DefaultTableModel) tableVender.getModel();
        modelVen.setRowCount(0); // Xóa dữ liệu cũ

        List<Supplier> supList = new ArrayList<>();
        try {
            if (!searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                switch (selectedValueVen) {
                    case "Mã nhà cung cấp":
                        int id = Integer.parseInt(searchText);
                        Supplier supplier = sup_bus.getSupplierById(id);
                        supList.add(supplier);
                        break;
                    case "Tên nhà cung cấp":
                        supList = sup_bus.getSupplierBySupplierName(searchText);
                        break;
                }
            } else {
                supList = sup_bus.getAllSuppliers(); // Tải lại toàn bộ dữ liệu
            }

            if (supList.isEmpty() && !searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy: " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
                supList = sup_bus.getAllSuppliers(); // Tải lại toàn bộ nếu không tìm thấy
            }

            // Hiển thị kết quả lên bảng
            for (Supplier ncc : supList) {
                String trangThai = ncc.isStatus() ? "Còn" : "Ngưng";
                modelVen.addRow(new Object[]{
                        ncc.getId(), ncc.getSupplierName(), ncc.getPhone(), ncc.getAddress(), trangThai
                });
            }

            SwingUtilities.invokeLater(() -> {
                modelVen.fireTableDataChanged();
                tableVender.revalidate();
                tableVender.repaint();
                scrollPaneVender.revalidate();
                scrollPaneVender.repaint();
            });

        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchAcc() {
        ComboBoxSuggestion<String> comboBoxAcc = navAcc.getComboBox();
        String selectedValueAcc = (String) comboBoxAcc.getSelectedItem();
        String searchText = navAcc.getTxtSearch().getText().trim();
        DefaultTableModel modelAcc = (DefaultTableModel) tableAcc.getModel();
        modelAcc.setRowCount(0); // Xóa dữ liệu cũ

        List<Account> accList = new ArrayList<>();
        try {
            if (!searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                switch (selectedValueAcc) {
                    case "Tài khoản":
                        accList = acc_bus.getAccountByUsername(searchText);
                        break;
                }
            } else {
                accList = acc_bus.getAllActiveAccounts(); // Tải lại toàn bộ dữ liệu
            }

            if (accList.isEmpty() && !searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy: " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
                accList = acc_bus.getAllActiveAccounts(); // Tải lại toàn bộ nếu không tìm thấy
            }

            // Hiển thị kết quả lên bảng
            for (Account tk : accList) {
                String trangThai = tk.isStatus() ? "Còn" : "Ngưng";
                modelAcc.addRow(new Object[]{
                        tk.getUsername(), tk.getPassword(), tk.getCreatedDate(), trangThai
                });
            }

            SwingUtilities.invokeLater(() -> {
                modelAcc.fireTableDataChanged();
                tableAcc.revalidate();
                tableAcc.repaint();
                scrollPaneAcc.revalidate();
                scrollPaneAcc.repaint();
            });

        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchOrder() {
        ComboBoxSuggestion<String> comboBoxOrder = navOrder.getComboBox();
        String selectedValueOrder = (String) comboBoxOrder.getSelectedItem();
        String searchText = navOrder.getTxtSearch().getText().trim();
        DefaultTableModel modelOrder = (DefaultTableModel) tableOrder.getModel();
        modelOrder.setRowCount(0); // Xóa dữ liệu cũ

        List<Object[]> orderList = new ArrayList<>();
        try {
            if (!searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...") || selectedValueOrder.equals("Ngày thanh toán")) {
                switch (selectedValueOrder) {
                    case "Mã hoá đơn":
                        try {
                            int id = Integer.parseInt(searchText);
                            Object[] orderRow = new Order[]{order_bus.getOrderById(id)};
                            if (orderRow != null) {
                                orderList.add(orderRow);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Mã hóa đơn phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;

                }
            } else {
                orderList = order_bus.getOrderSummaries(); // Tải lại toàn bộ dữ liệu
            }

            if (orderList.isEmpty() && !searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...") && !selectedValueOrder.equals("Ngày thanh toán")) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy: " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
                orderList = order_bus.getOrderSummaries(); // Tải lại toàn bộ nếu không tìm thấy
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DecimalFormat decimalFormatter = new DecimalFormat("#,###.##");

            for (Object[] row : orderList) {
                String orderDate = ((LocalDate) row[1]).format(dateFormatter);
                String status = row[4].toString();
                String unitPrice = decimalFormatter.format((double) row[6]);
                String totalAmount = decimalFormatter.format((double) row[8]);
                String subTotal = decimalFormatter.format((double) row[9]);

                modelOrder.addRow(new Object[]{
                        row[0], // Mã hóa đơn
                        orderDate, // Ngày tạo hóa đơn
                        row[2], // Số điện thoại KH
                        row[3], // Tên khách hàng
                        row[5], // Tên sản phẩm
                        unitPrice, // Đơn giá
                        row[7], // Số lượng
                        subTotal, // Tổng tiền sản phẩm
                        totalAmount, // Tổng tiền hóa đơn
                        status // Trạng thái hóa đơn
                });
            }

            SwingUtilities.invokeLater(() -> {
                modelOrder.fireTableDataChanged();
                tableOrder.revalidate();
                tableOrder.repaint();
                scrollPaneOrder.revalidate();
                scrollPaneOrder.repaint();
            });

        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchPro() {
        ComboBoxSuggestion<String> comboBoxPro = navPro.getComboBox();
        String selectedValuePro = (String) comboBoxPro.getSelectedItem();
        String searchText = navPro.getTxtSearch().getText().trim();
        DefaultTableModel modelPro = (DefaultTableModel) tableMe.getModel();
        modelPro.setRowCount(0); // Xóa dữ liệu cũ

        List<Product> proList = new ArrayList<>();
        try {
            if (!searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                switch (selectedValuePro) {
                    case "Mã thuốc":
                        try {
                            int id = Integer.parseInt(searchText);
                            Product product = pro_bus.getProductById(id);
                            if (product != null) {
                                proList.add(product);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Mã thuốc phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    case "Tên thuốc": // Changed from "Tên sản phẩm" to match ComboBox
                        proList = pro_bus.getProductByName(searchText);
                        break;

                }
            } else {
                proList = pro_bus.getAllProducts(); // Tải lại toàn bộ dữ liệu
            }

            if (proList.isEmpty() && !searchText.isEmpty() && !searchText.equals("Nhập nội dung tìm kiếm...")) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy: " + searchText, "Lỗi", JOptionPane.ERROR_MESSAGE);
                proList = pro_bus.getAllProducts(); // Tải lại toàn bộ nếu không tìm thấy
            }

            DecimalFormat decimalFormatter = new DecimalFormat("#,###.##");
            for (Product product : proList) {
                String priceAfterDiscount = decimalFormatter.format(product.getPrice());
                String status = product.isStatus() ? "Còn hàng" : "Hết hàng";
                modelPro.addRow(new Object[]{
                        product.getImageUrl(), // Ảnh
                        product.getId(), // Mã sản phẩm
                        product.getProductName(), // Tên sản phẩm
                        product.getCategory(), // Danh mục
                        product.getDescription(), // Mô tả
                        product.getColor(), // Color
                        product.getDiscount(), // Khuyến mãi
                        priceAfterDiscount, // Giá sau chiết khấu
                        product.getStockQuantity(), // Số lượng tồn kho
                        status // Trạng thái
                });
            }

            SwingUtilities.invokeLater(() -> {
                modelPro.fireTableDataChanged();
                tableMe.revalidate();
                tableMe.repaint();
                scrollPaneMe.revalidate();
                scrollPaneMe.repaint();
            });

        } catch (Exception ex) {
            Logger.getLogger(FormLookup.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addActionListener() {
        // Nhân viên
        navEmp.getTxtSearch().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                navEmp.getTxtSearch().selectAll();
            }
        });
        navEmp.getBtnSearch().addActionListener(this);

        // Khách hàng
        navCus.getTxtSearch().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                navCus.getTxtSearch().selectAll();
            }
        });
        navCus.getBtnSearch().addActionListener(this);

        // Nhà cung cấp
        navVender.getTxtSearch().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                navVender.getTxtSearch().selectAll();
            }
        });
        navVender.getBtnSearch().addActionListener(this);

        // Tài khoản
        navAcc.getTxtSearch().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                navAcc.getTxtSearch().selectAll();
            }
        });
        navAcc.getBtnSearch().addActionListener(this);

        // Hóa đơn
        navOrder.getTxtSearch().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                navOrder.getTxtSearch().selectAll();
            }
        });
        navOrder.getBtnSearch().addActionListener(this);
        navOrder.getBtnDate().addActionListener(this);
        navOrder.getBtnDateTo().addActionListener(this);

        // Sản phẩm
        navPro.getTxtSearch().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                navPro.getTxtSearch().selectAll();
            }
        });
        navPro.getBtnSearch().addActionListener(this);

        // Add MouseListener for tableMe (since FormLookup implements MouseListener)
        tableMe.addMouseListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(navEmp.getBtnSearch())) {
            searchEmp();
        } // Cus
        else if (o.equals(navCus.getBtnSearch())) {
            searchCus();
        } // Order
        else if (o.equals(navOrder.getBtnSearch())) {
            searchOrder();
        }
        else if (o.equals(navVender.getBtnSearch())) {
            searchVen();
        } // Account
        else if (o.equals(navAcc.getBtnSearch())) {
            searchAcc();
        } // Thuoc
        else if (o.equals(navPro.getBtnSearch())) {
            searchPro();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
