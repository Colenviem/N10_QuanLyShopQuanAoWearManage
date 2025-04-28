package gui.application.form.other;

import bus.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
//import connectDB.ConnectDB;
//import dao.KhachHang_DAO;
//import entity.KhachHang;
import dto.*;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.navbar.Navbar;
import gui.textfield.TextFieldCustom;

import java.awt.*;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class FormProduct extends JPanel implements ActionListener, MouseListener {

    private Navbar navbar;
    private DefaultTableModel tableModel;
    private ButtonCustom searchButton;
    private NavButtonCustom btnAdd;
    private NavButtonCustom btnEdit;
    private NavButtonCustom btnImport;
    private NavButtonCustom btnExport;
    private ProductBUS pro_bus;

    public FormProduct() {
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
        lblDiemTL1 = new javax.swing.JLabel();
        txtPrice = new gui.textfield.TextPay();
        pnlTable = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tableProduct = new gui.table.TableCustom();

        setLayout(new java.awt.BorderLayout());

        pnlNavbar.setLayout(new java.awt.BorderLayout());
        add(pnlNavbar, java.awt.BorderLayout.PAGE_START);

        pnlContainer.setLayout(new java.awt.BorderLayout());

        pnlTxtCon.setBackground(new java.awt.Color(255, 255, 255));
        pnlTxtCon.setOpaque(false);
        pnlTxtCon.setPreferredSize(new java.awt.Dimension(500, 180));
        pnlTxtCon.setLayout(new java.awt.BorderLayout());

        pnlTxt.setBackground(new java.awt.Color(255, 255, 255));
        pnlTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTxt.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTxt.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblMaKH.setText("Mã sản phẩm");
        lblMaKH.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblMaKH);

        txtMaKH.setEnabled(false);
        txtMaKH.setPreferredSize(new java.awt.Dimension(200, 40));
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });
        pnlTxt.add(txtMaKH);

        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenKH.setText("Tên sản phẩm");
        lblTenKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTenKH);

        txtTenKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtTenKH);

        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("Giảm giá");
        lblSDT.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtSDT);

        lblDiemTL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiemTL.setText("Mô tả");
        lblDiemTL.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblDiemTL);

//        txtDiemTL.setEnabled(false);
        txtDiemTL.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtDiemTL);

        lblDiemTL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiemTL1.setText("Giá");
        lblDiemTL1.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(lblDiemTL1);

