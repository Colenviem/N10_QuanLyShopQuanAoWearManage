package gui.application.form.other;

//import dao.ChiTietHoaDon_DAO;
//import dao.DVT_DAO;
//import dao.HoaDon_DAO;
//import dao.KhachHang_DAO;
//import dao.NhanVien_DAO;
//import dao.PhanCongCa_DAO;
//import dao.ThietBiYTe_DAO;
//import dao.Thuoc_DAO;
//import entity.ChiTietHoaDon;
//import entity.DonViTinh;
//import entity.ThietBiYTe;
//import entity.Thuoc;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.table.TableProductCellRender;
import gui.table.TableRemoveCellRender;
import gui.textfield.TextPay;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FormSales extends JPanel implements ActionListener, MouseListener {

    private ButtonCustom btnAdd;
    private ButtonCustom btnSearch;
    private ButtonCustom btnSearch1;
    private NavButtonCustom btnAddItem;
//    private ButtonCustom btnSaveTemp;
    private TextPay txtTongTien;
    private TextPay txtThanhTien;
//    private Thuoc_DAO thuoc_DAO;
//    private ThietBiYTe_DAO thietBiYTe_DAO;
    private DefaultTableModel tableModel;
    private DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
    private ComboBoxSuggestion comboxDVT;
    private ButtonCustom btnThanhToan;

    public FormSales() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
        setPreferredSize(new Dimension(1500, 750));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        btnSearch = new ButtonCustom("", "images/png/search.png");
        btnSearch.setPreferredSize(new Dimension(40, 40));
        txtQuay.setEditable(false);
        txtQuay.setFocusable(false);
        pnlHoaDon.add(btnSearch);
        pnlHoaDon.add(lblQuay);
        pnlHoaDon.add(txtQuay);
        pnlHoaDon.add(lblMaHD);
        pnlHoaDon.add(txtMaHD);
        pnlHoaDon.add(new JLabel("Đơn vị tính"));
        comboxDVT = new ComboBoxSuggestion();
        comboxDVT.setPreferredSize(new Dimension(100, 40));
        pnlHoaDon.add(comboxDVT);
        btnSearch1 = new ButtonCustom("", "imgs\\img\\search.png");
        btnSearch1.setPreferredSize(new Dimension(40, 40));
        pnlKH.add(btnSearch1);
        pnlKH.add(lblTenKH);
        pnlKH.add(txtTenKH);
        pnlKH.add(lblDTL);
        pnlKH.add(txtDTL);
        pnlKHMoi.add(lblTenKHMoi);
        pnlKHMoi.add(txtTenKHMoi);
        pnlKHMoi.add(lblDTLMoi);
        pnlKHMoi.add(txtDTLMoi);
        tableOrder.ScrollPaneTable(jScrollPane);
        cmdTT.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(206, 212, 216)));

        btnAddItem = new NavButtonCustom("Thêm sản phẩm", "images/png/add.png");

        pnlHD.setBorder(new EmptyBorder(0, 0, 0, 5));
        jPanel14.setBorder(new EmptyBorder(40, 0, 100, 0));

        txtTongTien = pay1.getTxtTongTien();
        txtTongTien.setText(txtTTHD.getText());
        txtDTL.setEditable(false);
        txtDTL.setFocusable(false);
        txtDTLMoi.setEditable(false);
        txtDTLMoi.setFocusable(false);
        txtMaNV.setEditable(false);
        txtMaNV.setFocusable(false);
        txtTenNV.setEditable(false);
        txtTenNV.setFocusable(false);
        txtMaHD.setEditable(false);
        txtMaHD.setFocusable(false);
        txtDTLMoi.setText("0");
        txtThanhTien = pay1.getTxtThanhTien();
        txtThanhTien.setText(txtTTHD.getText());
//        setDataNV();
//        String maHD = taoMaHoaDon();
//        txtMaHD.setText(maHD);
//        tabHoaDon.setTitleAt(0, maHD);

        tableOrder.getColumnModel().getColumn(0).setCellRenderer(new TableProductCellRender(tableOrder));
//        tableOrder.getColumnModel().getColumn(5).setCellEditor(new TableQtyCellRender(new EventCellInputChange() {
//            @Override
//            public void inputChanged() {
//                sumAmount();
//            }
//        }));
        tableOrder.getColumnModel().getColumn(7).setCellRenderer(new TableRemoveCellRender(tableOrder));
        tableModel = (DefaultTableModel) tableOrder.getModel();
        txtTenKH.setFocusable(false);
        txtTenKH.setEnabled(false);
        btnThanhToan = pay1.getBtnPay();
        txtSDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtSDT.getText().matches("0(3|7|8|9)\\d{8}")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    txtSDT.requestFocus();
                    return;
                }
            }
        });
