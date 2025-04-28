package gui.application.form.other;

import bus.AccountBUS;
import bus.SupplierBUS;
import dto.Account;
import dto.Supplier;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.navbar.Navbar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class FormSupplier extends JPanel implements ActionListener, MouseListener {

    private Navbar navbar;
    private SupplierBUS sup_bus;
    private DefaultTableModel tableModel;

    public FormSupplier() {
        try {
            sup_bus = new SupplierBUS();
            initComponents();
            init();
            DocDuLieuDatabaseVaoTable(); // Đẩy dữ liệu lên bảng khi khởi tạo
        } catch (Exception e) {
            e.printStackTrace();
        }
        ButtonCustom searchButton = navbar.getBtnSearch();
        NavButtonCustom btnImport = navbar.getBtnImport();
        NavButtonCustom btnExport = navbar.getBtnExport();
        btnImport.addActionListener(this);
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        navbar.getBtnAdd().addActionListener(this);
        navbar.getBtnEdit().addActionListener(this);
        tableCustomer.addMouseListener(this);
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
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new gui.textfield.TextPay();
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
        pnlTxtCon.setPreferredSize(new java.awt.Dimension(500, 130));
        pnlTxtCon.setLayout(new java.awt.BorderLayout());

        pnlTxt.setBackground(new java.awt.Color(255, 255, 255));
        pnlTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTxt.setPreferredSize(new java.awt.Dimension(100, 100));
        pnlTxt.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblMaKH.setText("Mã nhà cung cấp");
        lblMaKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblMaKH);

        txtMaKH.setEnabled(false);
        txtMaKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtMaKH);

        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenKH.setText("Tên nhà cung cấp");
        lblTenKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTenKH);

        txtTenKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtTenKH);

        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("Số điện thoại");
        lblSDT.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtSDT);

        lblDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiaChi.setText("Địa chỉ");
        lblDiaChi.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblDiaChi);

        txtDiaChi.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtDiaChi);

        pnlTxtCon.add(pnlTxt, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTxtCon, java.awt.BorderLayout.PAGE_START);

        pnlTable.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTable.setLayout(new java.awt.BorderLayout());

        lblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        lblCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomer.setText("Danh sách nhà cung cấp");
        lblCustomer.setPreferredSize(new java.awt.Dimension(37, 40));
        pnlTable.add(lblCustomer, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBorder(null);

        tableCustomer.setBorder(null);
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ", "Trạng thái"
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
            tableCustomer.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableCustomer.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableCustomer.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableCustomer.getColumnModel().getColumn(3).setPreferredWidth(320);
            tableCustomer.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        pnlTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTable, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormSupplier card = new FormSupplier();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDiaChi;
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
    private gui.textfield.TextPay txtDiaChi;
    private gui.textfield.TextPay txtMaKH;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtTenKH;
    // End of variables declaration//GEN-END:variables

    private void init() {

        String[] content = new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp"};
        navbar = new Navbar();
        navbar.setDefaultComboBox(new DefaultComboBoxModel<>(content));

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
    }

    public void DocDuLieuDatabaseVaoTable() throws RemoteException {
        List<Supplier> list = sup_bus.getAllSuppliers();
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        model.setRowCount(0);
        for (Supplier ncc : list) {
            String trangThai = ncc.isStatus() ? "Còn" : "Ngưng";
            model.addRow(new Object[]{
                ncc.getId(), ncc.getSupplierName(), ncc.getPhone(), ncc.getAddress(), trangThai
            });
        }
    }

    private void searchVen() {
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
        String selectedValue = (String) comboBox.getSelectedItem();
        DefaultTableModel modelVen = (DefaultTableModel) tableCustomer.getModel();

        List<Supplier> nccList = new ArrayList<>();
        modelVen.setRowCount(0); // Xóa dữ liệu cũ trước khi tìm kiếm
        Supplier supplier = new Supplier();
        try {
            if (!navbar.getTxtSearch().getText().equals("Nhập nội dung tìm kiếm...") && !navbar.getTxtSearch().getText().isEmpty()) {
                switch (selectedValue) {
                    case "Mã nhà cung cấp":
                        try {
                            int id = Integer.parseInt(navbar.getTxtSearch().getText());
                            supplier = sup_bus.getSupplierById(id);
                            nccList.add(supplier);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp phải là một số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return; // Dừng tìm kiếm nếu không phải là số
                        }
                        break;
                    case "Tên nhà cung cấp":
                        nccList = sup_bus.getSupplierBySupplierName(navbar.getTxtSearch().getText());
                        break;
                }
                if (nccList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhà cung cấp với: " + navbar.getTxtSearch().getText(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                nccList = sup_bus.getAllSuppliers();
            }

            // Hiển thị kết quả lên table
            for (Supplier ncc : nccList) {
                String trangThai = ncc.isStatus() ? "Còn" : "Ngưng";
                modelVen.addRow(new Object[]{
                    ncc.getId(),
                    ncc.getSupplierName(),
                    ncc.getPhone(),
                    ncc.getAddress(),
                    trangThai
                });
            }

            // Không cần dùng SwingUtilities.invokeLater ở đây vì không có thao tác nặng trên EDT
            modelVen.fireTableDataChanged();
            tableCustomer.revalidate();
            tableCustomer.repaint();
            JScrollPane scrollPane = (JScrollPane) tableCustomer.getParent().getParent();
            scrollPane.revalidate();
            scrollPane.repaint();

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(navbar.getBtnSearch())) {
            searchVen();
        } else if (o.equals(navbar.getBtnAdd())) {
            showAddSupplierDialog();
        } else if (o.equals(navbar.getBtnEdit())) {
            editSupplier();
        } else if (o.equals(navbar.getBtnImport())) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file Excel để nhập");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls"));
            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToOpen), "UTF-8"))) {
                    tableModel = (DefaultTableModel) tableCustomer.getModel();

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
                        tableModel.addRow(data);
                    }

                    JOptionPane.showMessageDialog(this, "Nhập dữ liệu từ file Excel thành công!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi nhập dữ liệu từ file Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (o.equals(navbar.getBtnExport())) {
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

                    tableModel = (DefaultTableModel) tableCustomer.getModel();

                    // Ghi tiêu đề cột
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        writer.write(tableModel.getColumnName(col) + (col == tableModel.getColumnCount() - 1 ? "\n" : ","));
                    }

                    // Ghi dữ liệu hàng
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        for (int col = 0; col < tableModel.getColumnCount(); col++) {
                            writer.write(tableModel.getValueAt(row, col).toString() + (col == tableModel.getColumnCount() - 1 ? "\n" : ","));
                        }
                    }

                    JOptionPane.showMessageDialog(this, "Xuất danh sách khách hàng thành công!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất danh sách khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        int row = tableCustomer.getSelectedRow();
        if (row != -1) {
            txtMaKH.setText(model.getValueAt(row, 0).toString());
            txtTenKH.setText(model.getValueAt(row, 1).toString());
            txtSDT.setText(model.getValueAt(row, 2).toString());
            txtDiaChi.setText(model.getValueAt(row,3).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e
    ) {

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
    }

    private void showAddSupplierDialog() { // Đổi tên hàm
        JDialog addSupplierDialog = new JDialog(); // Đổi tên biến
        addSupplierDialog.setTitle("Thêm Nhà Cung Cấp"); // Đổi title
        addSupplierDialog.setModal(true);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Thay đổi số hàng lưới
        JTextField txtTenNCC = new JTextField(20); // Đổi tên biến
        JTextField txtDiaChi = new JTextField(20);  // Thêm text field cho địa chỉ
        JTextField txtSDT = new JTextField(20);    // Thêm text field cho số điện thoại
        JComboBox<String> comboStatus = new JComboBox<>(new String[]{"Còn hoạt động", "Ngừng hoạt động"}); // Vietnamese options

        inputPanel.add(new JLabel("Tên NCC:")); // Đổi label
        inputPanel.add(txtTenNCC); // Đổi tên biến
        inputPanel.add(new JLabel("Địa chỉ:")); // Đổi label
        inputPanel.add(txtDiaChi);
        inputPanel.add(new JLabel("SĐT:")); // Đổi label
        inputPanel.add(txtSDT);
        inputPanel.add(new JLabel("Trạng thái:")); // Đổi label
        inputPanel.add(comboStatus);

        JButton btnAdd = new JButton("Thêm"); // Giữ nguyên tên biến nếu cần
        btnAdd.addActionListener(e -> {
            try {
                addSupplier(txtTenNCC.getText().trim(), txtDiaChi.getText().trim(), txtSDT.getText().trim(), (String) comboStatus.getSelectedItem(), addSupplierDialog); // Gọi hàm addSupplier
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addSupplierDialog, "Lỗi khi thêm nhà cung cấp vào database!", "Lỗi", JOptionPane.ERROR_MESSAGE); // Thông báo lỗi phù hợp
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);

        addSupplierDialog.add(inputPanel, BorderLayout.CENTER); // Đổi tên biến
        addSupplierDialog.add(buttonPanel, BorderLayout.SOUTH);

        addSupplierDialog.setSize(400, 200); // Có thể điều chỉnh kích thước
        addSupplierDialog.setLocationRelativeTo(this);
        addSupplierDialog.setVisible(true);
    }

    private void addSupplier(String tenNCC, String diaChi, String sdt, String trangThai, JDialog addSupplierDialog) throws SQLException { // Thêm các tham số cần thiết
        if (tenNCC.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(addSupplierDialog, "Vui lòng điền đầy đủ thông tin nhà cung cấp.", "Thông báo", JOptionPane.WARNING_MESSAGE); // Thông báo phù hợp
            return;
        }

        Supplier newSupplier = new Supplier(); // Sử dụng đối tượng Supplier
        newSupplier.setSupplierName(tenNCC);
        newSupplier.setAddress(diaChi);
        newSupplier.setPhone(sdt);
        newSupplier.setStatus(trangThai.equals("Còn hoạt động")); // Chuyển đổi trạng thái
        //newSupplier.setCreatedDate(LocalDate.now()); // Không cần thiết, Supplier không có thuộc tính này

        try {
            if (sup_bus.addSupplier(newSupplier)) { // Gọi BUS để thêm nhà cung cấp
                DocDuLieuDatabaseVaoTable(); // Cập nhật bảng
                addSupplierDialog.dispose();
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE); // Thông báo thành công
            } else {
                JOptionPane.showMessageDialog(addSupplierDialog, "Không thể thêm nhà cung cấp. Có thể đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE); // Thông báo lỗi cụ thể
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(addSupplierDialog, "Lỗi khi thêm nhà cung cấp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE); // Hiển thị chi tiết lỗi
        }
    }

    private void editSupplier() {
        try {
            sup_bus = new SupplierBUS();

            int id = Integer.parseInt(txtMaKH.getText()); // Mã nhà cung cấp
            String name = txtTenKH.getText(); // Tên nhà cung cấp
            String phone = txtSDT.getText(); // Số điện thoại
            String address = txtDiaChi.getText(); // Địa chỉ
            // Note: Status is not directly available in text fields; assume it's handled elsewhere or add a field
            // For simplicity, we'll assume status is not updated here or is managed separately

            Supplier supplier = sup_bus.getSupplierById(id);
            if (supplier == null) {
                JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            supplier.setSupplierName(name);
            supplier.setPhone(phone);
            supplier.setAddress(address);
            // If you want to update status, you need a way to get it (e.g., from a combo box or another field)
            // Example: supplier.setStatus(statusComboBox.getSelectedItem().equals("Còn hoạt động"));

            if (sup_bus.updateSupplier(supplier)) {
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                DocDuLieuDatabaseVaoTable();
                clearTextFields();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho Mã nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật nhà cung cấp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearTextFields() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
    }

}
