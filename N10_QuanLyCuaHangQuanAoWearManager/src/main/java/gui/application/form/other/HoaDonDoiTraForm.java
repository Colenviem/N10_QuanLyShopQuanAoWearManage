package gui.application.form;

//import dao.ChiTietHoaDon_DAO;
//import dao.DVT_DAO;
//import dao.HoaDon_DAO;
//import dao.NhaCungCap_DAO;
//import dao.NhomThuoc_DAO;
//import entity.ChiTietHoaDon;
//import entity.DonViTinh;
//import entity.Thuoc;
import gui.button.ButtonCustom;
import gui.textfield.TextPay;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class HoaDonDoiTraForm extends JPanel {

    private JFrame parentFrame;
    private ButtonCustom btnLuu;
    private ButtonCustom btnHuy;
    private ButtonCustom btnLink;
    private TextPay txtLink;
    private ButtonCustom btnDate;
    private TextPay txtDate;
    private JCheckBox trangThai;
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    private  ChiTietHoaDon_DAO cthd_dao;
//    private HoaDon_DAO hd_dao;


    public HoaDonDoiTraForm(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initComponents();
        init();
       
    }
    
    public HoaDonDoiTraForm()  throws Exception {
//        this.parentFrame = parentFrame;
//        initComponents();
//        init();
////        hd_dao= new HoaDon_DAO();
////        cthd_dao= new ChiTietHoaDon_DAO();
//        DocDuLieuDatabaseVaoTableCT(hdMau);
//        DienDuLieuHDCuVaoForm(hdMau);
//        radDoi.addActionListener(this);
//        radTra.addActionListener(this);
//        tableChiTietHDCu.addMouseListener(this);
//        btnThem.addActionListener(this);
//        btnXoaDong.addActionListener(this);
//        btnXoaAll.addActionListener(this);
//        tableChiTietDoiTra.getModel().addTableModelListener(this);
//        btnLuu.addActionListener(this);
    }

    private void init() {
        ButtonGroup group = new ButtonGroup();
        group.add(radDoi);
        group.add(radTra);
        
        btnLuu = new ButtonCustom("Tạo hóa đơn (F9)", "");
        btnHuy = new ButtonCustom("Hủy", "");

        btnLuu.setPreferredSize(new Dimension(100, 40));
        btnHuy.setPreferredSize(new Dimension(100, 40));

        btnLuu.setDefaultColor(new Color(26, 188, 156));
        btnLuu.setHoverColor(new Color(10, 150, 120));
        btnLuu.setShowBorder(false);
        btnLuu.setHorizontalTextPosition(SwingConstants.CENTER);

        btnHuy.setDefaultColor(new Color(255, 56, 74));
        btnHuy.setHoverColor(new Color(240, 40, 40));
        btnHuy.setShowBorder(false);
        btnHuy.setHorizontalTextPosition(SwingConstants.CENTER);

        pnlBtn.add(btnLuu);
        pnlBtn.add(btnHuy);
        pnlBtn.setBorder(new EmptyBorder(0, 0, 10, 10));
        txtMaNV.setFocusable(false);
        txtMaNV.setEditable(false);
        txtTenNV.setFocusable(false);
        txtTenNV.setEditable(false);
        txtMaKH.setFocusable(false);
        txtMaKH.setEditable(false);
        txtMaKH.setFocusable(false);
        txtTenKH.setEditable(false);
        txtMaHDCu.setFocusable(false);
        txtMaHDCu.setEditable(false);
        txtMaHDMoi.setFocusable(false);
        txtMaHDMoi.setEditable(false);
        txtNgayDoiTra.setFocusable(false);
        txtNgayDoiTra.setEditable(false);
        txtTongTienHoaDonTra.setFocusable(false);
        txtTongTienHoaDonTra.setEditable(false);
//        btnHuy.addActionListener(this);
        //---------------------------------------//
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);

        g2.setColor(new Color(206, 212, 216));
        g2.setStroke(new BasicStroke(2)); // Đặt độ dày viền là 2px
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 18, 18); // Điều chỉnh để viền nằm bên trong
        super.paintComponent(g);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//        if (o.equals(btnHuy)) {