//Đki sự kiện
        btnSearch.addActionListener(this);
        btnSearch1.addActionListener(this);
//        btnSaveTemp.addActionListener(this);
        tableOrder.addMouseListener(this);
        btnThanhToan.addActionListener(this);
        txtSDTMoi.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!txtSDTMoi.getText().matches("0(1|3|5|7|9)\\d{8}")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    txtSDTMoi.requestFocus();
                    return;
                }
            }
        });
        txtTenKHMoi.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!txtTenKHMoi.getText().matches("^[A-ZÀ-ỹ][a-zà-ỹ]*(?: [A-ZÀ-ỹ][a-zà-ỹ]*)*$")) {
                    JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ! Phải có ít nhất hai từ, mỗi từ viết hoa chữ cái đầu và không chứa ký tự đặc biệt.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    txtTenKHMoi.requestFocus();
                    return;

                }
            }
        });
        pay1.getTxtKhachDua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tinhTienThua();
            }
        });
//        comboxDVT.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    // Lấy chỉ số hàng hiện tại được chọn
//                    int row = tableOrder.getSelectedRow();
//
//                    // Kiểm tra xem hàng có hợp lệ không
//                    if (row != -1) { // Đảm bảo rằng có một hàng được chọn
//                        Thuoc_DAO tdao = new Thuoc_DAO();
//                        ArrayList<Thuoc> dsThuoc = tdao.getThuocTheoTenThuoc(tableOrder.getValueAt(row, 1).toString());;
//                        // Lấy đơn vị tính được chọn từ ComboBox
//                        Object selectedItem = comboxDVT.getSelectedItem();
//                        if (!dsThuoc.isEmpty()) {
//                            if (selectedItem != null) { // Kiểm tra null trước khi sử dụng
//                                String selectedUnit = selectedItem.toString();
//                                Thuoc thuoc = dsThuoc.getFirst();
//                                // Tính giá bán tương ứng với đơn vị tính đã chọn
//                                double giaBan = thuoc.getDsDVT().stream()
//                                        .filter(dvt -> dvt.getDVT().getDVT().equals(selectedUnit)) // So sánh với đơn vị tính được chọn
//                                        .mapToDouble(dvt -> dvt.getGiaBan()) // Lấy giá bán tương ứng
//                                        .findFirst() // Lấy phần tử đầu tiên (nếu có)
//                                        .orElse(0.0); // Nếu không tìm thấy, trả về giá trị mặc định là 0.0
//
//                                // Cập nhật giá bán vào bảng
//                                tableOrder.setValueAt(df.format(giaBan), row, 3);
//                                tableOrder.setValueAt(df.format(giaBan), row, 4);
//
//                                // Lấy số lượng từ ô cột 5
//                                String quantityStr = tableOrder.getValueAt(row, 5).toString();
//                                int quantity = Integer.parseInt(quantityStr); // Chuyển đổi sang số nguyên
//
//                                // Tính tổng giá và cập nhật vào ô cột 6
//                                double totalPrice = giaBan * quantity;
//                                tableOrder.setValueAt(df.format(totalPrice), row, 6);
//                                sumAmount();
//                            } else {
//                                System.out.println("Vui lòng chọn đơn vị tính.");
//                            }
//                        } else {
//                            System.out.println("Vui lòng chọn một hàng hợp lệ.");
//                        }
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, "Lỗi truy xuất dữ liệu: ", ex);
//                } catch (NumberFormatException ex) {
//                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, "Lỗi định dạng số: ", ex);
//                } catch (Exception ex) {
//                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, "Lỗi không xác định: ", ex);
//                }
//            }
//        });

        pay1.getBtnMoney1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay1.getTxtKhachDua().setText("50,000 VNĐ");

                // Lấy và xử lý giá trị tiền khách đưa
                String tienKhachDua = pay1.getTxtKhachDua().getText().replace(" VNĐ", "").replace(",", "");

                // Lấy và xử lý giá trị thanh tiền
                String thanhTien = pay1.getTxtThanhTien().getText().replace(" VNĐ", "").replace(",", "");

                try {
                    // Chuyển đổi chuỗi sang số nguyên
                    int khachDua = Integer.parseInt(tienKhachDua);
                    int tongTien = Integer.parseInt(thanhTien);

                    // Tính tiền thừa và định dạng kết quả
                    int tienThua = khachDua - tongTien;
                    pay1.getTxtTienThua().setText(df.format(tienThua));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(); // In ra lỗi để kiểm tra
                    pay1.getTxtTienThua().setText("Giá trị không hợp lệ");
                }
            }
        });

        pay1.getBtnMoney2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay1.getTxtKhachDua().setText("100,000 VNĐ");

                // Lấy và xử lý giá trị tiền khách đưa
                String tienKhachDua = pay1.getTxtKhachDua().getText().replace(" VNĐ", "").replace(",", "");

                // Lấy và xử lý giá trị thanh tiền
                String thanhTien = pay1.getTxtThanhTien().getText().replace(" VNĐ", "").replace(",", "");

                try {
                    // Chuyển đổi chuỗi sang số nguyên
                    int khachDua = Integer.parseInt(tienKhachDua);
                    int tongTien = Integer.parseInt(thanhTien);

                    // Tính tiền thừa và định dạng kết quả
                    int tienThua = khachDua - tongTien;
                    pay1.getTxtTienThua().setText(df.format(tienThua));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(); // In ra lỗi để kiểm tra
                    pay1.getTxtTienThua().setText("Giá trị không hợp lệ");
                }
            }
        });

        pay1.getBtnMoney3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay1.getTxtKhachDua().setText("200,000 VNĐ");

                // Lấy và xử lý giá trị tiền khách đưa
                String tienKhachDua = pay1.getTxtKhachDua().getText().replace(" VNĐ", "").replace(",", "");

                // Lấy và xử lý giá trị thanh tiền
                String thanhTien = pay1.getTxtThanhTien().getText().replace(" VNĐ", "").replace(",", "");

                try {
                    // Chuyển đổi chuỗi sang số nguyên
                    int khachDua = Integer.parseInt(tienKhachDua);
                    int tongTien = Integer.parseInt(thanhTien);

                    // Tính tiền thừa và định dạng kết quả
                    int tienThua = khachDua - tongTien;
                    pay1.getTxtTienThua().setText(df.format(tienThua));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(); // In ra lỗi để kiểm tra
                    pay1.getTxtTienThua().setText("Giá trị không hợp lệ");
                }
            }
        });

        pay1.getBtnMoney4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay1.getTxtKhachDua().setText("500,000 VNĐ");

                // Lấy và xử lý giá trị tiền khách đưa
                String tienKhachDua = pay1.getTxtKhachDua().getText().replace(" VNĐ", "").replace(",", "");

                // Lấy và xử lý giá trị thanh tiền
                String thanhTien = pay1.getTxtThanhTien().getText().replace(" VNĐ", "").replace(",", "");

                try {
                    // Chuyển đổi chuỗi sang số nguyên
                    int khachDua = Integer.parseInt(tienKhachDua);
                    int tongTien = Integer.parseInt(thanhTien);

                    // Tính tiền thừa và định dạng kết quả
                    int tienThua = khachDua - tongTien;
                    pay1.getTxtTienThua().setText(df.format(tienThua));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(); // In ra lỗi để kiểm tra
                    pay1.getTxtTienThua().setText("Giá trị không hợp lệ");
                }
            }
        });

    }

    private void sumAmount() {
        int total = 0;
        for (int i = 0; i < tableOrder.getRowCount(); i++) {
            String value = tableOrder.getValueAt(i, 6).toString();

            value = value.replace(" VNĐ", "").replace(",", "").trim();
            try {
                total += Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không thể chuyển đổi
                System.err.println("Error parsing value: " + value);
            }
        }
        // Định dạng tổng và gán vào ô text
        txtTTHD.setText(df.format(total));
        pay1.getTxtTongTien().setText(df.format(total));
        pay1.getTxtThanhTien().setText(df.format(total));
    }

