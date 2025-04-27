package gui.application.form.other;

import bus.CustomerBUS;
import bus.StoreBUS;
import com.microsoft.sqlserver.jdbc.SQLServerException;
//import connectDB.ConnectDB;
//import dao.KhachHang_DAO;
//import entity.KhachHang;
import dto.Customer;
import dto.Store;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.navbar.Navbar;
import gui.textfield.TextFieldCustom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class FormStore extends JPanel implements ActionListener, MouseListener {
    private Navbar navbar;
    private DefaultTableModel tableModel;
    private ButtonCustom searchButton;
    private NavButtonCustom btnAdd;
    private NavButtonCustom btnEdit;
    private NavButtonCustom btnImport;
    private NavButtonCustom btnExport;
    private StoreBUS storeBUS;

    public FormStore() {
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

        pnlNavbar = new javax.swing.JPanel();
        pnlContainer = new javax.swing.JPanel();
        pnlTxtCon = new javax.swing.JPanel();
        pnlTxt = new javax.swing.JPanel();
        lblMaKH = new javax.swing.JLabel();
        txtMaKH = new gui.textfield.TextPay();
        lblTenKH = new javax.swing.JLabel();
        txtTenKH = new gui.textfield.TextPay();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new gui.textfield.TextPay();
        lblDiemTL = new javax.swing.JLabel();
        txtDiemTL = new gui.textfield.TextPay();
        pnlTable = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tableCustomer = new gui.table.TableCustom();

        setLayout(new java.awt.BorderLayout());

        pnlNavbar.setLayout(new java.awt.BorderLayout());
        add(pnlNavbar, java.awt.BorderLayout.PAGE_START);

        pnlContainer.setLayout(new java.awt.BorderLayout());

        pnlTxtCon.setBackground(new java.awt.Color(255, 255, 255));
        pnlTxtCon.setOpaque(false);
        pnlTxtCon.setPreferredSize(new java.awt.Dimension(500, 96));
        pnlTxtCon.setLayout(new java.awt.BorderLayout());

        pnlTxt.setBackground(new java.awt.Color(255, 255, 255));
        pnlTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTxt.setPreferredSize(new java.awt.Dimension(80, 60));
        pnlTxt.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblMaKH.setText("Mã cửa hàng");
        lblMaKH.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblMaKH);

        txtMaKH.setEnabled(false);
        txtMaKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtMaKH);

        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenKH.setText("Tên cửa hàng");
        lblTenKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTenKH);

        txtTenKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtTenKH);

        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("Địa chỉ");
        lblSDT.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtSDT);

        lblDiemTL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiemTL.setText("Số điện thoại");
        lblDiemTL.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblDiemTL);

        txtDiemTL.setEnabled(false);
        txtDiemTL.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtDiemTL);

        pnlTxtCon.add(pnlTxt, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTxtCon, java.awt.BorderLayout.PAGE_START);

        pnlTable.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTable.setLayout(new java.awt.BorderLayout());

        lblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        lblCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomer.setText("Danh sách cửa hàng");
        lblCustomer.setPreferredSize(new java.awt.Dimension(37, 40));
        pnlTable.add(lblCustomer, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBorder(null);

        tableCustomer.setBorder(null);
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã cửa hàng", "Tên cửa hàng", "Địa chỉ", "Số điện thoại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(tableCustomer);
        if (tableCustomer.getColumnModel().getColumnCount() > 0) {
            tableCustomer.getColumnModel().getColumn(0).setPreferredWidth(320);
            tableCustomer.getColumnModel().getColumn(1).setPreferredWidth(320);
            tableCustomer.getColumnModel().getColumn(2).setPreferredWidth(320);
            tableCustomer.getColumnModel().getColumn(3).setPreferredWidth(320);
            tableCustomer.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        pnlTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTable, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args)  {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormStore card = new FormStore();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDiemTL;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlNavbar;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JPanel pnlTxtCon;
    private javax.swing.JScrollPane scrollPane;
    private gui.table.TableCustom tableCustomer;
    private gui.textfield.TextPay txtDiemTL;
    private gui.textfield.TextPay txtMaKH;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtTenKH;
    // End of variables declaration//GEN-END:variables

    private void init() throws Exception {
        String[] content = new String[]{"Mã cửa hàng", "Tên cửa hàng", "Số điện thoại"};
        navbar = new Navbar();
        navbar.setDefaultComboBox(new DefaultComboBoxModel<>(content));
        navbar.removeAdd();

        pnlNavbar.add(navbar);
        pnlNavbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlNavbar.setOpaque(false);

        pnlContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlContainer.setOpaque(false);
        pnlContainer.setBackground(new Color(255, 255, 255));

        lblCustomer.setPreferredSize(new Dimension(100, 40));
        lblCustomer.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblCustomer.setFont(new Font("Regular", Font.BOLD, 18));
        lblCustomer.setForeground(new Color(148, 148, 148));
        lblCustomer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 218)));

        Border comboBorder = new CompoundBorder(new LineBorder(new Color(206, 212, 218), 2), new EmptyBorder(10, 10, 0, 0));
        pnlTxt.setBorder(comboBorder);

        pnlTxtCon.setBorder(new EmptyBorder(0, 0, 20, 0));

        tableCustomer.ScrollPaneTable(scrollPane);
        setBackground(new Color(246, 250, 255));

        searchButton = navbar.getBtnSearch();
        btnImport = navbar.getBtnImport();
        btnExport = navbar.getBtnExport();
        btnAdd = navbar.getBtnAdd();
        btnEdit =navbar.getBtnEdit();

        try {
            DocDuLieuDatabaseVaoTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnImport.addActionListener(this);
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        tableCustomer.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
    }

    public void DocDuLieuDatabaseVaoTable() throws Exception {
        storeBUS = new StoreBUS();
        tableModel = (DefaultTableModel) tableCustomer.getModel();
        storeBUS.getAllStores()
                .stream()
                .forEach(store -> tableModel.addRow(new Object[]{
                        store.getId(),
                        store.getName(),
                        store.getAddress(),
                        store.getPhone(),
                        store.isStatus() ? "Còn" : "Ngưng"
                }));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableCustomer.getSelectedRow();
        if(row != -1) {
            DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
            txtMaKH.setText(model.getValueAt(row, 0).toString());
            txtTenKH.setText(model.getValueAt(row, 1).toString());
            txtSDT.setText(model.getValueAt(row, 2).toString());
            txtDiemTL.setText(model.getValueAt(row, 3).toString());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(searchButton)) {
            searchKhachHang();
        }
        if (o.equals(btnImport)) {
            importExecl();
        } else if (o.equals(btnExport)) {
            exportExecl();
        }else if(o.equals(btnEdit)){
            editKH();
        }
    }

    private void searchKhachHang() {
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
        String searchText = comboBox.getSelectedItem().toString();
        String searchValue = navbar.getTxtSearch().getText();
        System.out.println(searchValue);
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            storeBUS = new StoreBUS();
            if (searchText.equals("Mã khách hàng")) {
                if (searchValue.matches("\\d+")) {
                    int id = Integer.parseInt(searchValue);
                    Store store = storeBUS.getStoreById(id);
                    if (store != null) {
                        tableModel.setRowCount(0);
                        tableModel.addRow(new Object[]{
                                store.getId(),
                                store.getName(),
                                store.getAddress(),
                                store.getPhone(),
                                store.isStatus() ? "Còn" : "Ngưng"
                        });
                    }
                }
            } else if (searchText.equals("Tên khách hàng")) {
                List<Store> stores = storeBUS.getStoreByStoreName(searchValue);
                if (stores != null && !stores.isEmpty()) {
                    tableModel.setRowCount(0);
                    stores.stream()
                            .forEach((store -> tableModel.addRow(new Object[]{
                                    store.getId(),
                                    store.getName(),
                                    store.getAddress(),
                                    store.getPhone(),
                                    store.isStatus() ? "Còn" : "Ngưng"
                            })));
                }
            } else if (searchText.equals("Số điện thoại")) {
                List<Store> stores = storeBUS.getStoreByStoreName(searchValue);
                if (stores != null && !stores.isEmpty()) {
                    tableModel.setRowCount(0);
                    stores.stream()
                            .forEach((store -> tableModel.addRow(new Object[]{
                                    store.getId(),
                                    store.getName(),
                                    store.getAddress(),
                                    store.getPhone(),
                                    store.isStatus() ? "Còn" : "Ngưng"
                            })));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editKH() {
        try {
            storeBUS = new StoreBUS();

            if (!ValidInput()) {
                return;
            }

            int id = Integer.parseInt(txtMaKH.getText());
            String name = txtTenKH.getText();
            String phone = txtSDT.getText();
            Store store = storeBUS.getStoreById(id);
            store.setName(name);
            store.setPhone(phone);
            if(storeBUS.updateStore(store)){
                JOptionPane.showMessageDialog(this, "Cập nhật cửa hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tableModel.setRowCount(0);
                DocDuLieuDatabaseVaoTable();
                clearText();
            }else {
                JOptionPane.showMessageDialog(this, "Cập nhật cửa hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearText(){
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtDiemTL.setText("");
    }

    private boolean ValidInput(){
        String tenKH= txtTenKH.getText();
        String sdt= txtSDT.getText();
        if(!tenKH.matches("^[A-ZÀ-ỹ][a-zà-ỹ]*(?: [A-ZÀ-ỹ][a-zà-ỹ]*)*$")){
            JOptionPane.showMessageDialog(this, "Tên cửa hàng không hợp lệ! Phải có ít nhất hai từ, mỗi từ viết hoa chữ cái đầu và không chứa ký tự đặc biệt.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtTenKH.requestFocus();
            txtTenKH.selectAll();
            return false;
        }else if (!sdt.matches("^0[13579]\\d{8}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Phải bắt đầu bằng 0 và tiếp theo là 1, 3, 5, 7 hoặc 9, tổng cộng 10 chữ số.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtSDT.requestFocus();
            txtSDT.selectAll();
            return false;
        }
        return true;
    }

    private void createJOptionPane(String infor, String message) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy " + infor + " " + message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    public void importExecl(){
            DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file Excel để nhập");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls"));
            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToOpen), "UTF-8"))) {
                    model = (DefaultTableModel) tableCustomer.getModel();

                    String line;
                    boolean isFirstLine = true;
                    while ((line = reader.readLine()) != null) {
                        // Bỏ qua dòng đầu tiên nếu là tiêu đề
                        if (isFirstLine) {
                            isFirstLine = false;
                            continue;
                        }
                        // Chia dòng thành các phần tử
                        String[] data = line.split(",");
                        // Thêm dữ liệu vào model
                        model.addRow(data);
                    }
                    chonDong();
                    JOptionPane.showMessageDialog(this, "Nhập dữ liệu từ file Excel thành công!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi nhập dữ liệu từ file Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
    }
    
    public void exportExecl(){
            DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
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

                    model = (DefaultTableModel) tableCustomer.getModel();

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
                    chonDong();
                    JOptionPane.showMessageDialog(this, "Xuất danh sách khách hàng thành công!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất danh sách khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    public void chonDong(){
        tableCustomer.setRowSelectionInterval(0, 0);
        tableCustomer.scrollRectToVisible(tableCustomer.getCellRect(0, 0, true));
        tableCustomer.requestFocusInWindow();
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        int row = tableCustomer.getSelectedRow();
        if (row != -1) {
            txtMaKH.setText(model.getValueAt(row, 0).toString());
            txtTenKH.setText(model.getValueAt(row, 1).toString());
            txtSDT.setText(model.getValueAt(row, 2).toString());
            txtDiemTL.setText(model.getValueAt(row, 3).toString());
        }
    }
}