//            parentFrame.dispose();
//        }
//        if(o.equals(radDoi)){
//            if(radDoi.isSelected()){
//                txtMaHDMoi.setText(txtMaHDCu.getText()+"D");
//            }else{
//                txtMaHDMoi.setText(txtMaHDCu.getText()+"T");
//            }
//        }
//        if(o.equals(radTra)){
//            if(radTra.isSelected()){
//                txtMaHDMoi.setText(txtMaHDCu.getText()+"T");
//            }else{
//                txtMaHDMoi.setText(txtMaHDCu.getText()+"D");
//            }
//        }
//        if(o.equals(btnThem)){
//            if(tableChiTietHDCu.getSelectedRow()!=-1){
//                themVaoCTHDDT(tableChiTietHDCu.getSelectedRow());
//            }
//            System.out.println("gui.form.HoaDonDoiTraForm.actionPerformed()");
//        }
//        if(o.equals(btnXoaDong)){
//            DefaultTableModel model = (DefaultTableModel) tableChiTietDoiTra.getModel();
//            int selectedRow = tableChiTietDoiTra.getSelectedRow();
//            if (selectedRow != -1) { // Kiểm tra nếu có dòng nào được chọn
//                model.removeRow(selectedRow);
//                System.out.println("Đã xóa dòng: " + selectedRow);
//            } else {
//                System.out.println("Vui lòng chọn một dòng để xóa.");
//            }
//        }
//        if(o.equals(btnXoaAll)){
//            DefaultTableModel model = (DefaultTableModel) tableChiTietDoiTra.getModel();
//            model.setRowCount(0);
//        }
//        if(o.equals(btnLuu)){
//            if(tableChiTietDoiTra.getRowCount()<1){
//                JOptionPane.showMessageDialog(null, "Bạn chưa thêm sản phẩm vào bảng chi tiết hóa đơn đổi/sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            }else{
//                if(txtLyDo.getText().trim().isEmpty()){
//                    JOptionPane.showMessageDialog(null, "Vui lòng nhập lý do đổi/trả sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                }else{
//                    entity.HoaDon hoaDonMoi=taoHDTuForm();
//                }
//            }
//        }
//    }
    
    
    

    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(950, 600);
        HoaDonDoiTraForm tn = new HoaDonDoiTraForm(frame);
        frame.add(tn);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Xóa bỏ viền mặc định của frame
        frame.pack();
        frame.setVisible(true);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        pnlTilte = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlBtn = new javax.swing.JPanel();
        pnlInfor = new javax.swing.JPanel();
        pnlImg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableChiTietHDCu = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        jPanelThongTinHD = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelThongTinNV = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jPanelThongTinNV1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaHDCu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaHDMoi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNgayDoiTra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        radTra = new javax.swing.JRadioButton();
        radDoi = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableChiTietDoiTra = new javax.swing.JTable();
        btnXoaDong = new javax.swing.JButton();
        btnXoaAll = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTongTienHoaDonTra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtLyDo = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        pnlTilte.setOpaque(false);
        pnlTilte.setPreferredSize(new java.awt.Dimension(85, 40));
        pnlTilte.setLayout(new java.awt.BorderLayout());

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Tạo hóa đơn đổi trả");
        pnlTilte.add(lblTitle, java.awt.BorderLayout.CENTER);

        add(pnlTilte, java.awt.BorderLayout.PAGE_START);

        pnlBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlBtn.setOpaque(false);
        pnlBtn.setPreferredSize(new java.awt.Dimension(400, 60));
        pnlBtn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));
        add(pnlBtn, java.awt.BorderLayout.PAGE_END);

        pnlInfor.setOpaque(false);
        pnlInfor.setPreferredSize(new java.awt.Dimension(1000, 540));
        pnlInfor.setLayout(new java.awt.BorderLayout());

        pnlImg.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn cũ"));
        pnlImg.setOpaque(false);
        pnlImg.setPreferredSize(new java.awt.Dimension(470, 600));

        tableChiTietHDCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Đơn giá", "Số lượng", "Tổng Tiền"
            }
        ));
        jScrollPane1.setViewportView(tableChiTietHDCu);

        pnlImg.add(jScrollPane1);

        btnThem.setText("Thêm vào hóa đơn đổi/trả");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlImg.add(btnThem);

        pnlInfor.add(pnlImg, java.awt.BorderLayout.LINE_START);

        jPanelThongTinHD.add(jLabel1);

        jPanelThongTinNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));
        jPanelThongTinNV.setPreferredSize(new java.awt.Dimension(510, 90));

        jLabel2.setText("Mã nhân viên:");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 16));
        jPanelThongTinNV.add(jLabel2);

        txtMaNV.setPreferredSize(new java.awt.Dimension(350, 22));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        jPanelThongTinNV.add(txtMaNV);

        jLabel3.setText("Tên nhân viên:");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 16));
        jPanelThongTinNV.add(jLabel3);

        txtTenNV.setPreferredSize(new java.awt.Dimension(350, 22));
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });
        jPanelThongTinNV.add(txtTenNV);

        jPanelThongTinHD.add(jPanelThongTinNV);

        jPanelThongTinNV1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));
        jPanelThongTinNV1.setPreferredSize(new java.awt.Dimension(510, 90));

        jLabel4.setText("Mã khách hàng:");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 16));
        jPanelThongTinNV1.add(jLabel4);

        txtMaKH.setPreferredSize(new java.awt.Dimension(350, 22));
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });
        jPanelThongTinNV1.add(txtMaKH);

        jLabel5.setText("Tên khách hàng:");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 16));
        jPanelThongTinNV1.add(jLabel5);

        txtTenKH.setPreferredSize(new java.awt.Dimension(350, 22));
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });
        jPanelThongTinNV1.add(txtTenKH);

        jPanelThongTinHD.add(jPanelThongTinNV1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin đơn đổi trả"));
        jPanel1.setPreferredSize(new java.awt.Dimension(510, 400));
        jPanel1.setRequestFocusEnabled(false);

        jLabel7.setText("Mã hóa đơn cũ:");
        jLabel7.setPreferredSize(new java.awt.Dimension(106, 16));
        jPanel1.add(jLabel7);

        txtMaHDCu.setPreferredSize(new java.awt.Dimension(350, 22));
        txtMaHDCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDCuActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaHDCu);

        jLabel6.setText("Mã hóa mới:");
        jLabel6.setPreferredSize(new java.awt.Dimension(106, 16));
        jPanel1.add(jLabel6);

        txtMaHDMoi.setPreferredSize(new java.awt.Dimension(350, 22));
        txtMaHDMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDMoiActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaHDMoi);

        jLabel8.setText("Ngày đổi trả");
        jLabel8.setPreferredSize(new java.awt.Dimension(106, 16));
        jPanel1.add(jLabel8);

        txtNgayDoiTra.setPreferredSize(new java.awt.Dimension(350, 22));
        txtNgayDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayDoiTraActionPerformed(evt);
            }
        });
        jPanel1.add(txtNgayDoiTra);

        jLabel9.setText("Loại:");
        jLabel9.setPreferredSize(new java.awt.Dimension(106, 16));
        jPanel1.add(jLabel9);

        radTra.setText("Trả hàng");
        radTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTraActionPerformed(evt);
            }
        });
        jPanel1.add(radTra);

        radDoi.setText("Đổi hàng");
        radDoi.setPreferredSize(new java.awt.Dimension(275, 21));
        radDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDoiActionPerformed(evt);
            }
        });
        jPanel1.add(radDoi);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết đơn đổi/ trả"));
        jPanel3.setPreferredSize(new java.awt.Dimension(480, 150));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 120));

        tableChiTietDoiTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Đơn giá", "Số lượng"
            }
        ));
        tableChiTietDoiTra.setPreferredSize(new java.awt.Dimension(300, 100));
        jScrollPane2.setViewportView(tableChiTietDoiTra);

        jPanel3.add(jScrollPane2);

        jPanel1.add(jPanel3);

        btnXoaDong.setText("Xóa dòng");
        btnXoaDong.setPreferredSize(new java.awt.Dimension(190, 23));
        btnXoaDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDongActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoaDong);

        btnXoaAll.setText("Xóa tất cả trong bảng");
        btnXoaAll.setPreferredSize(new java.awt.Dimension(190, 23));
        btnXoaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAllActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoaAll);

        jLabel10.setText("Tiền hoàn:");
        jLabel10.setPreferredSize(new java.awt.Dimension(106, 16));
        jPanel1.add(jLabel10);

        txtTongTienHoaDonTra.setPreferredSize(new java.awt.Dimension(350, 22));
        txtTongTienHoaDonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienHoaDonTraActionPerformed(evt);
            }
        });
        jPanel1.add(txtTongTienHoaDonTra);

        jLabel11.setText("Lý do");
        jLabel11.setPreferredSize(new java.awt.Dimension(106, 16));
        jPanel1.add(jLabel11);

        txtLyDo.setPreferredSize(new java.awt.Dimension(350, 22));
        txtLyDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoActionPerformed(evt);
            }
        });
        jPanel1.add(txtLyDo);

        jPanelThongTinHD.add(jPanel1);

        pnlInfor.add(jPanelThongTinHD, java.awt.BorderLayout.CENTER);

        add(pnlInfor, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtMaHDCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDCuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDCuActionPerformed

    private void txtMaHDMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDMoiActionPerformed

    private void txtNgayDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayDoiTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayDoiTraActionPerformed

    private void radTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radTraActionPerformed

    private void radDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radDoiActionPerformed

    private void txtTongTienHoaDonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienHoaDonTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienHoaDonTraActionPerformed

    private void txtLyDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoActionPerformed

    private void btnXoaDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaDongActionPerformed

    private void btnXoaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaAllActionPerformed
    public double tinhTongTien() {
        DefaultTableModel model = (DefaultTableModel) tableChiTietDoiTra.getModel();
        double tongTien = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object soLuong = model.getValueAt(i, 4);
            Object donGia= model.getValueAt(i, 3); 
            if (soLuong != null && donGia!=null) {
                double dg = Double.parseDouble(donGia.toString());
                int sl = Integer.parseInt(soLuong.toString());
                tongTien += dg*sl;
            }
        }
        return tongTien;
    }