//        txtPrice.setEnabled(false);
        txtPrice.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtPrice);

        pnlTxtCon.add(pnlTxt, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTxtCon, java.awt.BorderLayout.PAGE_START);

        pnlTable.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTable.setLayout(new java.awt.BorderLayout());

        lblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        lblCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomer.setText("Danh sách sản phẩm");
        lblCustomer.setPreferredSize(new java.awt.Dimension(37, 40));
        pnlTable.add(lblCustomer, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBorder(null);

        tableProduct.setBorder(null);
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ảnh", "Mã sản phẩm", "Tên sản phẩm", "Giảm giá", "Mô tả", "Giá", "Số lượng tồn", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduct.setRowHeight(120);
        scrollPane.setViewportView(tableProduct);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(0).setPreferredWidth(250);
            tableProduct.getColumnModel().getColumn(1).setPreferredWidth(320);
            tableProduct.getColumnModel().getColumn(2).setPreferredWidth(320);
            tableProduct.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableProduct.getColumnModel().getColumn(4).setPreferredWidth(350);
            tableProduct.getColumnModel().getColumn(5).setPreferredWidth(250);
            tableProduct.getColumnModel().getColumn(6).setPreferredWidth(320);
            tableProduct.getColumnModel().getColumn(7).setPreferredWidth(200);
        }

        pnlTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTable, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormProduct card = new FormProduct();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDiemTL;
    private javax.swing.JLabel lblDiemTL1;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlNavbar;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JPanel pnlTxtCon;
    private javax.swing.JScrollPane scrollPane;
    private gui.table.TableCustom tableProduct;
    private gui.textfield.TextPay txtDiemTL;
    private gui.textfield.TextPay txtMaKH;
    private gui.textfield.TextPay txtPrice;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtTenKH;
    // End of variables declaration//GEN-END:variables

    private void init() throws Exception {
        String[] content = new String[]{"Mã sản phẩm", "Tên sản phẩm"};
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

        tableProduct.ScrollPaneTable(scrollPane);
        setBackground(new Color(246, 250, 255));

        searchButton = navbar.getBtnSearch();
        btnImport = navbar.getBtnImport();
        btnExport = navbar.getBtnExport();
        btnAdd = navbar.getBtnAdd();
        btnEdit = navbar.getBtnEdit();

        try {
            DocDuLieuDatabaseVaoTableStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnImport.addActionListener(this);
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        tableProduct.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
    }

    public void DocDuLieuDatabaseVaoTable() throws Exception {
        DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        pro_bus = new ProductBUS();
        tableModel = (DefaultTableModel) tableProduct.getModel();
        pro_bus.getAllProducts()
                .stream()
                .forEach(pro -> tableModel.addRow(new Object[]{
            pro.getImageUrl(),
            pro.getId(),
            pro.getProductName(),
            pro.getDiscount() * 100 + " %",
            pro.getDescription(),
            df.format(pro.getPrice()),
            pro.getStockQuantity(),
            pro.isStatus() ? "Còn" : "Ngưng"
        }));
    }

    public void DocDuLieuDatabaseVaoTableStatus() throws Exception {
        DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        pro_bus = new ProductBUS();
        tableModel = (DefaultTableModel) tableProduct.getModel();
        pro_bus.getProductByStatus(true)
                .stream()
                .forEach(pro -> tableModel.addRow(new Object[]{
                        pro.getImageUrl(),
                        pro.getId(),
                        pro.getProductName(),
                        pro.getDiscount() * 100 + " %",
                        pro.getDescription(),
                        df.format(pro.getPrice()),
                        pro.getStockQuantity(),
                        pro.isStatus()? "Còn" : "Ngưng"
                }));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
            txtMaKH.setText(model.getValueAt(row, 1).toString());
            txtTenKH.setText(model.getValueAt(row, 2).toString());
            txtSDT.setText(model.getValueAt(row, 3).toString());
            txtDiemTL.setText(model.getValueAt(row, 4).toString());
            txtPrice.setText(model.getValueAt(row, 5).toString());
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
            searchProduct();
        }
        if (o.equals(btnImport)) {
            importExecl();
        } else if (o.equals(btnExport)) {
            exportExecl();
        } else if (o.equals(btnEdit)) {
            editPro();
        }else if (o.equals(btnAdd)) {
            showAddProductDialog();
        }
    }

    private void searchProduct() {
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
        String searchText = comboBox.getSelectedItem().toString();
        String searchValue = navbar.getTxtSearch().getText();
        System.out.println(searchValue);
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            pro_bus = new ProductBUS();
            if (searchText.equals("Mã sản phẩm")) {
                if (searchValue.matches("\\d+")) {
                    int id = Integer.parseInt(searchValue);
                    Product pro = pro_bus.getProductById(id);
                    if (pro != null) {
                        tableModel.setRowCount(0);
                        tableModel.addRow(new Object[]{
                            pro.getImageUrl(),
                            pro.getId(),
                            pro.getProductName(),
                            pro.getDiscount(),
                            pro.getDescription(),
                            pro.getPrice(),
                            pro.getCategory(),
                            pro.isStatus() ? "Còn" : "Ngưng"
                        });
                    }
                }
            } else if (searchText.equals("Tên sản phẩm")) {
                List<Product> products = pro_bus.getProductByName(searchValue);
                if (products != null && !products.isEmpty()) {
                    tableModel.setRowCount(0);
                    products.stream()
                            .forEach((pro -> tableModel.addRow(new Object[]{
                        pro.getImageUrl(),
                        pro.getId(),
                        pro.getProductName(),
                        pro.getDiscount(),
                        pro.getDescription(),
                        pro.getPrice(),
                        pro.getCategory(),
                        pro.isStatus() ? "Còn" : "Ngưng"
                    })));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                DocDuLieuDatabaseVaoTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editPro() {
        try {
            pro_bus = new ProductBUS();

            int id = Integer.parseInt(txtMaKH.getText());
            String name = txtTenKH.getText();
            String discountStr = txtSDT.getText().replace(" %", ""); // Loại bỏ dấu % nếu có
            double giamGia = Double.parseDouble(discountStr) / 100.0; // Chuyển về dạng số thập phân
            String moTa = txtDiemTL.getText();
            String priceStr = txtPrice.getText().replace(" VNĐ", "").replace(",", ""); // Loại bỏ ký hiệu tiền tệ và dấu phẩy
            double gia = Double.parseDouble(priceStr);

            Product product = pro_bus.getProductById(id);
            product.setProductName(name);
            product.setDiscount(giamGia);
            product.setDescription(moTa);
            product.setPrice(gia);
            if (pro_bus.updateProduct(product)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tableModel.setRowCount(0);
                DocDuLieuDatabaseVaoTable();
                clearText();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho Giảm giá và Giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearText() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtDiemTL.setText("");
        txtPrice.setText("");
    }



    private void createJOptionPane(String infor, String message) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy " + infor + " " + message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void importExecl() {
        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel để nhập");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToOpen), "UTF-8"))) {
                model = (DefaultTableModel) tableProduct.getModel();

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

    public void exportExecl() {
        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
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

                model = (DefaultTableModel) tableProduct.getModel();

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

    public void chonDong() {
        tableProduct.setRowSelectionInterval(0, 0);
        tableProduct.scrollRectToVisible(tableProduct.getCellRect(0, 0, true));
        tableProduct.requestFocusInWindow();
        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            txtMaKH.setText(model.getValueAt(row, 1).toString());
            txtTenKH.setText(model.getValueAt(row, 2).toString());
            txtSDT.setText(model.getValueAt(row, 3).toString());
            txtDiemTL.setText(model.getValueAt(row, 4).toString());
            txtPrice.setText(model.getValueAt(row, 5).toString());
        }
    }

    private void showAddProductDialog() {
        JDialog addProductDialog = new JDialog();
        addProductDialog.setTitle("Thêm sản phẩm");
        addProductDialog.setModal(true);
        addProductDialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // Increased rows for Price and Image
        JTextField txtTenSP = new JTextField(20);
        JTextField txtGiamGia = new JTextField(20);
        JTextField txtMoTa = new JTextField(20);
        JTextField txtGia = new JTextField(20);
        JComboBox<String> comboTrangThai = new JComboBox<>(new String[]{"Còn", "Ngưng"});
        JLabel lblImage = new JLabel("Chọn ảnh:");
        JTextField txtImagePath = new JTextField(20);  // Field to display the image path
        JButton btnChooseImage = new JButton("Chọn ảnh");

        inputPanel.add(new JLabel("Tên sản phẩm:"));
        inputPanel.add(txtTenSP);
        inputPanel.add(new JLabel("Giảm giá (%):"));
        inputPanel.add(txtGiamGia);
        inputPanel.add(new JLabel("Mô tả:"));
        inputPanel.add(txtMoTa);
        inputPanel.add(new JLabel("Giá (VNĐ):"));
        inputPanel.add(txtGia);
        inputPanel.add(new JLabel("Trạng thái:"));
        inputPanel.add(comboTrangThai);
        inputPanel.add(lblImage);             // Label for image selection
        inputPanel.add(txtImagePath);
        inputPanel.add(btnChooseImage);

        btnChooseImage.addActionListener(new ActionListener() { //add action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Image Files", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(addProductDialog);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtImagePath.setText(selectedFile.getAbsolutePath()); // Set the path
                }
            }
        });

        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            try {
                addProduct(
                        txtTenSP.getText().trim(),
                        txtGiamGia.getText().trim(),
                        txtMoTa.getText().trim(),
                        txtGia.getText().trim(),
                        (String) comboTrangThai.getSelectedItem(),
                        txtImagePath.getText().trim(), // Get image path
                        addProductDialog
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addProductDialog, "Vui lòng nhập đúng định dạng số cho Giảm giá và Giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addProductDialog, "Lỗi khi thêm sản phẩm vào database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);

        addProductDialog.add(inputPanel, BorderLayout.CENTER);
        addProductDialog.add(buttonPanel, BorderLayout.SOUTH);

        addProductDialog.setSize(400, 300); // Increased height
        addProductDialog.setLocationRelativeTo(this);
        addProductDialog.setVisible(true);
    }

    private void addProduct(String tenSP, String giamGiaStr, String moTa, String giaStr, String trangThaiStr, String imagePath, JDialog dialog) throws Exception {
        try {
            double giamGia = Double.parseDouble(giamGiaStr) / 100.0;
            double gia = Double.parseDouble(giaStr.replace(" VNĐ", "").replace(",", ""));
            boolean trangThai = trangThaiStr.equals("Còn");

            Product newProduct = new Product();
            newProduct.setProductName(tenSP);
            newProduct.setDiscount(giamGia);
            newProduct.setDescription(moTa);
            newProduct.setPrice(gia);
            newProduct.setStockQuantity(0);
            newProduct.setStatus(trangThai);
            newProduct.setImageUrl(imagePath); // Set the image path

            if (pro_bus.addProduct(newProduct)) {
                JOptionPane.showMessageDialog(dialog, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                tableModel.setRowCount(0);
                DocDuLieuDatabaseVaoTable();
                clearInputFields(dialog);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Thêm sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    private void clearInputFields(JDialog dialog) {
        Component[] components = ((JPanel) dialog.getContentPane().getComponent(0)).getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(0);
            }
        }
    }
}
