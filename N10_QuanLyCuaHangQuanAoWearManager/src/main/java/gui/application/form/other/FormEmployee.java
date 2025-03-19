package gui.application.form.other;

//import connectDB.ConnectDB;
//import dao.NhanVien_DAO;
//import dao.NhaThuoc_DAO;
//import dao.ChucVu_DAO;
//import dao.TaiKhoan_DAO;
//import entity.ChucVu;
//import entity.TaiKhoan;
//import entity.NhaThuoc;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class FormEmployee extends JPanel implements ActionListener, MouseListener {

    private Navbar navbar;
//    private NhanVien_DAO nv_dao;
//    private NhaThuoc_DAO nt_dao;
//    private ChucVu_DAO cv_dao;
//    private TaiKhoan_DAO tk_dao;
    private DefaultTableModel tableModel;
    private  NavButtonCustom btnAdd;
    private  NavButtonCustom btnEdit;

    public FormEmployee() {
        try {
//            ConnectDB.getInstance().connect();
//            nv_dao = new NhanVien_DAO();
            initComponents();
            init();
//            DocDuLieuDatabaseVaoTable(); // Đẩy dữ liệu lên bảng khi khởi tạo
        } catch (Exception e) {
            e.printStackTrace();
        }
//        nt_dao= new NhaThuoc_DAO();
//        cv_dao= new ChucVu_DAO();
//        tk_dao= new TaiKhoan_DAO();
        ButtonCustom searchButton = navbar.getBtnSearch();
        NavButtonCustom btnImport = navbar.getBtnImport();
        NavButtonCustom btnExport = navbar.getBtnExport();
        btnAdd= navbar.getBtnAdd();
        btnEdit=navbar.getBtnEdit();
        KeyStroke keythem = KeyStroke.getKeyStroke("control T");
        btnEdit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keythem, "AddAction");
        btnEdit.getActionMap().put("AddAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                addNhanVien();
            }
        });
        
        //        btnEdit.addActionListener(e -> editKH());
        KeyStroke keySua = KeyStroke.getKeyStroke("control S");
        btnEdit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keySua, "SaveAction");
        btnEdit.getActionMap().put("SaveAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                updateNhanVien();
            }
        });
        
