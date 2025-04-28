package gui.application.form.other;

import dto.Order;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.navbar.Navbar;
import gui.textfield.TextFieldCustom;
import interfaces.IOrderDetailService;
import interfaces.IOrderService;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FormBill extends JPanel implements ActionListener, MouseListener {

    private DefaultTableModel tableModel;
    private DefaultTableModel tableModelOrderDetail;
    private ButtonCustom searchButton;
    private NavButtonCustom btnImport;
    private NavButtonCustom btnExport;
    private NavButtonCustom btnAdd;
    private IOrderService orderService;
    private IOrderDetailService orderDetailService;
    private static final String HOST = "localhost";
    private static final int PORT = 9090;
    private static Context ctx;

    public FormBill() {
        try {
            initComponents();
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlOrderDetail = new javax.swing.JPanel();
        scrollPaneOrderDetail = new javax.swing.JScrollPane();
        tableOrderDetail = new gui.table.TableCustom();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        navbar = new gui.navbar.Navbar();
        pnlOrder = new javax.swing.JPanel();
        scrollPaneOrder = new javax.swing.JScrollPane();
        tableOrder = new gui.table.TableCustom();

        setLayout(new java.awt.BorderLayout(0, 5));

        pnlOrderDetail.setOpaque(false);
        pnlOrderDetail.setPreferredSize(new java.awt.Dimension(100, 350));
        pnlOrderDetail.setLayout(new java.awt.BorderLayout());

        tableOrderDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Sản phẩm", "Giá bán", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneOrderDetail.setViewportView(tableOrderDetail);

        pnlOrderDetail.add(scrollPaneOrderDetail, java.awt.BorderLayout.CENTER);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 16));
        jPanel1.add(jLabel1, java.awt.BorderLayout.LINE_END);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel2, java.awt.BorderLayout.CENTER);

        pnlOrderDetail.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        add(pnlOrderDetail, java.awt.BorderLayout.PAGE_END);
        add(navbar, java.awt.BorderLayout.PAGE_START);

        pnlOrder.setOpaque(false);
        pnlOrder.setLayout(new java.awt.BorderLayout());

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Nhân viên", "Ngày thanh toán", "Tổng tiền hóa đơn", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneOrder.setViewportView(tableOrder);

        pnlOrder.add(scrollPaneOrder, java.awt.BorderLayout.CENTER);

        add(pnlOrder, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormBill card = new FormBill();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private gui.navbar.Navbar navbar;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlOrderDetail;
    private javax.swing.JScrollPane scrollPaneOrder;
    private javax.swing.JScrollPane scrollPaneOrderDetail;
    private gui.table.TableCustom tableOrder;
    private gui.table.TableCustom tableOrderDetail;
    // End of variables declaration//GEN-END:variables

    private void init() {
        navbar.getComboBox().addItem("Mã hóa đơn");
        navbar.getComboBox().addItem("Tên khách hàng");
        tableModel = (DefaultTableModel) tableOrder.getModel();
        tableModelOrderDetail = (DefaultTableModel) tableOrderDetail.getModel();
        setBackground(new Color(255, 255, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        TitledBorder titleOrder = new TitledBorder(
                BorderFactory.createLineBorder(new Color(202, 212, 216), 2),
                "Danh sách hóa đơn"
        );
        TitledBorder titleOrderDetail = new TitledBorder(
                BorderFactory.createLineBorder(new Color(202, 212, 216), 2),
                "Danh sách chi tiết hóa đơn"
        );
        titleOrder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titleOrderDetail.setTitleFont(new Font("Arial", Font.BOLD, 14));
        pnlOrder.setBorder(titleOrder);
        pnlOrderDetail.setBorder(titleOrderDetail);
        tableOrder.ScrollPaneTable(scrollPaneOrder);
        tableOrderDetail.ScrollPaneTable(scrollPaneOrderDetail);
        navbar.getBtnAdd().setText("Tạo lại hóa đơn");
        navbar.removeAdd();
        navbar.removeEdit();

        searchButton = navbar.getBtnSearch();
        btnImport = navbar.getBtnImport();
        btnExport = navbar.getBtnExport();
        btnAdd = navbar.getBtnAdd();

        tableOrder.addMouseListener(this);
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        btnAdd.addActionListener(this);

        try {
            ctx = new InitialContext();
            DocDuLieuOrderrVaoTable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();

        Object o = e.getSource();
        if (o.equals(btnExport)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // Kiểm tra đuôi file, nếu không có đuôi .csv thì thêm vào
                if (!filePath.toLowerCase().endsWith(".xls")) {
                    filePath += ".xls";
                }

                try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8")) {
                    // Thêm BOM vào đầu file để Excel nhận diện UTF-8
                    writer.write("\uFEFF");

                    model = (DefaultTableModel) tableOrder.getModel();

                    // Ghi tiêu đề cột
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        writer.write(model.getColumnName(col) + (col == model.getColumnCount() - 1 ? "\n" : ","));
                    }

                    // Ghi dữ liệu hàng
                    for (int row = 0; row < model.getRowCount(); row++) {
                        for (int col = 0; col < model.getColumnCount(); col++) {
                            writer.write(model.getValueAt(row, col).toString() + (col == model.getColumnCount() - 1 ? "\n" : ","));
                        }
                    }

                    JOptionPane.showMessageDialog(this, "Xuất danh sách khách hàng thành công!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất danh sách khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        if (o.equals(navbar.getBtnSearch())) {
            search();
        }
    }

    private void search() {
        try {
            String valueSearch = navbar.getTxtSearch().getText();
            ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
            String selectedValue = (String) comboBox.getSelectedItem();

            orderService = (IOrderService) ctx.lookup("rmi://" + HOST + ":" + PORT + "/OrderBUS");

            if (selectedValue.equals("Mã hóa đơn")) {
                if (valueSearch.matches("\\d+")) {
                    int id = Integer.parseInt(valueSearch);
                    Order order = orderService.getOrderById(id);
                    if (order != null) {
                        tableModel.setRowCount(0);
                        tableModel.addRow(new Object[]{
                                order.getId(),
                                order.getCustomer().getName(),
                                order.getEmployee().getFullName(),
                                order.getOrderDate(),
                                formatCurrencyVN(order.getTotalAmount()),
                                order.getStatus()
                        });
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mã hóa đơn phải là số!");
                }
            } else if (selectedValue.equals("Tên khách hàng")) {
                List<Order> orders = orderService.getOrdersByCustomerName(valueSearch);
                tableModel.setRowCount(0);
                if (!orders.isEmpty()) {
                    for (Order order : orders) {
                        tableModel.addRow(new Object[]{
                                order.getId(),
                                order.getCustomer().getName(),
                                order.getEmployee().getFullName(),
                                order.getOrderDate(),
                                order.getTotalAmount(),
                                order.getStatus()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableOrder.getSelectedRow();
        System.out.println("Row: " + row);
        if (row != -1) {
            int id = (int) tableOrder.getValueAt(row, 0);
            try {
                DocDuLieuOrderDetailVaoTable(id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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

    private void DocDuLieuOrderrVaoTable() throws Exception {
        tableModel = (DefaultTableModel) tableOrder.getModel();
        orderService = (IOrderService) ctx.lookup("rmi://" + HOST + ":" + PORT + "/OrderBUS");
        orderService.getAllOrders()
                .stream()
                .forEach(order -> tableModel.addRow(new Object[]{
                        order.getId(),
                        order.getCustomer().getName(),
                        order.getEmployee().getFullName(),
                        order.getOrderDate(),
                        formatCurrencyVN(order.getTotalAmount()),
                        order.getStatus()
                }));
    }

    private void DocDuLieuOrderDetailVaoTable(int id) throws Exception {
        tableModelOrderDetail = (DefaultTableModel) tableOrderDetail.getModel();
        tableModelOrderDetail.setRowCount(0);
        orderService = (IOrderService) ctx.lookup("rmi://" + HOST + ":" + PORT + "/OrderBUS");
        orderService.getOrderById(id)
                .getOrderDetails()
                .stream()
                .forEach(orderDetail -> tableModelOrderDetail.addRow(new Object[]{
                        orderDetail.getProduct().getId(),
                        orderDetail.getProduct().getProductName(),
                        orderDetail.getProduct().getPrice(),
                        orderDetail.getQuantity(),
                        orderDetail.getPrice()
                }));
    }
    public static String formatCurrencyVN(double amount) {
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyVN.format(amount);
    }
}
