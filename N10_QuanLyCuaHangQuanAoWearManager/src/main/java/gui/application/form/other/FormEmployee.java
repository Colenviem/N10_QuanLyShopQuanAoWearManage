package gui.application.form.other;

//import connectDB.ConnectDB;
//import dao.NhanVien_DAO;
//import dao.NhaThuoc_DAO;
//import dao.ChucVu_DAO;
//import dao.TaiKhoan_DAO;
//import entity.ChucVu;
//import entity.TaiKhoan;
//import entity.NhaThuoc;
import bus.AccountBUS;
import bus.CustomerBUS;
import bus.EmployeeBUS;
import bus.StoreBUS;
import dto.*;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.navbar.Navbar;

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
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class FormEmployee extends JPanel implements ActionListener, MouseListener {

    private Navbar navbar;
    private DefaultTableModel tableModel;
    private NavButtonCustom btnAdd;
    private NavButtonCustom btnEdit;
    private EmployeeBUS employeeBUS;
    private ButtonCustom searchButton;
    private NavButtonCustom btnImport;
    private NavButtonCustom btnExport;
    private AccountBUS accountBUS;
    private StoreBUS storeBUS;

    public FormEmployee() {
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

        txtEmail = new gui.textfield.TextPay();
        btnXoaTrang = new javax.swing.JButton();
        pnlNavbar = new javax.swing.JPanel();
        pnlContainer = new javax.swing.JPanel();
        pnlTxtCon = new javax.swing.JPanel();
        pnlTxt = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new gui.textfield.TextPay();
        lblTenNV = new javax.swing.JLabel();
        txtTenNV = new gui.textfield.TextPay();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new gui.textfield.TextPay();
        lblNgayThue = new javax.swing.JLabel();
        txtNgayThue = new gui.textfield.TextPay();
        lblVaiTro = new javax.swing.JLabel();
        txtVaiTro = new gui.textfield.TextPay();
        lblLuong = new javax.swing.JLabel();
        txtLuong = new gui.textfield.TextPay();
        lblTinhTrang = new javax.swing.JLabel();
        checkBoxTinhTrang = new javax.swing.JCheckBox();
        lblCuaHang = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        pnlTable = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tableEmployee = new gui.table.TableCustom();
        cbCuaHang = new ComboBoxSuggestion<>();
        cbTaiKhoan = new ComboBoxSuggestion<>();
        cbVaiTro = new ComboBoxSuggestion<>();

        txtEmail.setEnabled(false);
        txtEmail.setPreferredSize(new java.awt.Dimension(200, 40));

        btnXoaTrang.setText("jButton1");

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
        pnlTxt.setPreferredSize(new java.awt.Dimension(100, 60));
        pnlTxt.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblMaNV.setText("Mã nhân viên");
        lblMaNV.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblMaNV);

        txtMaNV.setEnabled(false);
        txtMaNV.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtMaNV);

        lblTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNV.setText("Tên nhân viên");
        lblTenNV.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTenNV);

        txtTenNV.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtTenNV);

        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("Số điện thoại");
        lblSDT.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtSDT);

        lblNgayThue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayThue.setText("Ngày thuê");
        lblNgayThue.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlTxt.add(lblNgayThue);

        txtNgayThue.setPreferredSize(new java.awt.Dimension(300, 40));
        pnlTxt.add(txtNgayThue);

        lblVaiTro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVaiTro.setText("Vai trò");
        lblVaiTro.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlTxt.add(lblVaiTro);

        List<String> vaiTroList = List.of("Nhân viên", "Quản lý", "Quản lý cửa hàng");

        vaiTroList.stream()
                .forEach(vaiTro -> {
                    cbVaiTro.addItem(vaiTro);
                });

        cbVaiTro.setPreferredSize(new java.awt.Dimension(250, 40));
        pnlTxt.add(cbVaiTro);

        lblLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLuong.setText("Lương");
        lblLuong.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblLuong);

        txtLuong.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(txtLuong);

        lblTinhTrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTinhTrang.setText("Tình trạng");
        lblTinhTrang.setPreferredSize(new java.awt.Dimension(60, 40));
        pnlTxt.add(lblTinhTrang);

        checkBoxTinhTrang.setOpaque(false);
        pnlTxt.add(checkBoxTinhTrang);

        lblCuaHang.setText("Cửa hàng");
        pnlTxt.add(lblCuaHang);

        cbCuaHang.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(cbCuaHang);

        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setText("Tài khoản");
        lblTaiKhoan.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTaiKhoan);

        cbTaiKhoan.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(cbTaiKhoan);

        pnlTxtCon.add(pnlTxt, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTxtCon, java.awt.BorderLayout.PAGE_START);

        pnlTable.setBackground(new java.awt.Color(255, 255, 255));
        pnlTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 218), 2));
        pnlTable.setLayout(new java.awt.BorderLayout());

        lblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        lblCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustomer.setText("Danh sách nhân viên");
        lblCustomer.setPreferredSize(new java.awt.Dimension(37, 40));
        pnlTable.add(lblCustomer, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBorder(null);

        tableEmployee.setBorder(null);
        tableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tài khoản", "Ngày thuê", "Số điện thoại", "Vai trò", "Lương", "Tình trạng", "Cửa hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(tableEmployee);
        if (tableEmployee.getColumnModel().getColumnCount() > 0) {
            tableEmployee.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableEmployee.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableEmployee.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(5).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(6).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        txtMaNV.setEnabled(false);

        pnlTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTable, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormEmployee card = new FormEmployee();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JLabel lblCuaHang;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblTinhTrang;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblNgayThue;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlNavbar;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JPanel pnlTxtCon;
    private javax.swing.JScrollPane scrollPane;
    private gui.table.TableCustom tableEmployee;
    private gui.textfield.TextPay txtNgayThue;
    private gui.textfield.TextPay txtEmail;
    private gui.textfield.TextPay txtMaNV;
    private gui.textfield.TextPay txtVaiTro;
    private gui.textfield.TextPay txtLuong;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtTenNV;
    private javax.swing.JCheckBox checkBoxTinhTrang;
    private ComboBoxSuggestion<String> cbCuaHang;
    private ComboBoxSuggestion<String> cbTaiKhoan;
    private ComboBoxSuggestion<String> cbVaiTro;
    // End of variables declaration//GEN-END:variables

    private void init() {
        String[] content = new String[]{"Mã nhân viên", "Tên nhân viên", "Số điện thoại"};
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

        tableEmployee.ScrollPaneTable(scrollPane);
        tableEmployee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setBackground(new Color(246, 250, 255));

        searchButton = navbar.getBtnSearch();
        btnImport = navbar.getBtnImport();
        btnExport = navbar.getBtnExport();
        btnAdd= navbar.getBtnAdd();
        btnEdit=navbar.getBtnEdit();

        btnImport.addActionListener(this);
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        tableEmployee.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnXoaTrang.addActionListener(this);

        try {
            DocDuLieu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(searchButton)) {
            searchNhanVien();
        } else if (o.equals(btnImport)) {
            importExecl();
        } else if (o.equals(btnExport)) {
            exportExecl();
        }else if(o.equals(btnAdd)){
            try {
                addEmployee();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if(o.equals(btnEdit)){
            try {
                editEmployee();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableEmployee.getSelectedRow();
        if (row != -1) {
            txtMaNV.setText(tableEmployee.getValueAt(row, 0).toString());
            txtTenNV.setText(tableEmployee.getValueAt(row, 1).toString());
            cbTaiKhoan.setSelectedItem(tableEmployee.getValueAt(row, 2).toString());
            txtNgayThue.setText(tableEmployee.getValueAt(row, 3).toString());
            txtSDT.setText(tableEmployee.getValueAt(row, 4).toString());
            txtVaiTro.setText(tableEmployee.getValueAt(row, 5).toString());
            txtLuong.setText(tableEmployee.getValueAt(row, 6).toString());
            checkBoxTinhTrang.setSelected(tableEmployee.getValueAt(row, 7).toString().equals("Đang làm"));
            cbCuaHang.setSelectedItem(tableEmployee.getValueAt(row, 8).toString());
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

    private void searchNhanVien() {
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
        String searchText = comboBox.getSelectedItem().toString();
        String searchValue = navbar.getTxtSearch().getText();
        System.out.println(searchValue);
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            employeeBUS = new EmployeeBUS();
            if (searchText.equals("Mã khách hàng")) {
                if (searchValue.matches("\\d+")) {
                    int id = Integer.parseInt(searchValue);
                    Employee employee = employeeBUS.getEmployeeById(id);
                    if (employee != null) {
                        tableModel.setRowCount(0);
                        Role roleEnum = employee.getRole();
                        String role = null;

                        if (roleEnum == Role.MANAGER) {
                            role = "Quản lý";
                        } else if (roleEnum == Role.STORE_MANAGER) {
                            role = "Quản lý cửa hàng";
                        } else if (roleEnum == Role.EMPLOYEE) {
                            role = "Nhân viên";
                        }

                        tableModel.addRow(new Object[] {
                                employee.getId(),
                                employee.getFullName(),
                                employee.getAccount().getUsername(),
                                employee.getHireDate(),
                                employee.getPhone(),
                                role,
                                employee.getSalary(),
                                employee.isStatus() ? "Đang làm" : "Nghỉ việc",
                                employee.getStore().getName()
                        });
                    }
                }
            } else if (searchText.equals("Tên khách hàng")) {
                List<Employee> employees = employeeBUS.getEmployeeByName(searchValue);
                if (employees != null && !employees.isEmpty()) {
                    tableModel.setRowCount(0);
                    employees.forEach(employee -> {
                        Role roleEnum = employee.getRole();
                        String role = null;

                        if (roleEnum == Role.MANAGER) {
                            role = "Quản lý";
                        } else if (roleEnum == Role.STORE_MANAGER) {
                            role = "Quản lý cửa hàng";
                        } else if (roleEnum == Role.EMPLOYEE) {
                            role = "Nhân viên";
                        }

                        tableModel.addRow(new Object[] {
                                employee.getId(),
                                employee.getFullName(),
                                employee.getAccount().getUsername(),
                                employee.getHireDate(),
                                employee.getPhone(),
                                role,
                                employee.getSalary(),
                                employee.isStatus() ? "Đang làm" : "Nghỉ việc",
                                employee.getStore().getName()
                        });
                    });
                }
            } else if (searchText.equals("Số điện thoại")) {
                List<Employee> employees = employeeBUS.getEmployeeByName(searchValue);
                if (employees != null && !employees.isEmpty()) {
                    tableModel.setRowCount(0);
                    employees.forEach(employee -> {
                        Role roleEnum = employee.getRole();
                        String role = null;

                        if (roleEnum == Role.MANAGER) {
                            role = "Quản lý";
                        } else if (roleEnum == Role.STORE_MANAGER) {
                            role = "Quản lý cửa hàng";
                        } else if (roleEnum == Role.EMPLOYEE) {
                            role = "Nhân viên";
                        }

                        tableModel.addRow(new Object[] {
                                employee.getId(),
                                employee.getFullName(),
                                employee.getAccount().getUsername(),
                                employee.getHireDate(),
                                employee.getPhone(),
                                role,
                                employee.getSalary(),
                                employee.isStatus() ? "Đang làm" : "Nghỉ việc",
                                employee.getStore().getName()
                        });
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEmployee() throws Exception {
        if(valiData()){
            employeeBUS = new EmployeeBUS();
            accountBUS = new AccountBUS();
            storeBUS = new StoreBUS();
            Employee employee = new Employee();
            employee.setFullName(txtTenNV.getText());
            employee.setHireDate(LocalDate.now());
            employee.setPhone(txtSDT.getText());
            if(cbVaiTro.getSelectedItem().equals("Nhân viên")) {
                employee.setRole(Role.EMPLOYEE);
            } else if(cbVaiTro.getSelectedItem().equals("Quản lý")) {
                employee.setRole(Role.MANAGER);
            } else if(cbVaiTro.getSelectedItem().equals("Quản lý cửa hàng")) {
                employee.setRole(Role.STORE_MANAGER);
            }
            employee.setSalary(Double.parseDouble(txtLuong.getText()));
            employee.setStatus(checkBoxTinhTrang.isSelected());
            Account account = accountBUS.getAccountByUsername((String) cbTaiKhoan.getSelectedItem()).getFirst();
            Store store = storeBUS.getStoreByStoreName((String) cbCuaHang.getSelectedItem()).getFirst();
            employee.setAccount(account);
            employee.setStore(store);
            employeeBUS.addEmployee(employee);
        }
    }

    private void editEmployee() throws Exception {
        if(valiData()){
            employeeBUS = new EmployeeBUS();
            Employee employee = employeeBUS.getEmployeeById(Integer.parseInt(txtMaNV.getText()));
            employee.setFullName(txtTenNV.getText());
            employee.setHireDate(LocalDate.now());
            employee.setPhone(txtSDT.getText());
            if(cbVaiTro.getSelectedItem().equals("Nhân viên")) {
                employee.setRole(Role.EMPLOYEE);
            } else if(cbVaiTro.getSelectedItem().equals("Quản lý")) {
                employee.setRole(Role.MANAGER);
            } else if(cbVaiTro.getSelectedItem().equals("Quản lý cửa hàng")) {
                employee.setRole(Role.STORE_MANAGER);
            }
            employee.setSalary(Double.parseDouble(txtLuong.getText()));
            employee.setStatus(checkBoxTinhTrang.isSelected());
            Account account = accountBUS.getAccountByUsername((String) cbTaiKhoan.getSelectedItem()).getFirst();
            Store store = storeBUS.getStoreByStoreName((String) cbCuaHang.getSelectedItem()).getFirst();
            employee.setAccount(account);
            employee.setStore(store);
            employeeBUS.updateEmployee(employee);
        }
    }

    private void DocDuLieu() throws Exception {
        employeeBUS = new EmployeeBUS();
        accountBUS = new AccountBUS();
        storeBUS = new StoreBUS();

        accountBUS.getAccountsNotInEmployee()
                .stream()
                .forEach(account -> {
                    cbTaiKhoan.addItem(account.getUsername());
                });

        storeBUS.getAllStores()
                .stream()
                .forEach(store -> {
                    cbCuaHang.addItem(store.getName());
                });

        tableModel = (DefaultTableModel) tableEmployee.getModel();
        tableModel.setRowCount(0);
        employeeBUS.getAllEmployees()
                .forEach(employee -> {
                    Role roleEnum = employee.getRole();
                    String role = null;

                    if (roleEnum == Role.MANAGER) {
                        role = "Quản lý";
                    } else if (roleEnum == Role.STORE_MANAGER) {
                        role = "Quản lý cửa hàng";
                    } else if (roleEnum == Role.EMPLOYEE) {
                        role = "Nhân viên";
                    }

                    tableModel.addRow(new Object[] {
                            employee.getId(),
                            employee.getFullName(),
                            employee.getAccount().getUsername(),
                            employee.getHireDate(),
                            employee.getPhone(),
                            role,
                            employee.getSalary(),
                            employee.isStatus() ? "Đang làm" : "Nghỉ việc",
                            employee.getStore().getName()
                    });
                });
    }

    public boolean valiData(){
        if(txtTenNV.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
            return false;
        }
        if(txtSDT.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            return false;
        }
        if(txtLuong.getText().isEmpty() && !txtLuong.getText().matches("[0-9]+")){
            JOptionPane.showMessageDialog(this, "Lương không được để trống");
            return false;
        }

        return true;
    }

    public  void importExecl(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel để nhập");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToOpen), "UTF-8"))) {
                tableModel = (DefaultTableModel) tableEmployee.getModel();
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
    }

    public void exportExecl(){
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

                tableModel = (DefaultTableModel) tableEmployee.getModel();

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

                JOptionPane.showMessageDialog(this, "Xuất danh sách nhân viên thành công!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất danh sách nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
