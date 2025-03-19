package gui.application.form.other;

//import connectDB.ConnectDB;
//import dao.ChiTietHoaDon_DAO;
//import dao.HoaDon_DAO;
//import dao.KhachHang_DAO;
//import dao.NhanVien_DAO;
//import entity.ChiTietHoaDon;
//import entity.Thuoc;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.navbar.Navbar;
import gui.textfield.TextFieldCustom;
import java.awt.Color;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class FormBill extends JPanel implements ActionListener, MouseListener {

    private DefaultTableModel tableModel;
//    private  HoaDon_DAO hd_dao;
//    private  ChiTietHoaDon_DAO cthd_dao;
//    private  NhanVien_DAO nv_dao;
//    private  KhachHang_DAO kh_dao;
    private String ma = null;
//    private entity.HoaDon hdMau=null;
    private ButtonCustom searchButton;
    private NavButtonCustom btnImport;
    private NavButtonCustom btnExport;
    private NavButtonCustom btnAdd;

    public FormBill() {
        try {
//            ConnectDB.getInstance().connect();
//            hd_dao= new HoaDon_DAO();
//            cthd_dao= new ChiTietHoaDon_DAO();
//            nv_dao= new NhanVien_DAO();
//            kh_dao= new KhachHang_DAO();

            initComponents();
            init();
//            DocDuLieuDatabaseVaoTable();
            tableOrder.addMouseListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchButton = navbar.getBtnSearch();
        btnImport = navbar.getBtnImport();
        btnExport = navbar.getBtnExport();
        btnAdd = navbar.getBtnAdd();
        btnExport.addActionListener(this);
        searchButton.addActionListener(this);
        btnAdd.addActionListener(this);
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Sản phẩm", "Đơn Vị Tính", "Giá bán", "Số lượng", "Thành tiền"
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
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Nhân viên", "Ngày thanh toán", "Tổng tiền", "Tiền giảm", "Tổng tiền hóa đơn", "Ghi chú"
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
        //navbar.removeEdit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonCustom searchButton = navbar.getBtnSearch();
        TextFieldCustom txtSearch = navbar.getTxtSearch();
        ComboBoxSuggestion<String> comboBox = navbar.getComboBox();

        String selectedValue = (String) comboBox.getSelectedItem();
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
        if (o.equals(searchButton)) {
            if (selectedValue.equals("Mã hóa đơn")) {
                String timMa = txtSearch.getText();

                if (timMa.equals("Nhập nội dung tìm kiếm...")) {
                    try {
//                        DocDuLieuDatabaseVaoTable();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        // Tìm khách hàng theo mã
//                        TimTheoMa(timMa);
                    } catch (Exception ex) {
                        ex.printStackTrace(); // In ra lỗi nếu có

                    }

                }
            } else {
                String timTen = txtSearch.getText();
                if (timTen.equals("Nhập nội dung tìm kiếm...")) {
                    try {
//                        DocDuLieuDatabaseVaoTable();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
//                        TimTheoTen(timTen);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (o.equals(btnAdd)) {
//            openOrderInfoFormForAdd();
        }
    }
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        Object o=e.getSource();
//        if(o.equals(tableOrder)){
//            int row = tableOrder.getSelectedRow();
//            if (row != -1) { // Kiểm tra xem có hàng nào được chọn không
//                // Lấy dữ liệu từ hàng đã chọn
//                
//                String maHD = tableOrder.getValueAt(row, 0).toString();
//                String tenKH = tableOrder.getValueAt(row, 1).toString();
//                String tenNV = tableOrder.getValueAt(row, 2).toString();
//                LocalDateTime ngayThanhToan = null;
//
//                Object ngayThanhToanObj = tableOrder.getValueAt(row, 3);
//                if (ngayThanhToanObj instanceof java.sql.Timestamp) {
//                    ngayThanhToan = ((java.sql.Timestamp) ngayThanhToanObj).toLocalDateTime();
//                } else if (ngayThanhToanObj instanceof LocalDateTime) {
//                    ngayThanhToan = (LocalDateTime) ngayThanhToanObj;
//                }
//                double tongTien = Double.parseDouble(tableOrder.getValueAt(row, 4).toString());
//                double tienGiam = Double.parseDouble(tableOrder.getValueAt(row, 5).toString());
//                double tongTienHoaDon = Double.parseDouble(tableOrder.getValueAt(row,6).toString());
//                String ghiChu = tableOrder.getValueAt(row, 7) != null ? 
//                 tableOrder.getValueAt(row, 7).toString() : null;
//                entity.KhachHang kh=null;
//                try {
//                    kh = kh_dao.getKHTheoTen(tenKH);
//                } catch (Exception ex) {
//                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                entity.NhanVien nv=null;
//                try {
//                    nv = nv_dao.getNhanVienTheoTen(tenNV);
//                } catch (Exception ex) {
//                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                hdMau= new entity.HoaDon(maHD, ngayThanhToan, tongTien, tienGiam, tongTienHoaDon, ghiChu, kh, nv);
//               
//                try {
//                    DocDuLieuDatabaseVaoTableCT(maHD);
//                } catch (Exception ex) {
//                    Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

//    @Override
//    public void mouseExited(MouseEvent e) {}
//    public void DocDuLieuDatabaseVaoTable() throws Exception {
//       
//        ArrayList<entity.HoaDon> list = hd_dao.getAllHD();
//        DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
//        model.setRowCount(0);
//        for (entity.HoaDon hd : list) {
//            
//            model.addRow(new Object[]{
//               hd.getMaHoaDon(),hd.getKhachHang().getTenKH(),hd.getNhanVien().getTenNhanVien(),hd.getNgayThanhToan(),hd.getTongTien(),hd.getTienGiam(),hd.getTongTienHoaDon(),hd.getGhiChu()
//            });
//        }
//    }
//    public void TimTheoMa(String maHD) throws Exception {
//        ArrayList<entity.HoaDon> list = hd_dao.getHDTheoMaHoaDon(maHD);
//        DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
//        if (model.getRowCount() > 0) {
//            model.setRowCount(0);  // Xóa tất cả các dòng trước khi thêm mới
//        }
//        for (entity.HoaDon hd : list) {
//            
//            model.addRow(new Object[]{
//               hd.getMaHoaDon(),hd.getKhachHang().getTenKH(),hd.getNhanVien().getTenNhanVien(),hd.getNgayThanhToan(),hd.getTongTien(),hd.getTienGiam(),hd.getTongTienHoaDon(),hd.getGhiChu()
//            });
//        }
//    }
//    public void TimTheoTen(String maHD) throws Exception {
//        ArrayList<entity.HoaDon> list = hd_dao.getHDTheoTenKH(maHD);
//        DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
//        if (model.getRowCount() > 0) {
//            model.setRowCount(0);  // Xóa tất cả các dòng trước khi thêm mới
//        }
//        for (entity.HoaDon hd : list) {
//            
//            model.addRow(new Object[]{
//               hd.getMaHoaDon(),hd.getKhachHang().getTenKH(),hd.getNhanVien().getTenNhanVien(),hd.getNgayThanhToan(),hd.getTongTien(),hd.getTienGiam(),hd.getTongTienHoaDon(),hd.getGhiChu()
//            });
//        }
//    }
//    public void DocDuLieuDatabaseVaoTableCT(String ma) throws Exception {
//       
//        HashMap<entity.ChiTietHoaDon, Double> giaBanMap = new HashMap<>();
//
//        HashMap<ChiTietHoaDon, Double> list = cthd_dao.getChiTietHoaDonThuocByMaHoaDon(ma);
//        DefaultTableModel model = (DefaultTableModel) tableOrderDetail.getModel();
//        model.setRowCount(0); // Xóa tất cả các hàng trong bảng
//
//        for (Map.Entry<ChiTietHoaDon, Double> entry : list.entrySet()) {
//            entity.ChiTietHoaDon ct = entry.getKey();
//            double giaBan = entry.getValue();
//            // Thêm dòng vào bảng
//            model.addRow(new Object[]{
//                ct.getThuoc().getMaThuoc() ,ct.getThuoc().getTenThuoc(),ct.getDVT().getDVT(),giaBan,ct.getSoLuong(),giaBan*ct.getSoLuong()
//            });
//        }
//        ArrayList<entity.ChiTietHoaDon> list2 = cthd_dao.getChiTietThietBiYTeByMaHoaDon(ma);
//        DefaultTableModel model2 = (DefaultTableModel) tableOrderDetail.getModel();
////        model.setRowCount(0);
//        for (entity.ChiTietHoaDon hd2 : list2) {
//            model.addRow(new Object[]{
//               hd2.getThietBiYTe().getMaThietBi(),hd2.getThietBiYTe().getTenThietBi(),hd2.getDVT().getDVT(),hd2.getThietBiYTe().getGiaBan(),hd2.getSoLuong(),hd2.getThietBiYTe().getGiaBan()*hd2.getSoLuong()
//            });
//        }
//    }
//    private void openOrderInfoFormForAdd() {
//        JFrame frame = new JFrame();
//        frame.setSize(960, 700);
// 
//        if (hdMau!= null) {
//            if(hdMau.getMaHoaDon().matches(".*D$")||hdMau.getMaHoaDon().matches(".*T$")||hdMau.getMaHoaDon().matches(".*C$")){
//                JOptionPane.showMessageDialog(null, "Hóa đơn trả và đổi không thể tiếp tục đổi và trả.", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
//               
//            }else{
//                if(KiemTra7D(hdMau.getNgayThanhToan())){
//                    HoaDonDoiTraForm tn=null;
//                    try {
//                        tn = new HoaDonDoiTraForm(frame,hdMau );
//                    } catch (Exception ex) {
//                        Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    frame.add(tn);
//                    frame.setLocationRelativeTo(null);
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    frame.setUndecorated(true);
//                    frame.pack();
//                    frame.setVisible(true);
//                }else{
//                    JOptionPane.showMessageDialog(null, "Chỉ có thế đổi trả sản phẩm nếu thời gian < 7 ngày.", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);   
//                }
//            }
//        }else{
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần chỉnh sửa", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
//        }
//    }
    public boolean KiemTra7D(LocalDateTime d) {
        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Tính số ngày giữa d và currentTime
        long daysBetween = ChronoUnit.DAYS.between(d, currentTime);

        // Kiểm tra nếu số ngày <= 7
        return daysBetween <= 7;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