//    public void setDataNV() {
//        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
//        entity.NhanVien nv = null;
//        try {
//            nv = nhanVien_DAO.getNhanVienTheoMaNhanVien("QL010922001").getFirst();
//        } catch (Exception ex) {
//            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        txtMaNV.setText(nv.getMaNhanVien());
//        txtTenNV.setText(nv.getTenNhanVien());
//    }
//
//    public String taoMaHoaDon() {
//        HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
//        PhanCongCa_DAO phanCongCa_DAO = new PhanCongCa_DAO();
//        String maHoaDon = null;
//
//        String ca = "C1";
//        LocalDate today = LocalDate.now();
//        String formattedDate = String.format("%02d%02d%02d",
//                today.getDayOfMonth(),
//                today.getMonthValue(),
//                today.getYear() % 100);
//        int stt = hoaDon_DAO.getALLHoaDon().stream()
//                .map(hd -> Integer.parseInt(hd.getMaHoaDon().substring(hd.getMaHoaDon().length() - 4))) // lấy số thứ tự từ mã hóa đơn
//                .max(Integer::compareTo) // tìm số lớn nhất
//                .orElse(0); // Nếu không có mã nào thì trả về 0
//        maHoaDon = "HD" + formattedDate + ca + String.format("%04d", stt + 1);
//
//        return maHoaDon;
//    }
//
//    public String taoMaKH() {
//        KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
//        String maKH = null;
//        LocalDate today = LocalDate.now();
//        String formattedDate = String.format("%02d%02d%02d",
//                today.getDayOfMonth(),
//                today.getMonthValue(),
//                today.getYear() % 100);
//        int stt = khachHang_DAO.getAllTBKhachHang().stream()
//                .map(hd -> Integer.parseInt(hd.getMaKH().substring(hd.getMaKH().length() - 4))) // lấy số thứ tự từ mã hóa đơn
//                .max(Integer::compareTo) // tìm số lớn nhất
//                .orElse(0); // Nếu không có mã nào thì trả về 0
//        maKH = "KH" + formattedDate + String.format("%04d", stt + 1);
//        return maKH;
//    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//        if (o.equals(btnSearch)) {
//            timKiemSanPham(txtSearch.getText());
//            // Lấy giá trị từ txtDTL
//            String dtlStr = txtDTL.getText(); // Lấy giá trị từ TextField
//            // Kiểm tra nếu chuỗi không rỗng và có thể chuyển đổi thành số nguyên
//            if (!dtlStr.isEmpty()) {
//                try {
//                    int dtl = Integer.parseInt(dtlStr); // Chuyển đổi sang số nguyên
//                    System.out.println(dtl);
//
//                    // Thực hiện kiểm tra giá trị dtl
//                    if (dtl > 100000) {
//                        pay1.getTxtDTL().setText(String.valueOf(dtl));
//                    } else if (dtl > 10000) {
//                        pay1.getTxtDTL().setText(String.valueOf(dtl));
//                    } else if (dtl > 1000) {
//                        pay1.getTxtDTL().setText(String.valueOf(dtl));
//                    }
//                } catch (NumberFormatException ex) {
//                    // Xử lý lỗi nếu chuỗi không phải là số nguyên hợp lệ
//                    System.out.println("Giá trị không hợp lệ: " + dtlStr);
//                }
//            } else {
//                System.out.println("Vui lòng nhập giá trị vào trường.");
//            }
//        }
//        if (o.equals(btnSearch1)) {
//            if (!txtSDT.getText().isEmpty()) {
//                try {
////                    KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
////                    ArrayList<entity.KhachHang> dsKH = khachHang_DAO.getKHTheoSDT(txtSDT.getText());
////                    if (!txtSDT.getText().matches("0(1|3|5|7|9)\\d{8}")) {
////                        JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
////                        txtSDT.requestFocus();
////                        return;
////                    }
////                    if (!dsKH.isEmpty()) {
////                        entity.KhachHang kh = dsKH.get(0);
////                        txtTenKH.setText(kh.getTenKH());
////                        txtDTL.setText(String.valueOf(kh.getDiemTichLuy()));
//                    } else {
//                        // Xuất thông báo hỏi có muốn thêm khách hàng mới không
//                        int response = JOptionPane.showConfirmDialog(null,
//                                "Số điện thoại không tồn tại. Bạn có muốn thêm khách hàng mới không?",
//                                "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//
//                        if (response == JOptionPane.YES_OPTION) {
//                            // Chuyển sang tab thứ 2
//                            tabTTKH.setSelectedIndex(1);
//                            txtSDTMoi.setText(txtSDT.getText());
//                            txtTenKHMoi.requestFocus();
//                        }
//                        txtTenKH.setText("");
//                    }
//                } catch (Exception ex) {
//                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                txtSDT.requestFocus();
//            }
//        }
//
//        if (o.equals(btnThanhToan)) {
//            try {
//                if ((!txtSDT.getText().matches("0(1|3|5|7|9)\\d{8}") || txtSDT.getText().isEmpty())) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//                    txtSDT.requestFocus();
//                    return;
//                }
//
//                if (tabTTKH.getSelectedIndex() == 1) {
//                    if (!txtSDTMoi.getText().matches("0(1|3|5|7|9)\\d{8}")) {
//                        JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//                        txtSDTMoi.requestFocus();
//                        return;
//                    }
//                }
//
//                TextPay txtKhachDua = pay1.getTxtKhachDua();
//                String tienKhachDua = txtKhachDua.getText().replace(" VNĐ", "").replace(",", "");
//                if (!tienKhachDua.matches("\\d+")) {
//                    JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại số tiền thanh toán");
//                    txtKhachDua.requestFocus();
//                    return;
//                }
//                if (tableModel.getRowCount() == 0) {
//                    JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
//                    return;
//                }
//
//                if (tinhTienThua()) {
//                    HoaDon_DAO hddao = new HoaDon_DAO();
//                    ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
//                    KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
//                    Double tongTien = Double.parseDouble(pay1.getTxtTongTien().getText().replace(" VNĐ", "").replace(",", ""));
//                    System.out.println(tongTien);
//                    Double tongTienHD = Double.parseDouble(pay1.getTxtThanhTien().getText().replace(" VNĐ", "").replace(",", ""));
//                    String maKH = null;
//                    if (txtSDTMoi.getText().isEmpty()) {
//                        maKH = khachHang_DAO.getKHTheoSDT(txtSDT.getText()).getFirst().getMaKH();
//                    } else {
//                        maKH = taoMaKH();
//                        khachHang_DAO.create(new entity.KhachHang(maKH, txtTenKHMoi.getText(), txtSDTMoi.getText()));
//                    }
//                    entity.HoaDon hd = new entity.HoaDon(taoMaHoaDon(), LocalDateTime.now(), tongTien,
//                            tongTienHD - tongTien, tongTienHD, new entity.KhachHang(maKH), new entity.NhanVien(txtMaNV.getText()));
//                    hddao.create(hd);
//
//                    for (int i = 0; i < tableModel.getRowCount(); i++) {
//                        try {
//                            DVT_DAO dvt_dao = new DVT_DAO();
//                            ThietBiYTe_DAO thietBiYTe_DAO = new ThietBiYTe_DAO();
//                            Thuoc_DAO thuoc_DAO = new Thuoc_DAO();
//
//                            // Lấy mã thiết bị y tế
//                            String maTBYT = null;
//                            if (!thietBiYTe_DAO.getTBYTTheoTenThietBi(tableOrder.getValueAt(i, 1).toString()).isEmpty()) {
//                                ThietBiYTe thietBiYTe = thietBiYTe_DAO.getTBYTTheoTenThietBi(tableOrder.getValueAt(i, 1).toString()).getFirst();
//                                maTBYT = thietBiYTe.getMaThietBi();
//                                int soLuong = Integer.parseInt(tableOrder.getValueAt(i, 5).toString());
//                                thietBiYTe.capNhatSoLuong(soLuong);
//                                thietBiYTe_DAO.update(thietBiYTe);
//                            }
//
//                            // Lấy mã thuốc
//                            String maThuoc = null;
//                            if (!thuoc_DAO.getThuocTheoTenThuoc(tableOrder.getValueAt(i, 1).toString()).isEmpty()) {
//                                Thuoc thuoc = thuoc_DAO.getThuocTheoTenThuoc(tableOrder.getValueAt(i, 1).toString()).getFirst();
//                                maThuoc = thuoc.getMaThuoc();
//                                int soLuong = Integer.parseInt(tableOrder.getValueAt(i, 5).toString());
//                                thuoc.capNhatSoLuong(soLuong);
//                                thuoc_DAO.update(thuoc);
//                            }
//
//                            // Lấy mã đơn vị tính
//                            String maDVT = null;
//                            if (!dvt_dao.getDVTTheoTen(tableOrder.getValueAt(i, 2).toString()).isEmpty()) {
//                                maDVT = dvt_dao.getDVTTheoTen(tableOrder.getValueAt(i, 2).toString()).getFirst().getMaDVT();
//                            }
//                            int sl = Integer.parseInt(tableOrder.getValueAt(i, 5).toString());
//                            chiTietHoaDon_DAO.create(new ChiTietHoaDon(hd, new Thuoc(maThuoc), new ThietBiYTe(maTBYT), SOMEBITS, new DonViTinh(maDVT)));
//                        } catch (Exception ex) {
//                            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                    JOptionPane.showMessageDialog(this, "Thanh toán thành công");
//                    tableModel.setRowCount(0);
//                    tabHoaDon.setTitleAt(0, taoMaHoaDon());
//                    txtKhachDua.setText("0 VNĐ");
//                    pay1.getTxtTienThua().setText("0 VNĐ");
//                    txtSDT.setText("");
//                    txtTenKH.setText("");
//                    txtSDTMoi.setText("");
//                    txtTenKHMoi.setText("");
//                    sumAmount();
//                }
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        // Vẽ viền
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(206, 212, 218));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 18, 18);
        super.paintComponent(g);
    }