//    public void DocDuLieuDatabaseVaoTableCT(entity.HoaDon hd) throws Exception {
//       
//        HashMap<entity.ChiTietHoaDon, Double> giaBanMap = new HashMap<>();
//
//        HashMap<ChiTietHoaDon, Double> list = cthd_dao.getChiTietHoaDonThuocByMaHoaDon(hd.getMaHoaDon());
//        DefaultTableModel model = (DefaultTableModel) tableChiTietHDCu.getModel();
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
//        ArrayList<entity.ChiTietHoaDon> list2 = cthd_dao.getChiTietThietBiYTeByMaHoaDon(hd.getMaHoaDon());
//        DefaultTableModel model2 = (DefaultTableModel) tableChiTietHDCu.getModel();
////        model.setRowCount(0);
//        for (entity.ChiTietHoaDon hd2 : list2) {
//            model.addRow(new Object[]{
//               hd2.getThietBiYTe().getMaThietBi(),hd2.getThietBiYTe().getTenThietBi(),hd2.getDVT().getDVT(),hd2.getThietBiYTe().getGiaBan(),hd2.getSoLuong(),hd2.getThietBiYTe().getGiaBan()*hd2.getSoLuong()
//            });
//        }
//    }
//    public  void DienDuLieuHDCuVaoForm(entity.HoaDon hd){
//        txtMaNV.setText(hd.getNhanVien().getMaNhanVien());
//        txtTenNV.setText(hd.getNhanVien().getTenNhanVien());
//        txtMaKH.setText(hd.getKhachHang().getMaKH());
//        txtTenKH.setText(hd.getKhachHang().getTenKH());
//        txtMaHDCu.setText(hd.getMaHoaDon());
//        txtMaHDMoi.setText(hd.getMaHoaDon()+"D");
//        txtNgayDoiTra.setText(String.valueOf(LocalDateTime.now()));
//        radDoi.setSelected(true);
//        txtLyDo.setText(hd.getGhiChu());
//        txtTongTienHoaDonTra.setText(String.valueOf(tinhTongTien()));
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaAll;
    private javax.swing.JButton btnXoaDong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelThongTinHD;
    private javax.swing.JPanel jPanelThongTinNV;
    private javax.swing.JPanel jPanelThongTinNV1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBtn;
    private javax.swing.JPanel pnlImg;
    private javax.swing.JPanel pnlInfor;
    private javax.swing.JPanel pnlTilte;
    private javax.swing.JRadioButton radDoi;
    private javax.swing.JRadioButton radTra;
    private javax.swing.JTable tableChiTietDoiTra;
    private javax.swing.JTable tableChiTietHDCu;
    private javax.swing.JTextField txtLyDo;
    private javax.swing.JTextField txtMaHDCu;
    private javax.swing.JTextField txtMaHDMoi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayDoiTra;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTongTienHoaDonTra;
    // End of variables declaration//GEN-END:variables
    public  boolean themVaoCTHDDT(int iTableCu){
        String maSanPham = tableChiTietHDCu.getValueAt(iTableCu, 0).toString();
        String tenSanPham = tableChiTietHDCu.getValueAt(iTableCu, 1).toString();
        String dvt = tableChiTietHDCu.getValueAt(iTableCu, 2).toString();
        double donGia = Double.parseDouble(tableChiTietHDCu.getValueAt(iTableCu, 3).toString());
        int soLuong = Integer.parseInt(tableChiTietHDCu.getValueAt(iTableCu, 4).toString());
        System.out.println(maSanPham+tenSanPham+dvt+soLuong);
        DefaultTableModel model = (DefaultTableModel) tableChiTietDoiTra.getModel();
       if(model.getRowCount()>0){
            if (model.getRowCount() > 0) {
                // Duyệt qua bảng để kiểm tra trùng lặp
                for (int i = 0; i < model.getRowCount(); i++) {
                    Object maThuocObj = model.getValueAt(i, 0); // Lấy giá trị mã thuốc từ cột 0
                    Object dvtObj = model.getValueAt(i, 2);     // Lấy giá trị đơn vị tính từ cột 2

                    // Kiểm tra nếu bất kỳ giá trị nào là null, thì bỏ qua hàng này
                    if (maThuocObj == null || dvtObj == null) {
                        continue;
                    }

                    String existingMaThuoc = maThuocObj.toString();
                    String existingDVT = dvtObj.toString();

                    if (maSanPham.equals(existingMaThuoc) && dvt.equals(existingDVT)) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm này đã tồn tại ở bảng chi tiết hóa đơn đổi trả.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                        return false;  // Trả về false nếu sản phẩm đã tồn tại
                    }
                }
            }
       }
         model.addRow(new Object[]{
            maSanPham,tenSanPham,dvt,donGia,soLuong,soLuong*donGia
        });
        return true;
    }
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        Object o=e.getSource();
//        if(o.equals(tableChiTietHDCu)){
//            int row = tableChiTietHDCu.getSelectedRow();
//            if (row != -1) { // Kiểm tra xem có hàng nào được chọn không
////                String maHD = tableOrde.getValueAt(row, 0).toString();
////                String tenKH = tableOrder.getValueAt(row, 1).toString();
////                String tenNV = tableOrder.getValueAt(row, 2).toString();
//            }
//        }
//    }
//    @Override
//    public void mousePressed(MouseEvent e) {}
//
//    @Override
//    public void mouseReleased(MouseEvent e) {}
//
//    @Override
//    public void mouseEntered(MouseEvent e) {}
//
//    @Override
//    public void mouseExited(MouseEvent e) {}
//
//    @Override
//    public void tableChanged(TableModelEvent e) {
//       double tongTien = tinhTongTien();
//        System.out.println("Tiền");
//        // Cập nhật giá trị tổng tiền vào txtTongTien
//        txtTongTienHoaDonTra.setText(String.valueOf(tongTien)); 
//        
//        
//        int row = e.getFirstRow();
//        int column = e.getColumn();
//        
//        // Kiểm tra nếu cột được chỉnh sửa là cột số lượng (cột 5)
//        if (column == 4) {
//            
//            Object maSanPham = tableChiTietDoiTra.getValueAt(row, 0);
//            Object donViTinh = tableChiTietDoiTra.getValueAt(row, 2);
//            Object soLuongMoi = tableChiTietDoiTra.getValueAt(row, 4);
//
//            if (maSanPham != null && donViTinh != null && soLuongMoi != null) {
//                try {
//                    int soLuongMoiInt = Integer.parseInt(soLuongMoi.toString());
//                    // Kiểm tra số lượng mới > 0
//                    if (soLuongMoiInt <= 0) {
//                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.");
//                        tableChiTietDoiTra.setValueAt(1, row, 4); // Đặt lại giá trị nếu không hợp lệ
//                        return;
//                    }
//
//                    // Lấy số lượng từ tableChiTietHDCu dựa trên mã sản phẩm và đơn vị tính
//                    int soLuongCu = 0;
//                    boolean found = false;
//
//                    for (int i = 0; i < tableChiTietHDCu.getRowCount(); i++) {
//                        if (maSanPham.equals(tableChiTietHDCu.getValueAt(i, 0)) &&
//                            donViTinh.equals(tableChiTietHDCu.getValueAt(i, 2))) {
//                            soLuongCu = Integer.parseInt(tableChiTietHDCu.getValueAt(i, 4).toString());
//                            found = true;
//                            break;
//                        }
//                    }
//
//                    // Kiểm tra số lượng mới phải nhỏ hơn số lượng cũ
//                    if (soLuongMoiInt > soLuongCu) {
//                        JOptionPane.showMessageDialog(null, "Số lượng phải nhỏ hơn số lượng trong hóa đơn cũ.");
//                        tableChiTietDoiTra.setValueAt(soLuongCu, row, 4); // Đặt giá trị hợp lệ
//                    }
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên hợp lệ cho số lượng.");
//                    tableChiTietDoiTra.setValueAt(1, row, 4); // Đặt lại giá trị nếu không hợp lệ
//                }
//            }
//        }
    }
//    public entity.HoaDon taoHDTuForm(){
//       
//        String maNew =txtMaHDMoi.getText();
//        String maNV = txtMaNV.getText(); // Mã nhân viên
//        String maKH = txtMaKH.getText(); // Mã khách hàng
//        LocalDateTime ngayDoiTra = LocalDateTime.now(); // Ngày giờ đổi trả hiện tại
//        double tongTien = tinhTongTien()*-1; // Tổng tiền từ hàm tính toán
//        String ghiChu = txtLyDo.getText();
//        entity.HoaDon hdw = null;
//        hdw = new entity.HoaDon(maKH, ngayDoiTra, tongTien, 0, tongTien, ghiChu,new entity.KhachHang(maKH),new entity.NhanVien(maNV));
//        return hdw;
//    } 
//}