//        btnImport.addActionListener(e -> importExecl());
        KeyStroke keyThemExecl = KeyStroke.getKeyStroke("F1");
        btnImport.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyThemExecl, "ImportAction");
        btnImport.getActionMap().put("ImportAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importExecl();
            }
        });
        
        KeyStroke keyXuatExecl = KeyStroke.getKeyStroke("F2");
        btnExport.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyXuatExecl, "ExportAction");
        btnExport.getActionMap().put("ExportAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportExecl();
            }
        });
        
        KeyStroke keyTim = KeyStroke.getKeyStroke("ENTER");
        searchButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyTim, "SearchAction");
        searchButton.getActionMap().put("SearchAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                searchNhanVien();
            }
        });
        btnXoaTrang.addActionListener(e -> clearText());
        KeyStroke keyXoaTrang = KeyStroke.getKeyStroke("control BACK_SPACE");
        btnXoaTrang.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyXoaTrang, "DeleteAction");
        btnXoaTrang.getActionMap().put("DeleteAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearText();
            }
        });
        
        
        
        btnImport.addActionListener(this);
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        tableEmployee.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnXoaTrang.addActionListener(this);
//        ArrayList<String> dsTrangThai = nv_dao.getdsTrangThai();
//        for (String trangThai : dsTrangThai) {
//            cbBTrangThai.addItem(trangThai);
//        }
//        ArrayList<String> dsTaiKhoan = nv_dao.getdsTaiKhoan();
//        for (String taiKhoan : dsTaiKhoan) {
//            cbBTaiKhoan.addItem(taiKhoan);
//        }
//        ArrayList<String> dsChucVu = nv_dao.getdsChucVu();
//        for (String chucVu : dsChucVu) {
//            cbBChucVu.addItem(chucVu);
//        }
//        ArrayList<String> dsHieuThuoc = nv_dao.getdsHieuThuoc();
//        for (String hieuThuoc : dsHieuThuoc) {
//            cbBNhaThuoc.addItem(hieuThuoc);
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtEmail = new gui.textfield.TextPay();
        btnXoaTrang = new javax.swing.JButton();
        pnlNavbar = new javax.swing.JPanel();
        pnlContainer = new javax.swing.JPanel();
        pnlTxtCon = new javax.swing.JPanel();
        pnlTxt = new javax.swing.JPanel();
        lblMaKH = new javax.swing.JLabel();
        txtMaNV = new gui.textfield.TextPay();
        lblTenKH = new javax.swing.JLabel();
        txtTenNV = new gui.textfield.TextPay();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new gui.textfield.TextPay();
        lblDiemTL = new javax.swing.JLabel();
        txemail = new gui.textfield.TextPay();
        RdNu = new javax.swing.JRadioButton();
        RdNam = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtNgaySinh = new gui.textfield.TextPay();
        jLabel2 = new javax.swing.JLabel();
        txtNgayVaoLam = new gui.textfield.TextPay();
        jLabel3 = new javax.swing.JLabel();
        txtCCCD = new gui.textfield.TextPay();
        jLabel10 = new javax.swing.JLabel();
        cbBTrangThai = new gui.combobox.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        txtTienLuong = new gui.textfield.TextPay();
        jLabel5 = new javax.swing.JLabel();
        txtTienThuong = new gui.textfield.TextPay();
        jLabel6 = new javax.swing.JLabel();
        txtLuongThucTe = new gui.textfield.TextPay();
        jLabel7 = new javax.swing.JLabel();
        cbBTaiKhoan = new gui.combobox.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        cbBNhaThuoc = new gui.combobox.ComboBoxSuggestion();
        jLabel9 = new javax.swing.JLabel();
        cbBChucVu = new gui.combobox.ComboBoxSuggestion();
        pnlTable = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tableEmployee = new gui.table.TableCustom();

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

        lblMaKH.setText("Mã khách hàng");
        lblMaKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblMaKH);

        txtMaNV.setEnabled(false);
        txtMaNV.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtMaNV);

        lblTenKH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenKH.setText("Tên nhân viên");
        lblTenKH.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblTenKH);

        txtTenNV.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtTenNV);

        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDT.setText("Số điện thoại");
        lblSDT.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtSDT);

        lblDiemTL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiemTL.setText("Email");
        lblDiemTL.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlTxt.add(lblDiemTL);

        txemail.setPreferredSize(new java.awt.Dimension(300, 40));
        txemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txemailActionPerformed(evt);
            }
        });
        pnlTxt.add(txemail);

        buttonGroup1.add(RdNu);
        RdNu.setSelected(true);
        RdNu.setText("Nữ");
        pnlTxt.add(RdNu);

        buttonGroup1.add(RdNam);
        RdNam.setText("Nam");
        RdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdNamActionPerformed(evt);
            }
        });
        pnlTxt.add(RdNam);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ngày sinh");
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlTxt.add(jLabel1);

        txtNgaySinh.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(txtNgaySinh);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ngày vào làm");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(jLabel2);

        txtNgayVaoLam.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(txtNgayVaoLam);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CCCD");
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 40));
        pnlTxt.add(jLabel3);

        txtCCCD.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlTxt.add(txtCCCD);

        jLabel10.setText("Trạng Thái");
        pnlTxt.add(jLabel10);
        pnlTxt.add(cbBTrangThai);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tiền lương");
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(jLabel4);

        txtTienLuong.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(txtTienLuong);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Tiền thưởng");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(jLabel5);

        txtTienThuong.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(txtTienThuong);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Lương thực tế");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTxt.add(jLabel6);

        txtLuongThucTe.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(txtLuongThucTe);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tài khoản");
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 40));
        pnlTxt.add(jLabel7);

        cbBTaiKhoan.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(cbBTaiKhoan);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nhà thuốc");
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTxt.add(jLabel8);

        cbBNhaThuoc.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(cbBNhaThuoc);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Chức vụ");
        jLabel9.setPreferredSize(new java.awt.Dimension(50, 40));
        pnlTxt.add(jLabel9);

        cbBChucVu.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlTxt.add(cbBChucVu);

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
                "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Giới tính", "Ngày sinh", "Ngày vào làm", "CCCD", "Trạng thái", "Lương", "Tiền thưởng", "Lương thực tế", "Nhà thuốc", "Tài khoản", "Chức vụ", "Ảnh"
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
            tableEmployee.getColumnModel().getColumn(8).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(9).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(10).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(11).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(12).setPreferredWidth(100);
            tableEmployee.getColumnModel().getColumn(13).setPreferredWidth(100);
        }

        pnlTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlContainer.add(pnlTable, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void RdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RdNamActionPerformed

    private void txemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txemailActionPerformed

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
    private javax.swing.JRadioButton RdNam;
    private javax.swing.JRadioButton RdNu;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.ButtonGroup buttonGroup1;
    private gui.combobox.ComboBoxSuggestion cbBChucVu;
    private gui.combobox.ComboBoxSuggestion cbBNhaThuoc;
    private gui.combobox.ComboBoxSuggestion cbBTaiKhoan;
    private gui.combobox.ComboBoxSuggestion cbBTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private gui.table.TableCustom tableEmployee;
    private gui.textfield.TextPay txemail;
    private gui.textfield.TextPay txtCCCD;
    private gui.textfield.TextPay txtEmail;
    private gui.textfield.TextPay txtLuongThucTe;
    private gui.textfield.TextPay txtMaNV;
    private gui.textfield.TextPay txtNgaySinh;
    private gui.textfield.TextPay txtNgayVaoLam;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtTenNV;
    private gui.textfield.TextPay txtTienLuong;
    private gui.textfield.TextPay txtTienThuong;
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
    }