//    private void timKiemSanPham(String text) {
//        ArrayList<Thuoc> dsThuoc = new ArrayList<>();
//        ArrayList<ThietBiYTe> dsTBYT = new ArrayList<>();
//        ThietBiYTe_DAO thietBiYTe_DAO = new ThietBiYTe_DAO();
//        Thuoc_DAO thuoc_DAO = new Thuoc_DAO();
//        System.out.println(text);
//        if (!text.isEmpty()) {
//            try {
//                dsThuoc.addAll(thuoc_DAO.getThuocTheoTenThuoc(text));
//                dsTBYT.addAll(thietBiYTe_DAO.getTBYTTheoTenThietBi(text));
//                if (dsTBYT.size() == 0 && dsThuoc.size() == 0) {
//                    JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                    txtSearch.requestFocus();
//                    txtSearch.selectAll();
//                    return;
//                }
//                if (!dsThuoc.isEmpty()) {
//                    Thuoc t = dsThuoc.getFirst();
//                    // Thêm hàng vào bảng
//                    tableModel.addRow(new Object[]{
//                        t.getImg(),
//                        t.getTenThuoc(),
//                        t.getDsDVT().getFirst().getDVT().getDVT(),
//                        df.format(t.getDsDVT().getFirst().getGiaBan()),
//                        df.format(t.getDsDVT().getFirst().getGiaBan()),
//                        1,
//                        df.format(1 * t.getDsDVT().getFirst().getGiaBan())
//                    });
//
//                }
//                if (!dsTBYT.isEmpty()) {
//                    // Thêm các kết quả từ danh sách thiết bị y tế vào bảng
//                    ThietBiYTe tbyt = dsTBYT.getFirst();
//                    tableModel.addRow(new Object[]{
//                        tbyt.getImg(),
//                        tbyt.getTenThietBi(),
//                        tbyt.getDonViTinh().getDVT(),
//                        df.format(tbyt.getGiaBan()),
//                        df.format(tbyt.getGiaBan()),
//                        1,
//                        df.format(1 * tbyt.getGiaBan())
//                    });
//                }
//                txtSearch.setText("");
//                txtSearch.requestFocus();
//                SwingUtilities.invokeLater(() -> {
//                    tableModel.fireTableDataChanged();
//                    tableOrder.revalidate(); // Cập nhật JTable
//                    tableOrder.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableOrder.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//                sumAmount();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } catch (Exception ex) {
//                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, "Error fetching medical equipment", ex);
//            }
//        } else {
//            txtSearch.requestFocus();
//        }
//    }

    private boolean tinhTienThua() {
        int khachDua = Integer.parseInt(pay1.getTxtKhachDua().getText().replaceAll("[^0-9]", ""));
        int thanhTien = Integer.parseInt(txtThanhTien.getText().replace(" VNĐ", "").replace(",", ""));
        if (khachDua < thanhTien) {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại tiền khách đưa");
            pay1.getTxtKhachDua().requestFocus();
            return false;
        } else {
            pay1.getTxtTienThua().setText(df.format(khachDua - thanhTien));
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaHD = new gui.textfield.TextPay();
        lblMaHD = new javax.swing.JLabel();
        txtQuay = new gui.textfield.TextPay();
        lblQuay = new javax.swing.JLabel();
        txtDTLMoi = new gui.textfield.TextPay();
        lblDTLMoi = new javax.swing.JLabel();
        txtTenKHMoi = new gui.textfield.TextPay();
        lblTenKHMoi = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        txtTenKH = new gui.textfield.TextPay();
        lblDTL = new javax.swing.JLabel();
        txtDTL = new gui.textfield.TextPay();
        pnlCenter = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        pay1 = new gui.pay.Pay();
        pnlHD = new javax.swing.JPanel();
        tabHoaDon = new gui.tabbedpane.TabbedPaneCustom();
        pnlOrder = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tableOrder = new gui.table.TableCustom();
        cmdTT = new javax.swing.JPanel();
        lblTTHD = new javax.swing.JLabel();
        txtTTHD = new javax.swing.JLabel();
        pnlTop = new javax.swing.JPanel();
        tabTTKH = new gui.tabbedpane.TabbedPaneCustom();
        pnlKH = new javax.swing.JPanel();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new gui.textfield.TextPay();
        pnlKHMoi = new javax.swing.JPanel();
        lblSDTMoi = new javax.swing.JLabel();
        txtSDTMoi = new gui.textfield.TextPay();
        pnlTTNV = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        txtMaNV = new gui.textfield.TextPay();
        lblTenNV = new javax.swing.JLabel();
        txtTenNV = new gui.textfield.TextPay();
        pnlHoaDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new gui.textfield.TextPay();

        txtMaHD.setPreferredSize(new java.awt.Dimension(150, 40));

        lblMaHD.setText("Mã hóa đơn");
        lblMaHD.setPreferredSize(new java.awt.Dimension(90, 40));

        txtQuay.setText("Quầy số 1");
        txtQuay.setPreferredSize(new java.awt.Dimension(98, 40));

        lblQuay.setText("Quầy");
        lblQuay.setPreferredSize(new java.awt.Dimension(40, 40));

        txtDTLMoi.setPreferredSize(new java.awt.Dimension(165, 40));

        lblDTLMoi.setText("Điểm tích lũy");
        lblDTLMoi.setPreferredSize(new java.awt.Dimension(80, 40));

        txtTenKHMoi.setPreferredSize(new java.awt.Dimension(270, 40));

        lblTenKHMoi.setText("Tên khách hàng");
        lblTenKHMoi.setPreferredSize(new java.awt.Dimension(100, 40));

        lblTenKH.setText("Tên khách hàng");
        lblTenKH.setPreferredSize(new java.awt.Dimension(100, 40));

        txtTenKH.setPreferredSize(new java.awt.Dimension(270, 40));

        lblDTL.setText("Điểm tích lũy");
        lblDTL.setPreferredSize(new java.awt.Dimension(80, 40));

        txtDTL.setPreferredSize(new java.awt.Dimension(120, 40));

        setLayout(new java.awt.BorderLayout());

        pnlCenter.setOpaque(false);
        pnlCenter.setLayout(new java.awt.BorderLayout());

        jPanel14.setOpaque(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(350, 100));
        jPanel14.setLayout(new java.awt.BorderLayout());
        jPanel14.add(pay1, java.awt.BorderLayout.CENTER);

        pnlCenter.add(jPanel14, java.awt.BorderLayout.LINE_END);

        pnlHD.setOpaque(false);
        pnlHD.setLayout(new java.awt.BorderLayout());

        pnlOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 216), 2));
        pnlOrder.setOpaque(false);
        pnlOrder.setLayout(new java.awt.BorderLayout());

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ảnh", "Tên sản phẩm", "Đơn vị tính", "Giá bán", "Giá bán khuyễn mãi", "Số lượng", "Tổng giá tiền", "Xóa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrder.setRowHeight(100);
        jScrollPane.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableOrder.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableOrder.getColumnModel().getColumn(5).setPreferredWidth(50);
            tableOrder.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        pnlOrder.add(jScrollPane, java.awt.BorderLayout.CENTER);

        cmdTT.setOpaque(false);
        cmdTT.setPreferredSize(new java.awt.Dimension(250, 40));
        cmdTT.setLayout(new java.awt.BorderLayout());

        lblTTHD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTTHD.setText("Tổng tiền");
        cmdTT.add(lblTTHD, java.awt.BorderLayout.CENTER);

        txtTTHD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTTHD.setText("0 VNĐ");
        txtTTHD.setPreferredSize(new java.awt.Dimension(140, 16));
        cmdTT.add(txtTTHD, java.awt.BorderLayout.LINE_END);

        pnlOrder.add(cmdTT, java.awt.BorderLayout.PAGE_END);

        tabHoaDon.addTab("0001", pnlOrder);

        pnlHD.add(tabHoaDon, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlHD, java.awt.BorderLayout.CENTER);

        add(pnlCenter, java.awt.BorderLayout.CENTER);

        pnlTop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 212, 216), 2));
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new java.awt.Dimension(100, 140));
        pnlTop.setLayout(new java.awt.BorderLayout());

        pnlKH.setOpaque(false);
        pnlKH.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        lblSDT.setText("Số điện thoại");
        lblSDT.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlKH.add(lblSDT);

        txtSDT.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlKH.add(txtSDT);

        tabTTKH.addTab("Khách hàng", pnlKH);

        pnlKHMoi.setOpaque(false);
        pnlKHMoi.setPreferredSize(new java.awt.Dimension(450, 60));
        pnlKHMoi.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        lblSDTMoi.setText("Số điện thoại");
        lblSDTMoi.setPreferredSize(new java.awt.Dimension(80, 40));
        pnlKHMoi.add(lblSDTMoi);

        txtSDTMoi.setPreferredSize(new java.awt.Dimension(150, 40));
        pnlKHMoi.add(txtSDTMoi);

        tabTTKH.addTab("Khách hàng mới", pnlKHMoi);

        pnlTop.add(tabTTKH, java.awt.BorderLayout.CENTER);

        pnlTTNV.setOpaque(false);
        pnlTTNV.setPreferredSize(new java.awt.Dimension(350, 100));
        pnlTTNV.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 2));

        lblMaNV.setText("Mã nhân viên");
        lblMaNV.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTTNV.add(lblMaNV);

        txtMaNV.setPreferredSize(new java.awt.Dimension(246, 40));
        pnlTTNV.add(txtMaNV);

        lblTenNV.setText("Tên nhân viên");
        lblTenNV.setPreferredSize(new java.awt.Dimension(90, 40));
        pnlTTNV.add(lblTenNV);

        txtTenNV.setPreferredSize(new java.awt.Dimension(246, 40));
        pnlTTNV.add(txtTenNV);

        pnlTop.add(pnlTTNV, java.awt.BorderLayout.LINE_END);

        pnlHoaDon.setOpaque(false);
        pnlHoaDon.setPreferredSize(new java.awt.Dimension(100, 46));
        pnlHoaDon.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 2));

        jLabel1.setText("Tìm kiếm sản phẩm");
        pnlHoaDon.add(jLabel1);

        txtSearch.setPreferredSize(new java.awt.Dimension(450, 40));
        pnlHoaDon.add(txtSearch);

        pnlTop.add(pnlHoaDon, java.awt.BorderLayout.PAGE_END);

        add(pnlTop, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        FormSales banHang = new FormSales();
        frame.add(banHang);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cmdTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblDTL;
    private javax.swing.JLabel lblDTLMoi;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblQuay;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSDTMoi;
    private javax.swing.JLabel lblTTHD;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenKHMoi;
    private javax.swing.JLabel lblTenNV;
    private gui.pay.Pay pay1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlHD;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKH;
    private javax.swing.JPanel pnlKHMoi;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlTTNV;
    private javax.swing.JPanel pnlTop;
    private gui.tabbedpane.TabbedPaneCustom tabHoaDon;
    private gui.tabbedpane.TabbedPaneCustom tabTTKH;
    private gui.table.TableCustom tableOrder;
    private gui.textfield.TextPay txtDTL;
    private gui.textfield.TextPay txtDTLMoi;
    private gui.textfield.TextPay txtMaHD;
    private gui.textfield.TextPay txtMaNV;
    private gui.textfield.TextPay txtQuay;
    private gui.textfield.TextPay txtSDT;
    private gui.textfield.TextPay txtSDTMoi;
    private gui.textfield.TextPay txtSearch;
    private javax.swing.JLabel txtTTHD;
    private gui.textfield.TextPay txtTenKH;
    private gui.textfield.TextPay txtTenKHMoi;
    private gui.textfield.TextPay txtTenNV;
    // End of variables declaration//GEN-END:variables

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        try {
//            int row = tableOrder.getSelectedRow();
//
//            if (row != -1) { // Đảm bảo rằng có một hàng được chọn
//                Thuoc_DAO tdao = new Thuoc_DAO();
//                ThietBiYTe_DAO tbytdao = new ThietBiYTe_DAO();
//                ArrayList<Thuoc> dsThuoc = new ArrayList<>();
//                ArrayList<ThietBiYTe> dsTBYT = new ArrayList<>();
//                try {
//                    dsTBYT = tbytdao.getTBYTTheoTenThietBi(tableOrder.getValueAt(row, 1).toString());
//                } catch (Exception ex) {
//                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                dsThuoc = tdao.getThuocTheoTenThuoc(tableOrder.getValueAt(row, 1).toString());
//                AtomicInteger count = new AtomicInteger(0); // Khai báo biến đếm là AtomicInteger
//                comboxDVT.removeAllItems(); // Xóa tất cả mục hiện có trong ComboBox
//                if (!dsThuoc.isEmpty()) {
//                    Thuoc thuoc = dsThuoc.getFirst();
//                    thuoc.getDsDVT().stream()
//                            .forEach(dvt -> {
//                                if (count.get() < 3) { // Chỉ thêm nếu count nhỏ hơn 3
//                                    comboxDVT.addItem(dvt.getDVT().getDVT());
//                                    count.incrementAndGet(); // Tăng count sau khi thêm thành công
//                                }
//                            });
//                } else if (!dsTBYT.isEmpty()) {
//                    ThietBiYTe tbyt = dsTBYT.getFirst();
//                    comboxDVT.addItem(tbyt.getDonViTinh().getDVT());
//                }
//                // Xóa các mục hiện có trong ComboBox trước khi thêm mới
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        sumAmount();
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

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
