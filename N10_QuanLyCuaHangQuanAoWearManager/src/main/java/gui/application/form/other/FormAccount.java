package gui.application.form.other;

import bus.AccountBUS;
import dto.Account;
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
import java.sql.SQLException;

public class FormAccount extends JPanel implements ActionListener, MouseListener {

    private Navbar navbar;
    private AccountBUS accountBUS;
    private DefaultTableModel tableModel;

    public FormAccount() {
        try {
            accountBUS = new AccountBUS();
            initComponents();
            init();
            DocDuLieuDatabaseVaoTableAcc(); // Đẩy dữ liệu lên bảng khi khởi tạo
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
        pnlTxtCon.setPreferredSize(new java.awt.Dimension(500, 100));
        pnlTxtCon.setLayout(new java.awt.BorderLayout());

        pnlTxt.setBackground(new java.awt.Color(255, 255, 255));
        pnlTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTxt.setPreferredSize(new java.awt.Dimension(100, 60));
        pnlTxt.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblMaKH.setText("Tài khoản");
        lblMaKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblMaKH);

        txtMaKH.setEnabled(false);
        txtMaKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtMaKH);

        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenKH.setText("Mật khẩu");
        lblTenKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTenKH);

        txtTenKH.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtTenKH);

        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("Quyền");
        lblSDT.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtSDT);

        pnlTxtCon.add(pnlTxt, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTxtCon, java.awt.BorderLayout.PAGE_START);

        pnlTable.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTable.setLayout(new java.awt.BorderLayout());

        lblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        lblCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomer.setText("Danh sách tài khoản");
        lblCustomer.setPreferredSize(new java.awt.Dimension(37, 40));
        pnlTable.add(lblCustomer, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBorder(null);

        tableCustomer.setBorder(null);
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Tài khoản", "Mật khẩu", "Trạng thái", "Ngày đăng ký"
            }
        ));
        scrollPane.setViewportView(tableCustomer);

        pnlTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTable, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormAccount card = new FormAccount();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCustomer;
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
    private gui.textfield.TextPay txtMaKH;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtTenKH;
    // End of variables declaration//GEN-END:variables

    private void init() {

        String[] content = new String[]{"Tài khoản"};
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

        navbar.removeEdit();
        pnlTxtCon.setBorder(new EmptyBorder(0, 0, 20, 0));

        tableCustomer.ScrollPaneTable(scrollPane);
        setBackground(new Color(246, 250, 255));
    }

    public void DocDuLieuDatabaseVaoTableAcc() {
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện có

        try {
            List<Account> activeAccounts = accountBUS.getAllActiveAccounts();
            for (Account tk : activeAccounts) {
                model.addRow(new Object[]{
                        tk.getUsername(),
                        tk.getPassword(), // Hiển thị mật khẩu đã che hoặc bạn có thể chọn không hiển thị
                        tk.isStatus() ? "Có hoạt động" : "Không hoạt động",
                        tk.getCreatedDate()
                });
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu tài khoản: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DocALLDatabaseVaoTableAcc() {
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện có

        try {
            List<Account> activeAccounts = accountBUS.getAllAccounts();
            for (Account tk : activeAccounts) {
                model.addRow(new Object[]{
                        tk.getUsername(),
                        tk.getPassword(), // Hiển thị mật khẩu đã che hoặc bạn có thể chọn không hiển thị
                        tk.isStatus() ? "Có hoạt động" : "Không hoạt động",
                        tk.getCreatedDate()
                });
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu tài khoản: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchAcc() {
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
        String selectedValueAcc = (String) comboBox.getSelectedItem();
        DefaultTableModel modelAcc = (DefaultTableModel) tableCustomer.getModel();
        String searchText = navbar.getTxtSearch().getText().trim();

        modelAcc.setRowCount(0);

        if (!searchText.equals("Nhập nội dung tìm kiếm...") && !searchText.isEmpty()) {
            try {
                List<Account> tkList = switch (selectedValueAcc) {
                    case "Tài khoản" -> accountBUS.getAccountByUsername(searchText);
                    default -> List.of(); // Handle other search criteria if added
                };

                if (tkList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản: " + searchText, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    DocDuLieuDatabaseVaoTableAcc();
                } else {
                    for (Account tk : tkList) {
                        modelAcc.addRow(new Object[]{
                                tk.getUsername(),
                                tk.getPassword(), // Or a masked password
                                tk.getCreatedDate()
                        });
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm tài khoản: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                DocALLDatabaseVaoTableAcc(); // Reload all data on error
            }
        } else {
            DocALLDatabaseVaoTableAcc(); // Reload all data if search text is empty
            return;
        }

        SwingUtilities.invokeLater(() -> {
            modelAcc.fireTableDataChanged();
            tableCustomer.revalidate();
            tableCustomer.repaint();
            if (tableCustomer.getParent() instanceof JViewport && tableCustomer.getParent().getParent() instanceof JScrollPane scrollPane) {
                scrollPane.revalidate();
                scrollPane.repaint();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(navbar.getBtnSearch())) {
            searchAcc();
        } else if (o.equals(navbar.getBtnAdd())) {
            showAddAccountDialog();
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

    private void showAddAccountDialog() {
        JDialog addAccountDialog = new JDialog();
        addAccountDialog.setTitle("Thêm Tài Khoản");
        addAccountDialog.setModal(true);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtTaiKhoan = new JTextField(20);
        JPasswordField txtMatKhau = new JPasswordField(20);
        JComboBox<String> comboStatus = new JComboBox<>(new String[]{"Có hoạt động", "Không hoạt động"}); // Vietnamese options

        inputPanel.add(new JLabel("Tên Tài Khoản:"));
        inputPanel.add(txtTaiKhoan);
        inputPanel.add(new JLabel("Mật Khẩu:"));
        inputPanel.add(txtMatKhau);
        inputPanel.add(new JLabel("Trạng thái:"));
        inputPanel.add(comboStatus);

        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            try {
                addAccount(txtTaiKhoan.getText().trim(), new String(txtMatKhau.getPassword()).trim(), (String) comboStatus.getSelectedItem(), addAccountDialog);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(addAccountDialog, "Lỗi khi thêm tài khoản vào database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);

        addAccountDialog.add(inputPanel, BorderLayout.CENTER);
        addAccountDialog.add(buttonPanel, BorderLayout.SOUTH);

        addAccountDialog.setSize(400, 200);
        addAccountDialog.setLocationRelativeTo(this);
        addAccountDialog.setVisible(true);
    }

    private void addAccount(String username, String password, String status, JDialog addAccountDialog) throws SQLException {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(addAccountDialog, "Vui lòng điền đầy đủ tên tài khoản và mật khẩu.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setStatus(status.equals("Có hoạt động"));
        newAccount.setCreatedDate(LocalDate.now());

        try {
            if (accountBUS.addAccount(newAccount)) {
                DocDuLieuDatabaseVaoTableAcc();
                addAccountDialog.dispose();
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(addAccountDialog, "Không thể thêm tài khoản. Có thể tài khoản đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(addAccountDialog, "Lỗi khi thêm tài khoản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}