//    public void DocDuLieuDatabaseVaoTable() throws Exception {
//        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVienDangLam();
//        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
//        model.setRowCount(0);
//        for (entity.NhanVien nv : list) {
//            String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            String ngaysinh = nv.getNgaySinh().format(formatter);
//            String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//            model.addRow(new Object[]{
//                nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//            });
//        }
//    }

//    public void DocDuLieuDatabaseVaoTableFull() throws Exception {
//        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
//        model.setRowCount(0);
//        for (entity.NhanVien nv : list) {
//            String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            String ngaysinh = nv.getNgaySinh().format(formatter);
//            String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//            model.addRow(new Object[]{
//                nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//            });
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Không khởi tạo lại navbar
        ButtonCustom searchButton = navbar.getBtnSearch();
        TextFieldCustom txtSearch = navbar.getTxtSearch();
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
        String selectedValue = (String) comboBox.getSelectedItem();
        tableModel = (DefaultTableModel) tableEmployee.getModel();
        NavButtonCustom btnImport = navbar.getBtnImport();
        NavButtonCustom btnExport = navbar.getBtnExport();
        Object o = e.getSource();
        if (o.equals(searchButton)) {
//            searchNhanVien();
        } else if (o.equals(btnImport)) {
            importExecl();
        } else if (o.equals(btnExport)) {
            exportExecl();
        }else 
            if(o.equals(btnAdd)){
//                addNhanVien();
            }else if(o.equals(btnEdit)){
//                updateNhanVien();
            }
    }
    private boolean validInput() {
    String ten = txtTenNV.getText();
    String sdt = txtSDT.getText();
    String email = txemail.getText();
    String ngaySinh = txtNgaySinh.getText();
    String ngayVaoLam = txtNgayVaoLam.getText();
    String cccd = txtCCCD.getText();
    String luong = txtTienLuong.getText();
    String tienThuong = txtTienThuong.getText();
    String luongThucTe = txtLuongThucTe.getText();

    // Kiểm tra tên
    if (!ten.matches("^[A-ZÀ-ỹ][a-zà-ỹ]*(?: [A-ZÀ-ỹ][a-zà-ỹ]*)*$")) {
        JOptionPane.showMessageDialog(this, "Tên nhân viên không hợp lệ! Phải có ít nhất hai từ, mỗi từ viết hoa chữ cái đầu và không chứa ký tự đặc biệt.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtTenNV.requestFocus();
        txtTenNV.selectAll();
        return false;
    }

    // Kiểm tra số điện thoại
    if (!sdt.matches("^0[13579]\\d{8}$")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Phải bắt đầu bằng 0 và tiếp theo là 1, 3, 5, 7 hoặc 9, tổng cộng 10 chữ số.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtSDT.requestFocus();
        txtSDT.selectAll();
        return false;
    }

    // Kiểm tra email
    if (!email.matches("^[\\w-!@#$%^&*\\.]+@([a-zA-Z]+\\.)+(com|net)$")) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ! Phải bắt đầu bằng chuỗi không chứa ký tự đặc biệt và kết thúc bằng gmail.com.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txemail.requestFocus();
        txemail.selectAll();
        return false;
    }

    // Kiểm tra ngày sinh (>= 25 tuổi)
    // Cần phải chuyển đổi chuỗi ngày thành kiểu Date để so sánh
    // Giả sử định dạng ngày là dd/MM/yyyy
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfBirth = sdf.parse(ngaySinh);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -25);
        if (dateOfBirth.after(calendar.getTime())) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải >= 25 tuổi!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtNgaySinh.requestFocus();
            txtNgaySinh.selectAll();
            return false;
        }
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtNgaySinh.requestFocus();
        txtNgaySinh.selectAll();
        return false;
    }

    // Kiểm tra ngày vào làm (<= ngày hiện tại)
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateStart = sdf.parse(ngayVaoLam);
        Date currentDate = new Date();
        if (dateStart.after(currentDate)) {
JOptionPane.showMessageDialog(this, "Ngày vào làm phải trước hoặc bằng ngày hiện tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtNgayVaoLam.requestFocus();
            txtNgayVaoLam.selectAll();
            return false;
        }
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Ngày vào làm không hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtNgayVaoLam.requestFocus();
        txtNgayVaoLam.selectAll();
        return false;
    }

    // Kiểm tra CCCD
    if (cccd.isEmpty() || !cccd.matches("^\\d{12}$")) {
        JOptionPane.showMessageDialog(this, "CCCD không hợp lệ! Phải gồm 12 chữ số.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtCCCD.requestFocus();
        txtCCCD.selectAll();
        return false;
    }

    // Kiểm tra lương
    if (luong.isEmpty() || Double.parseDouble(luong) <= 0) {
        JOptionPane.showMessageDialog(this, "Lương phải lớn hơn 0!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtTienLuong.requestFocus();
        txtTienLuong.selectAll();
        return false;
    }

    // Kiểm tra tiền thưởng
    if (tienThuong.isEmpty() || Double.parseDouble(tienThuong) <= 0) {
        JOptionPane.showMessageDialog(this, "Tiền thưởng phải lớn hơn 0!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtTienThuong.requestFocus();
        txtTienThuong.selectAll();
        return false;
    }

    // Kiểm tra lương thực tế
    if (luongThucTe.isEmpty() || Double.parseDouble(luongThucTe) <= 0) {
        JOptionPane.showMessageDialog(this, "Lương thực tế phải lớn hơn 0!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        txtLuongThucTe.requestFocus();
        txtLuongThucTe.selectAll();
        return false;
    }


    return true; // Nếu tất cả các trường đều hợp lệ
}

    @Override
    public void mouseClicked(MouseEvent e) {
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
        int row = tableEmployee.getSelectedRow();
        if (row != -1) {
            txtMaNV.setText(model.getValueAt(row, 0).toString());
            txtTenNV.setText(model.getValueAt(row, 1).toString());
            txtSDT.setText(model.getValueAt(row, 2).toString());
            txemail.setText(model.getValueAt(row, 3).toString());
            txtNgaySinh.setText(model.getValueAt(row, 5).toString());
            txtNgayVaoLam.setText(model.getValueAt(row, 6).toString());
            txtCCCD.setText(model.getValueAt(row, 7).toString());
            txtTienLuong.setText(model.getValueAt(row, 9).toString());
            txtTienThuong.setText(model.getValueAt(row, 10).toString());
            txtLuongThucTe.setText(model.getValueAt(row, 11).toString());
            String gioiTinh = model.getValueAt(row, 4).toString();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                RdNam.setSelected(true);
                RdNu.setSelected(false);
            } else if (gioiTinh.equalsIgnoreCase("Nữ")) {
                RdNu.setSelected(true);
                RdNam.setSelected(false);

            }
            String tThai = model.getValueAt(row, 8).toString();
            String nhathuoc = model.getValueAt(row, 12).toString();
            String taikhoan = model.getValueAt(row, 13).toString();
            String chucvu = model.getValueAt(row, 14).toString();
            cbBTrangThai.setSelectedItem(tThai);
            cbBNhaThuoc.setSelectedItem(nhathuoc);
            cbBTaiKhoan.setSelectedItem(taikhoan);
            cbBChucVu.setSelectedItem(chucvu);
        }
    }
    public void chonDong(){
        tableEmployee.setRowSelectionInterval(0, 0);
        tableEmployee.scrollRectToVisible(tableEmployee.getCellRect(0, 0, true));
        tableEmployee.requestFocusInWindow();
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
        int row = tableEmployee.getSelectedRow();
        if (row != -1) {
            txtMaNV.setText(model.getValueAt(row, 0).toString());
            txtTenNV.setText(model.getValueAt(row, 1).toString());
            txtSDT.setText(model.getValueAt(row, 2).toString());
            txemail.setText(model.getValueAt(row, 3).toString());
            txtNgaySinh.setText(model.getValueAt(row, 5).toString());
            txtNgayVaoLam.setText(model.getValueAt(row, 6).toString());
            txtCCCD.setText(model.getValueAt(row, 7).toString());
            txtTienLuong.setText(model.getValueAt(row, 9).toString());
            txtTienThuong.setText(model.getValueAt(row, 10).toString());
            txtLuongThucTe.setText(model.getValueAt(row, 11).toString());
            String gioiTinh = model.getValueAt(row, 4).toString();
            if (gioiTinh.equalsIgnoreCase("Nam")) {
                RdNam.setSelected(true);
                RdNu.setSelected(false);
            } else if (gioiTinh.equalsIgnoreCase("Nữ")) {
                RdNu.setSelected(true);
                RdNam.setSelected(false);

            }
            String tThai = model.getValueAt(row, 8).toString();
            String nhathuoc = model.getValueAt(row, 12).toString();
            String taikhoan = model.getValueAt(row, 13).toString();
            String chucvu = model.getValueAt(row, 14).toString();
            cbBTrangThai.setSelectedItem(tThai);
            cbBNhaThuoc.setSelectedItem(nhathuoc);
            cbBTaiKhoan.setSelectedItem(taikhoan);
            cbBChucVu.setSelectedItem(chucvu);
        }
    }
//    public void searchNhanVien(){
//        TextFieldCustom txtSearch = navbar.getTxtSearch();
//        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();
//        String selectedValue = (String) comboBox.getSelectedItem();
//        if (selectedValue.equals("Mã nhân viên")) {
//                String timMa = txtSearch.getText();
//                if (timMa.equals("Nhập nội dung tìm kiếm...")) {
//                    try {
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        for (entity.NhanVien nv : list) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                        SwingUtilities.invokeLater(() -> {
//                            tableModel.fireTableDataChanged();
//                            tableEmployee.revalidate(); // Cập nhật JTable
//                            tableEmployee.repaint(); // Vẽ lại JTable
//                            JScrollPane scrollPane = (JScrollPane) tableEmployee.getParent().getParent(); // Lấy JScrollPane
//                            scrollPane.revalidate(); // Cập nhật JScrollPane
//                            scrollPane.repaint(); // Vẽ lại JScrollPane
//                        });
//                        
//                        chonDong();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                } else {
//                    try {
//                        // Tìm nhân viên theo mã
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> tim = nv_dao.getNhanVienTheoMaNhanVien(timMa);
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        // Chỉ hiển thị nếu có nhân viên tìm thấy
//                        if (!tim.isEmpty()) {
//                            for (entity.NhanVien nv : tim) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                              
//                        } else {
//                            nv_dao = new NhanVien_DAO();
//                            ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                            tableModel = (DefaultTableModel) tableEmployee.getModel();
//                            tableModel.setRowCount(0);
//                            for (entity.NhanVien nv : list) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                            JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên " + timMa, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                            
//                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace(); // In ra lỗi nếu có
//                    }
//                    SwingUtilities.invokeLater(() -> {
//                        tableModel.fireTableDataChanged();
//                        tableEmployee.revalidate(); // Cập nhật JTable
//                        tableEmployee.repaint(); // Vẽ lại JTable
//                        JScrollPane scrollPane = (JScrollPane) tableEmployee.getParent().getParent(); // Lấy JScrollPane
//                        scrollPane.revalidate(); // Cập nhật JScrollPane
//                        scrollPane.repaint(); // Vẽ lại JScrollPane
//                    });
//                    chonDong();
//                }
//            } else if (selectedValue.equals("Tên nhân viên")) {
//                String timTen = txtSearch.getText();
//                if (timTen.equals("Nhập nội dung tìm kiếm...")) {
//                    try {
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        for (entity.NhanVien nv : list) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }else {
//                    try {
//                        // Tìm nhân viên theo tên
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> tim = nv_dao.getNhanVienTheoTenNhanVien(timTen);
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        // Chỉ hiển thị nếu có nhân viên tìm thấy
//                        if (!tim.isEmpty()) {
//                            for (entity.NhanVien nv : tim) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                        } else {
//                            nv_dao = new NhanVien_DAO();
//                            ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                            tableModel = (DefaultTableModel) tableEmployee.getModel();
//                            tableModel.setRowCount(0);
//                            for (entity.NhanVien nv : list) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                            JOptionPane.showMessageDialog(this, "Không tìm thấy tên nhân viên " + timTen, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//                SwingUtilities.invokeLater(() -> {
//                    tableModel.fireTableDataChanged();
//                    tableEmployee.revalidate(); // Cập nhật JTable
//                    tableEmployee.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableEmployee.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//                chonDong();
//            } else if (selectedValue.equals("Số điện thoại")) {
//                String timTen = txtSearch.getText();
//                if (timTen.equals("Nhập nội dung tìm kiếm...")) {
//                    try {
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        for (entity.NhanVien nv : list) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                } else {
//                    try {
//                        // Tìm nhân viên theo tên
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> tim = nv_dao.getNhanVienTheoSdtNhanVien(timTen);
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        // Chỉ hiển thị nếu có nhân viên tìm thấy
//                        if (!tim.isEmpty()) {
//                            for (entity.NhanVien nv : tim) {
//                        String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh = nv.getNgaySinh().format(formatter);
//                        String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                        } else {
//                            nv_dao = new NhanVien_DAO();
//                            ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                            tableModel = (DefaultTableModel) tableEmployee.getModel();
//                            tableModel.setRowCount(0);
//                            for (entity.NhanVien nv : list) {
//                                String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";         
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                                String ngaysinh = nv.getNgaySinh().format(formatter);
//                                String ngayvaolam= nv.getNgayVaoLam().format(formatter);
//                                tableModel.addRow(new Object[]{
//                             nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), nv.getEmail(), gioiTinh, ngaysinh, ngayvaolam, nv.getCCCD(), nv.getTrangThai(), nv.getLuong(), nv.getTienThuong(), nv.getLuongThucTe(), nv.getNhaThuoc().getMaNhaThuoc(), nv.getTaiKhoan().getTaiKhoan(), nv.getChucVu().getMaChucVu()
//                                 });
//                           }
//                            JOptionPane.showMessageDialog(this, "Không tìm thấy tên nhân viên " + timTen, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//            SwingUtilities.invokeLater(() -> {
//                tableModel.fireTableDataChanged();
//                tableEmployee.revalidate(); // Cập nhật JTable
//                tableEmployee.repaint(); // Vẽ lại JTable
//                JScrollPane scrollPane = (JScrollPane) tableEmployee.getParent().getParent(); // Lấy JScrollPane
//                scrollPane.revalidate(); // Cập nhật JScrollPane
//                scrollPane.repaint(); // Vẽ lại JScrollPane
//            });
//            chonDong();
//    }
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
    
//    public void addNhanVien(){
//        if(validInput()){
//                DateTimeFormatter date= DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                String ten = txtTenNV.getText();
//                String sdt = txtSDT.getText();
//                String email = txemail.getText();
//                boolean gioiTinh = RdNam.isSelected();
//                String ngaysinh= txtNgaySinh.getText();
//                String[] ngaySinh1 = ngaysinh.split("-");
//                String ngaySinh= ngaySinh1[2] + "-" + ngaySinh1[1] +"-"+ngaySinh1[0];
//                LocalDate ngay= LocalDate.parse(ngaySinh,date);
//                String ngaylam= txtNgayVaoLam.getText();
//                String[] ngaylam1 = ngaylam.split("-");
//                String ngayLam= ngaylam1[2] + "-" + ngaylam1[1] +"-"+ngaylam1[0];
//                LocalDate ngayVaoLam= LocalDate.parse(ngayLam,date);
//                String cccd = txtCCCD.getText();
//                double luong = Double.parseDouble(txtTienLuong.getText());
//                double tienThuong = Double.parseDouble(txtTienThuong.getText());
//                double luongThucTe = Double.parseDouble(txtLuongThucTe.getText());
//                String trangThai = cbBTrangThai.getSelectedItem().toString();
//                String tennhathuoc= cbBNhaThuoc.getSelectedItem().toString();
//                String nhathuoc = null;
//                try {
//                    nhathuoc = nt_dao.getNTTheoTenNhaThuoc(tennhathuoc);
//                } catch (Exception ex) {
//                    Logger.getLogger(FormEmployee.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                String tenchucvu= cbBChucVu.getSelectedItem().toString();
//                String chucVu = null;
//                try {
//                    chucVu = cv_dao.getMaChucVuTheoTen(tenchucvu);
//                } catch (Exception ex) {
//                    Logger.getLogger(FormEmployee.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                String last3= sdt.substring(sdt.length()-3);
//                String dd = ngaylam1[0]; // Nam
//                String mm = ngaylam1[1]; // Tháng
//                String yy = ngaylam1[2].substring(2); // Ngay
//                String newMa;
//                if(cbBChucVu.getSelectedItem().toString().equals("Quản lý")){
//                    newMa= String.format("QL%s%s%s%s", dd, mm, yy, last3);
//                }else{
//                    newMa= String.format("NV%s%s%s%s", dd, mm, yy, last3);
//                }
//                String taiKhoan= "TK" + newMa;
//                System.out.println("MaNhanVien: " + newMa);
//                System.out.println("Tên: " + ten);
//                System.out.println("sdt: " + sdt);
//                System.out.println("email: " + email);
//                System.out.println("MaNhanVien: " + gioiTinh);
//                System.out.println("NhaThuoc: " + ngay);
//                System.out.println("ChucVu: " + ngayVaoLam);
//                System.out.println("TaiKhoan: " + cccd);
//                System.out.println("MaNhanVien: " + trangThai);
//                System.out.println("NhaThuoc: " + tienThuong);
//                System.out.println("ChucVu: " + luongThucTe);
//                System.out.println("TaiKhoan: " + luong);
//                System.out.println("NhaThuoc: " + nhathuoc);
//                System.out.println("ChucVu: " + chucVu);
//                System.out.println("TaiKhoan: " + taiKhoan);
//                try {
//                    entity.NhanVien nv= new entity.NhanVien(newMa, ten , sdt, email, gioiTinh, ngay, ngayVaoLam, cccd, trangThai, tienThuong, luongThucTe, luong, new NhaThuoc(nhathuoc), new TaiKhoan(taiKhoan), new ChucVu(chucVu,tenchucvu));
//                   if( nv_dao.create(nv)){
//                                            try {
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        for (entity.NhanVien nv1 : list) {
//                        String gioiTinh1 = nv1.isGioiTinh() ? "Nam" : "Nữ";         
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                        String ngaysinh1 = nv1.getNgaySinh().format(formatter);
//                        String ngayvaolam1= nv1.getNgayVaoLam().format(formatter);
//                        tableModel.addRow(new Object[]{
//                             nv1.getMaNhanVien(), nv1.getTenNhanVien(), nv1.getSoDienThoai(), nv1.getEmail(), gioiTinh1, ngaysinh1, ngayvaolam1, nv1.getCCCD(), nv1.getTrangThai(), nv1.getLuong(), nv1.getTienThuong(), nv1.getLuongThucTe(), nv1.getNhaThuoc().getMaNhaThuoc(), nv1.getTaiKhoan().getTaiKhoan(), nv1.getChucVu().getMaChucVu()
//                                 });
//                           }
//                        SwingUtilities.invokeLater(() -> {
//                            tableModel.fireTableDataChanged();
//                            tableEmployee.revalidate(); // Cập nhật JTable
//                            tableEmployee.repaint(); // Vẽ lại JTable
//                            JScrollPane scrollPane = (JScrollPane) tableEmployee.getParent().getParent(); // Lấy JScrollPane
//                            scrollPane.revalidate(); // Cập nhật JScrollPane
//                            scrollPane.repaint(); // Vẽ lại JScrollPane
//                        });
//                        chonDong();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//
//                        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
//                   }
//                    
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                
//                
//            }
//    }
//    
//    public void updateNhanVien(){
//        if(validInput()){
//                DateTimeFormatter date= DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                String ma= txtMaNV.getText();
//                String ten = txtTenNV.getText();
//                String sdt = txtSDT.getText();
//                String email = txemail.getText();
//                boolean gioiTinh = RdNam.isSelected();
//                String ngaysinh= txtNgaySinh.getText();
//                String[] ngaySinh1 = ngaysinh.split("-");
//                String ngaySinh= ngaySinh1[2] + "-" + ngaySinh1[1] +"-"+ngaySinh1[0];
//                LocalDate ngay= LocalDate.parse(ngaySinh,date);
//                String ngaylam= txtNgayVaoLam.getText();
//                String[] ngaylam1 = ngaylam.split("-");
//                String ngayLam= ngaylam1[2] + "-" + ngaylam1[1] +"-"+ngaylam1[0];
//                LocalDate ngayVaoLam= LocalDate.parse(ngayLam,date);
//                String cccd = txtCCCD.getText();
//                double luong = Double.parseDouble(txtTienLuong.getText());
//                double tienThuong = Double.parseDouble(txtTienThuong.getText());
//                double luongThucTe = Double.parseDouble(txtLuongThucTe.getText());
//                String trangThai = cbBTrangThai.getSelectedItem().toString();
//                String tennhathuoc= cbBNhaThuoc.getSelectedItem().toString();
//                String nhathuoc = null;
//                try {
//                    nhathuoc = nt_dao.getNTTheoTenNhaThuoc(tennhathuoc);
//                } catch (Exception ex) {
//                    Logger.getLogger(FormEmployee.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                String tenchucvu= cbBChucVu.getSelectedItem().toString();
//                String chucVu = null;
//                try {
//                    chucVu = cv_dao.getMaChucVuTheoTen(tenchucvu);
//                } catch (Exception ex) {
//                    Logger.getLogger(FormEmployee.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                String taiKhoan= cbBTaiKhoan.getSelectedItem().toString();
//                System.out.println("MaNhanVien: " + ma);
//                System.out.println("Tên: " + ten);
//                System.out.println("sdt: " + sdt);
//                System.out.println("email: " + email);
//                System.out.println("MaNhanVien: " + gioiTinh);
//                System.out.println("NhaThuoc: " + ngay);
//                System.out.println("ChucVu: " + ngayVaoLam);
//                System.out.println("TaiKhoan: " + cccd);
//                System.out.println("MaNhanVien: " + trangThai);
//                System.out.println("NhaThuoc: " + tienThuong);
//                System.out.println("ChucVu: " + luongThucTe);
//                System.out.println("TaiKhoan: " + luong);
//                System.out.println("NhaThuoc: " + nhathuoc);
//                System.out.println("ChucVu: " + chucVu);
//                System.out.println("TaiKhoan: " + taiKhoan);
//
//                try {
//                    entity.NhanVien nv= new entity.NhanVien(ma, ten , sdt, email, gioiTinh, ngay, ngayVaoLam, cccd, trangThai, tienThuong, luongThucTe, luong, new NhaThuoc(nhathuoc), new TaiKhoan(taiKhoan), new ChucVu(chucVu));
//                   if( nv_dao.update(nv)){
//                       try {
//                        nv_dao = new NhanVien_DAO();
//                        ArrayList<entity.NhanVien> list = nv_dao.getAllTBNhanVien();
//                        tableModel = (DefaultTableModel) tableEmployee.getModel();
//                        tableModel.setRowCount(0);
//                        for (entity.NhanVien nv1 : list) {
//                            String gioiTinh1 = nv1.isGioiTinh() ? "Nam" : "Nữ";         
//                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                            String ngaysinh1 = nv1.getNgaySinh().format(formatter);
//                            String ngayvaolam= nv1.getNgayVaoLam().format(formatter);
//                            tableModel.addRow(new Object[]{
//                                nv1.getMaNhanVien(), nv1.getTenNhanVien(), nv1.getSoDienThoai(), nv1.getEmail(), gioiTinh1, ngaysinh1, ngayvaolam, nv1.getCCCD(), nv1.getTrangThai(), nv1.getLuong(), nv1.getTienThuong(), nv1.getLuongThucTe(), nv1.getNhaThuoc().getMaNhaThuoc(), nv1.getTaiKhoan().getTaiKhoan(), nv1.getChucVu().getMaChucVu()
//                            });
//        }
//                        SwingUtilities.invokeLater(() -> {
//                            tableModel.fireTableDataChanged();
//                            tableEmployee.revalidate(); // Cập nhật JTable
//                            tableEmployee.repaint(); // Vẽ lại JTable
//                            JScrollPane scrollPane = (JScrollPane) tableEmployee.getParent().getParent(); // Lấy JScrollPane
//                            scrollPane.revalidate(); // Cập nhật JScrollPane
//                            scrollPane.repaint(); // Vẽ lại JScrollPane
//                        });
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                        chonDong();
//                        JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công");
//                        
//                   }
//                    
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                
//                
//            }
//    }
    
    public void clearText(){
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
        txtLuongThucTe.setText("");
        txtNgaySinh.setText("");
        txtNgayVaoLam.setText("");
        txtSDT.setText("");
        txtTienLuong.setText("");
        txtTienThuong.setText("");
        RdNu.isSelected();
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
