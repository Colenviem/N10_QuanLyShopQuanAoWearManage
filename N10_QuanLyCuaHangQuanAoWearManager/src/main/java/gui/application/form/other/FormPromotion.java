
package gui.application.form.other;
import com.toedter.calendar.JCalendar;
//import connectDB.ConnectDB;
//import dao.ChiTietKhuyenMai_DAO;
//import dao.KhuyenMai_DAO;
//import dao.LoaiKhuyenMai_DAO;
//import dao.ThietBiYTe_DAO;
//import dao.Thuoc_DAO;
//import entity.ChiTietKhuyenMai;
//import entity.LoaiKhuyenMai;
//import entity.ThietBiYTe;
//import entity.Thuoc;
import gui.button.ButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.textfield.TextFieldCustom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FormPromotion extends JPanel implements ActionListener,MouseListener{

    private ButtonCustom btnTimDis;
    private ButtonCustom btnTimPro;
    private ButtonCustom btnDate;
    private ButtonCustom btnDateTo;
    private ButtonCustom btnUpdate;
    private ButtonCustom btnAdd;
    private ButtonCustom btnSave;
    private ComboBoxSuggestion comboBoxTTKM;
    private ComboBoxSuggestion comboBoxTTCTKM;
//    private KhuyenMai_DAO km_dao;
//    private LoaiKhuyenMai_DAO lkm_dao;
//    private ChiTietKhuyenMai_DAO ctkm_dao;
//    private Thuoc_DAO thuoc_dao;
//    private ThietBiYTe_DAO tb_dao;
    private double phanTram, max;
    private String ma;
    private JCheckBox jCheckBox;
    public FormPromotion() {
        try {
//            ConnectDB.getInstance().connect();
//            km_dao = new KhuyenMai_DAO();
//            lkm_dao = new LoaiKhuyenMai_DAO();
//            ctkm_dao=new ChiTietKhuyenMai_DAO();
//            thuoc_dao= new Thuoc_DAO();
//            tb_dao= new ThietBiYTe_DAO();
            initComponents();
            init();
//            DocDuLieuDatabaseVaoTableTT("Đang áp dụng"); // Đẩy dữ liệu lên bảng khi khởi tạo
//            DocDuLieuVaoCBBLoai();
            tableKM.addMouseListener(this);
            btnAdd.addActionListener(this);
            btnUpdate.addActionListener(this);
            comboBoxTTKM.addActionListener(this);
            comboBoxTTCTKM.addActionListener(this);
            comboBoxLoai.addActionListener(this);
            jCheckBox.addActionListener(this);
            btnSave.addActionListener(this);
            btnTimDis.addActionListener(this);
            btnTimPro.addActionListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        jPanel1 = new javax.swing.JPanel();
         cmd0 = new javax.swing.JPanel();
        cmd1 = new javax.swing.JPanel();
        jLabel0 = new javax.swing.JLabel();
        txtmaKM = new gui.textfield.TextPay();
        jLabel1 = new javax.swing.JLabel();
        txtTenKM = new gui.textfield.TextPay();
        cmd2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNgayBD = new gui.textfield.TextPay();
        cmd3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayKT = new gui.textfield.TextPay();
        cmd4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        comBoBoxNhapTT = new gui.combobox.ComboBoxSuggestion();
        cmd6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        comboBoxLoaiKM = new gui.combobox.ComboBoxSuggestion();
        cmd7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtGiaTriKM = new gui.textfield.TextPay();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSoTienGiamToiDa = new gui.textfield.TextPay();;
        cmd5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtxtMoTa = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKM = new gui.table.TableCustom();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelLoai = new javax.swing.JLabel();
        jLabelNhapMa = new javax.swing.JLabel();
        
        txtTimKM = new TextFieldCustom("Nhập nội dung tìm kiếm...");
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTKM = new gui.table.TableCustom();
        pnlSpKm = new javax.swing.JPanel();
        comboBoxLoai = new gui.combobox.ComboBoxSuggestion();
        comboBoxLoai.addItem("Thuốc");
        comboBoxLoai.addItem("Thiết bị y tế");
        comboBoxLoai.addItem("Sản phẩm sắp hết hạn");
        comboBoxLoai.addItem("Tất cả");
        comboBoxLoai.setSelectedItem("Tất cả");
        txtTimMaSanPham = new TextFieldCustom("Nhập nội dung tìm kiếm...");

        setLayout(new java.awt.BorderLayout(10, 0));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 40));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 10));
        
        cmd0.setOpaque(false);
        cmd0.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd0.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        cmd1.setOpaque(false);
        cmd1.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        
        jLabel0.setText("Mã khuyến mãi");
        jLabel0.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd0.add(jLabel0);

        txtmaKM.setPreferredSize(new java.awt.Dimension(200, 40));
        cmd0.add(txtmaKM);
        
        jLabel1.setText("Tên khuyến mãi");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd1.add(jLabel1);
        jPanel1.add(cmd0);
        txtTenKM.setPreferredSize(new java.awt.Dimension(200, 40));
        cmd1.add(txtTenKM);

        jPanel1.add(cmd1);

        cmd2.setOpaque(false);
        cmd2.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        jLabel2.setText("Ngày bắt đầu");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd2.add(jLabel2);

        txtNgayBD.setPreferredSize(new java.awt.Dimension(160, 40));
        cmd2.add(txtNgayBD);

        jPanel1.add(cmd2);

        cmd3.setOpaque(false);
        cmd3.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        jLabel3.setText("Ngày kết thúc");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd3.add(jLabel3);

        txtNgayKT.setMinimumSize(new java.awt.Dimension(200, 40));
        txtNgayKT.setPreferredSize(new java.awt.Dimension(160, 40));
        cmd3.add(txtNgayKT);

        jPanel1.add(cmd3);

        cmd4.setOpaque(false);
        cmd4.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        jLabel4.setText("Trạng thái");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd4.add(jLabel4);

        comBoBoxNhapTT.setPreferredSize(new java.awt.Dimension(200, 40));
        
        comBoBoxNhapTT.addItem("Đang áp dụng");
         comBoBoxNhapTT.addItem("Ngưng áp dụng");
        cmd4.add(comBoBoxNhapTT);
        
        jPanel1.add(cmd4);

        cmd6.setOpaque(false);
        cmd6.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        jLabel7.setText("Loại khuyến mãi");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd6.add(jLabel7);

        comboBoxLoaiKM.setPreferredSize(new java.awt.Dimension(200, 40));
        
        cmd6.add(comboBoxLoaiKM);

        jPanel1.add(cmd6);

        cmd7.setOpaque(false);
        cmd7.setPreferredSize(new java.awt.Dimension(360, 40));
        cmd7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        jLabel9.setText("Giá trị khuyến mãi");
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 40));
        cmd7.add(jLabel9);

        txtGiaTriKM.setPreferredSize(new java.awt.Dimension(200, 40));
        cmd7.add(txtGiaTriKM);
        

        jPanel1.add(cmd7);

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(360, 40));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        jLabel8.setText("Số tiền giảm tối đa");
        jLabel8.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel8.add(jLabel8);

        txtSoTienGiamToiDa.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel8.add(txtSoTienGiamToiDa);

        jPanel1.add(jPanel8);

        cmd5.setOpaque(false);
        cmd5.setPreferredSize(new java.awt.Dimension(360, 200));
        cmd5.setLayout(new java.awt.BorderLayout());

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 0));

        jLabel5.setText("Mô tả");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 40));
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel5);

        cmd5.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jtxtMoTa.setColumns(20);
        jtxtMoTa.setRows(5);
        jScrollPane3.setViewportView(jtxtMoTa);

        cmd5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(cmd5);

        add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 350));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        
        tableKM.setBorder(null);
        tableKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null,null, null, null, null},
                {null, null, null, null,null,null, null, null, null},
                {null, null, null, null,null,null, null, null, null},
                {null, null, null, null,null,null, null, null, null}
            },
            new String [] {
                "Mã khuyến mãi", "Tên khuyến mãi", "Loại khuyến mãi", "Thời gian bắt đầu", "Thời gian kết thúc", "Giá trị khuyến mãi", "Tiền giảm tối đa" ,"Trạng thái", "Mô tả"
            }
        ));
        jScrollPane1.setViewportView(tableKM);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        jLabel6.setText("Nhập mã khuyến mãi");
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel5.add(jLabel6);

        txtTimKM.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel5.add(txtTimKM);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        tableCTKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, false},
                {null, null, null, false},
                {null, null, null, false},
                {null, null, null, false}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Áp dụng"
            }
        ) {
            Class[] columnTypes = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex]; // Xác định kiểu dữ liệu của từng cột
            }
        });
        // Thiết lập chiều rộng cho cột
        tableCTKM.getColumnModel().getColumn(3).setPreferredWidth(50); // Cột Boolean

        jScrollPane2.setViewportView(tableCTKM);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlSpKm.setOpaque(false);
        pnlSpKm.setPreferredSize(new java.awt.Dimension(10, 110));
        pnlSpKm.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jLabelNhapMa.setText("Nhập mã sản phẩm");
        jLabelNhapMa.setPreferredSize(new java.awt.Dimension(120, 40));
        pnlSpKm.add(jLabelNhapMa);
       
        txtTimMaSanPham.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlSpKm.add(txtTimMaSanPham);

        jPanel4.add(pnlSpKm, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        setOpaque(false);
        //---------------------------------//
        btnDate = new ButtonCustom("", "imgs\\icon\\date.png");
        btnDate.setPreferredSize(new Dimension(40, 40));
        btnDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo JDialog
                JDialog dialog = new JDialog();
                dialog.setSize(300, 300);

                // Tạo JCalendar
                JCalendar calendar = new JCalendar();

                // Thêm JCalendar vào JDialog
                dialog.add(calendar, BorderLayout.CENTER);

                // Thêm nút để xác nhận chọn ngày
                JButton okButton = new JButton("OK");
                okButton.addActionListener(event -> {
                    Date selectedDate = calendar.getDate();
                   
                    dialog.dispose(); // Đóng hộp thoại
                });

                dialog.add(okButton, BorderLayout.SOUTH);

                // Hiển thị JDialog
                dialog.setLocationRelativeTo(jPanel1); // Đặt vị trí của dialog
                dialog.setVisible(true);
            }
        });
        cmd2.add(btnDate);
        //---------------------------------//
        btnDateTo = new ButtonCustom("", "imgs\\icon\\date.png");
        btnDateTo.setPreferredSize(new Dimension(40, 40));
        btnDateTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo JDialog
                JDialog dialog = new JDialog();
                dialog.setSize(300, 300);

                // Tạo JCalendar
                JCalendar calendar = new JCalendar();

                // Thêm JCalendar vào JDialog
                dialog.add(calendar, BorderLayout.CENTER);

                // Thêm nút để xác nhận chọn ngày
                JButton okButton = new JButton("OK");
                okButton.addActionListener(event -> {
                    Date selectedDate = calendar.getDate();
                   
                    dialog.dispose(); // Đóng hộp thoại
                });

                dialog.add(okButton, BorderLayout.SOUTH);

                // Hiển thị JDialog
                dialog.setLocationRelativeTo(jPanel1); // Đặt vị trí của dialog
                dialog.setVisible(true);
            }
        });
        cmd3.add(btnDateTo);
        //---------------------------------//
        btnAdd = new ButtonCustom("Thêm", "");
        btnUpdate = new ButtonCustom("Cập nhật", "");
        btnAdd.setPreferredSize(new Dimension(100, 40));
        btnUpdate.setPreferredSize(new Dimension(100, 40));
        jPanel1.add(Box.createRigidArea(new Dimension(40, 0)));
        jPanel1.add(btnAdd);
        jPanel1.add(Box.createRigidArea(new Dimension(100, 0)));
        jPanel1.add(btnUpdate);
        //---------------------------------//
        JLabel jLable8 = new JLabel("Tình trạng");
        jLable8.setPreferredSize(new Dimension(100, 40));
        jLable8.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel jLable9 = new JLabel("Tình trạng");
        jLable9.setPreferredSize(new Dimension(60, 40));
        jLable9.setHorizontalAlignment(SwingConstants.RIGHT);
        comboBoxTTKM = new ComboBoxSuggestion();
        comboBoxTTKM.addItem("Đang áp dụng");
        comboBoxTTKM.addItem("Đã ngưng");
        comboBoxTTKM.addItem("Tất cả");
        comboBoxTTCTKM = new ComboBoxSuggestion();
        comboBoxTTCTKM.addItem("Đã áp dụng");
        comboBoxTTCTKM.addItem("Chưa áp dụng");
        comboBoxTTCTKM.addItem("Tất cả");
        comboBoxTTKM.setPreferredSize(new Dimension(200, 40));
        comboBoxTTCTKM.setPreferredSize(new Dimension(200, 40));
        btnTimDis = new ButtonCustom("Tìm kiếm", "imgs\\img\\search.png");
        btnTimPro = new ButtonCustom("Tìm kiếm", "imgs\\img\\search.png");
        jPanel5.add(btnTimDis);
        jPanel5.add(jLable8);
        jPanel5.add(comboBoxTTKM);
        pnlSpKm.add(btnTimPro);
        pnlSpKm.add(jLable9);
        pnlSpKm.add(comboBoxTTCTKM);
        jLabelLoai.setText("Loại");
        jLabelLoai.setPreferredSize(new java.awt.Dimension(120, 40));
        pnlSpKm.add(jLabelLoai);
        comboBoxLoai.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSpKm.add(comboBoxLoai);
         
        //----------------------------------------------------//
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(202, 212, 216)), "Thông tin khuyến mãi"
        );
        titledBorder1.setTitleColor(new Color(0, 0, 0));  // Đặt màu cho tiêu đề
        Border border1 = new CompoundBorder(
                titledBorder1,
                new EmptyBorder(30, 0, 0, 0)
        );
        jPanel1.setBorder(border1);

        //----------------------------------------------------//
         jCheckBox = new JCheckBox("Áp dụng tất cả");
        jCheckBox.setOpaque(false);
        jCheckBox.setBorderPainted(false);
        jCheckBox.setFocusPainted(false);
        pnlSpKm.add(jCheckBox);
        //----------------------------------------------------//
        TitledBorder titledBorder3 = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(202, 212, 216)), "Danh sách khuyến mãi"
        );
        titledBorder3.setTitleColor(new Color(0, 0, 0));  // Đặt màu cho tiêu đề
        Border border4 = new CompoundBorder(
                titledBorder3,
                new EmptyBorder(10, 10, 10, 10)
        );
        jPanel3.setBorder(border4);
        tableKM.ScrollPaneTable(jScrollPane1);
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 216), 2));
        //----------------------------------------------------//
        TitledBorder titledBorder4 = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(202, 212, 216)), "Danh sách những sản phẩm khuyễn mãi"
        );
        titledBorder4.setTitleColor(new Color(0, 0, 0));  // Đặt màu cho tiêu đề
        Border border5 = new CompoundBorder(
                titledBorder4,
                new EmptyBorder(10, 10, 10, 10)
        );
        jPanel4.setBorder(border5);
        btnSave = new ButtonCustom("Lưu", "");
        btnSave.setPreferredSize(new Dimension(100, 40));
        pnlSpKm.add(btnSave);
        
        tableCTKM.ScrollPaneTable(jScrollPane2);
        jScrollPane2.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 216), 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        // Khởi tạo JFrame
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormPromotion card = new FormPromotion();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hiển thị frame trong EventQueue
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cmd0;
    private javax.swing.JPanel cmd1;
    private javax.swing.JPanel cmd2;
    private javax.swing.JPanel cmd3;
    private javax.swing.JPanel cmd4;
    private javax.swing.JPanel cmd5;
    private javax.swing.JPanel cmd6;
    private javax.swing.JPanel cmd7;
    private gui.combobox.ComboBoxSuggestion comBoBoxNhapTT;
    private gui.combobox.ComboBoxSuggestion comboBoxSuggestion2;
    private gui.combobox.ComboBoxSuggestion comboBoxLoai;
    private gui.combobox.ComboBoxSuggestion comboBoxLoaiKM;
    private javax.swing.JLabel jLabel0;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelLoai;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNhapMa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtxtMoTa;
    private javax.swing.JPanel pnlSpKm;
    private gui.table.TableCustom tableKM;
    private gui.table.TableCustom tableCTKM;
    private gui.textfield.TextPay txtTenKM;
    private gui.textfield.TextPay txtmaKM;
    private TextFieldCustom txtTimMaSanPham;
    private TextFieldCustom txtTimKM;
    private gui.textfield.TextPay txtNgayBD;
    private gui.textfield.TextPay txtNgayKT;
    private gui.textfield.TextPay txtGiaTriKM;
    private gui.textfield.TextPay txtSoTienGiamToiDa;
 
    
    // End of variables declaration//GEN-END:variables

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o=e.getSource();
//        if(o.equals(btnUpdate)){
//          
//            entity.KhuyenMai km= getLayDLTuForm();
//            if(km_dao.update(km)){
//                try {
//                    JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công" , "Thông báo", JOptionPane.ERROR_MESSAGE);
//                    DocDuLieuDatabaseVaoTable();
//                } catch (Exception ex) {
//                    Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }else{
//                JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        if(o.equals(btnAdd)){
//           
//            entity.KhuyenMai km= getLayDLTuForm();
//            String ma=null;
//            try {
//                ma = km_dao.createMa();
//            } catch (SQLException ex) {
//                Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if(km.getTenKhuyenMai().trim().isEmpty()){
//                JOptionPane.showMessageDialog(null, "Bạn cần nhập tên khuyến mãi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            }else{
//                if(!km.isTrangThai()){
//                    System.out.println(km.isTrangThai());
//                    JOptionPane.showMessageDialog(null, "Trạng thái khi tạo phải là 'Đang áp dụng' ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                }else{
//                    if(km.getGiaTriKhuyenMai()<=0||km.getGiaTriKhuyenMai()>=0.7){
//                        JOptionPane.showMessageDialog(null, "Giá trị khuyến mãi phải lớn hơn 0 và bé hơn hoặc bằng 0.7(70%)", "Thông báo", HEIGHT);
//                    }else{
//                        if(km.getSoTienGiamToiDa()<=1000){
//                            JOptionPane.showMessageDialog(null, "Tiền giảm tối đa phải lớn hơn 1.000 vnd", "Thông báo", HEIGHT);
//                        }else{
//                            entity.KhuyenMai kmnew= new entity.KhuyenMai(ma,km.getTenKhuyenMai(), km.getThoiGianBatDau(), km.getThoiGianKetThuc(), km.isTrangThai(), km.getGiaTriKhuyenMai(), km.getMoTa(), km.getSoTienGiamToiDa(), km.getLoaiKM());
//                            if(!km_dao.create(kmnew)){
//                                        JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                            }else{
//                                try {
//                                    JOptionPane.showMessageDialog(this, "Thêm thành công" , "Thông báo", JOptionPane.ERROR_MESSAGE);
//                                    DocDuLieuDatabaseVaoTable();
//                                } catch (Exception ex) {
//                                    Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if(o.equals(comboBoxTTKM)){
//            
//            String selectedItem = (String) comboBoxTTKM.getSelectedItem();
//            if(selectedItem.equals("Tất cả")){
//                try {
//                    DocDuLieuDatabaseVaoTable();
//                } catch (Exception ex) {
//                    Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }else{
//                try {
//                    DocDuLieuDatabaseVaoTableTT(selectedItem);
//                } catch (Exception ex) {
//                    Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            
//        }
//        if(o.equals(comboBoxLoai)||o.equals(comboBoxTTCTKM)){
//           
//            String cbbLoai =( String) comboBoxLoai.getSelectedItem();
//            String cbbTTCTKM =( String) comboBoxTTCTKM.getSelectedItem();
//            if(ma!=null){
//                if(cbbLoai.equals("Tất cả")&&cbbTTCTKM.equals("Đã áp dụng")){
//                    try {
//                     
//                        DocDuLieuDatabaseVaoTableCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Thuốc")&&cbbTTCTKM.equals("Chưa áp dụng")){
//                    try {
//                        ThemThuocKhongKMVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Thiết bị y tế")&&cbbTTCTKM.equals("Chưa áp dụng")){
//                    try {
//                        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//                        model.setRowCount(0);
//                        ThemTBKhongKMVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Tất cả")&&cbbTTCTKM.equals("Chưa áp dụng")){
//                    try {
//                        ThemThuocKhongKMVaoCT(ma, phanTram, max);
//                        ThemTBKhongKMVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Thuốc")&&cbbTTCTKM.equals("Đã áp dụng")){
//                    try {
//                        ThemThuocKMVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Thiết bị y tế")&&cbbTTCTKM.equals("Đã áp dụng")){
//                    try {
//                        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//                        model.setRowCount(0);
//                        ThemTBKMVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
////                if(cbbLoai.equals("Tất cả")&&cbbTTCTKM.equals("Đã áp dụng")){
////                    try {
////                        ThemThuocKMVaoCT(ma, phanTram, max);
////                        ThemTBKMVaoCT(ma, phanTram, max);
////                    } catch (Exception ex) {
////                        Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                }
//                if(cbbLoai.equals("Thuốc")&&cbbTTCTKM.equals("Tất cả")){
//                    try {
//                        ThemALLThuocVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Thiết bị y tế")&&cbbTTCTKM.equals("Tất cả")){
//                    try {
//                        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//                        model.setRowCount(0);
//                        ThemALLTBVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                if(cbbLoai.equals("Tất cả")&&cbbTTCTKM.equals("Tất cả")){
//                    try {
//                        System.out.println(ma);
//                        ThemALLThuocVaoCT(ma, phanTram, max);
//                        
//                        ThemALLTBVaoCT(ma, phanTram, max);
//                    } catch (Exception ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }
//        if(o.equals(jCheckBox)){
//            boolean selected = jCheckBox.isSelected();
//		        
//		        // Lặp qua mọi hàng trong bảng
//		        for (int row = 0; row < tableCTKM.getRowCount(); row++) {
//		            // Đặt giá trị của cột "Đã áp dụng" tương ứng
//		            tableCTKM.setValueAt(selected, row, 3);
//		        }
//        }
//        if (o.equals(btnSave)) {
//            // Duyệt từng dòng của bảng tableTTCTKM
//            DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//            int rowCount = model.getRowCount();
//
//            for (int i = 0; i < rowCount; i++) {
//                // Lấy giá trị ở cột đầu tiên
//                boolean coKhuyenMai = Boolean.parseBoolean(model.getValueAt(i, 3).toString());
//                String maT = model.getValueAt(i, 0).toString();
//                int sl = 0; // Khởi tạo biến
//                Object valueAtColumn4 = model.getValueAt(i, 2); // Cột 4 có chỉ số 3
//
//                if (valueAtColumn4 != null) {
//                    // Chuyển đổi giá trị sang int nếu không null
//                    sl = Integer.parseInt(valueAtColumn4.toString());
//                }
//                if(coKhuyenMai){
//                    // Kiểm tra nếu mã bắt đầu bằng "T"
//                    entity.ChiTietKhuyenMai ctkm = null;
//                    if (maT.matches("^TBYT.*")) {
//                        // Nếu bắt đầu bằng "TBYT", tạo đối tượng ThietBiYTe
//                        entity.ThietBiYTe thietBiYTe = new entity.ThietBiYTe(maT);
//                        ctkm= new ChiTietKhuyenMai(sl, null, thietBiYTe);
//                    }else{
//                        // Nếu bắt đầu bằng "T", tạo đối tượng Thuoc
//                        entity.Thuoc thuoc = new Thuoc(maT);
//                        ctkm= new ChiTietKhuyenMai(sl, thuoc, null); 
//                    }
//                    try {
//                  //      System.out.println(ctkm.getSoLuong());
//                        if(ctkm_dao.create(ctkm, ma)){
//                         //   System.out.println("cap nhat thành công " +maT);
//                        }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }else{
//                   try {
//                       if(ctkm_dao.deleteCT(ma, maT)){
//                           // System.out.println("Xóa thành công " +maT);
//                        }
//                        
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }
//        if(o.equals(btnTimDis)){
//            //System.out.println(txtTimKM.getText());
//            if (txtTimKM.getText().equals("Nhập nội dung tìm kiếm...")) {
//                    try {
//                        DocDuLieuDatabaseVaoTable();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                } else {
//                    try {
//                        // Tìm khách hàng theo mã
//                        TimTheoMa(txtTimKM.getText());
//                    } catch (Exception ex) {
//                        ex.printStackTrace(); // In ra lỗi nếu có
//
//                    }
//              }
//        }
//        if(o.equals(btnTimPro)){
//            //System.out.println(txtTimKM.getText());
//            if (txtTimMaSanPham.getText().equals("Nhập nội dung tìm kiếm...")) {
//                    try {
//                        DocDuLieuDatabaseVaoTable();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                } else {
//                    try {
//                        // Tìm khách hàng theo mã
//                    } catch (Exception ex) {
//                        ex.printStackTrace(); // In ra lỗi nếu có
//
//                    }
//              }
//        }
//        
//    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        Object o=e.getSource();
//        if(o.equals(tableKM)){
//            int row = tableKM.getSelectedRow();
//            if (row != -1) { // Kiểm tra xem có hàng nào được chọn không
//                // Lấy dữ liệu từ hàng đã chọn
//                
//                String maKhuyenMai = tableKM.getValueAt(row, 0).toString();
//                String tenKhuyenMai = tableKM.getValueAt(row, 1).toString();
//                String maLoaiKhuyenMai = tableKM.getValueAt(row, 2).toString();
//                LoaiKhuyenMai loaiKhuyenMai = new LoaiKhuyenMai(maLoaiKhuyenMai);
//                // Lấy dữ liệu thời gian
//                LocalDateTime thoiGianBatDau = null;
//                LocalDateTime thoiGianKetThuc = null;
//
//                Object thoiGianBatDauObj = tableKM.getValueAt(row, 3);
//                if (thoiGianBatDauObj instanceof java.sql.Timestamp) {
//                    thoiGianBatDau = ((java.sql.Timestamp) thoiGianBatDauObj).toLocalDateTime();
//                } else if (thoiGianBatDauObj instanceof LocalDateTime) {
//                    thoiGianBatDau = (LocalDateTime) thoiGianBatDauObj;
//                }
//
//                Object thoiGianKetThucObj = tableKM.getValueAt(row, 4);
//                if (thoiGianKetThucObj instanceof java.sql.Timestamp) {
//                    thoiGianKetThuc = ((java.sql.Timestamp) thoiGianKetThucObj).toLocalDateTime();
//                } else if (thoiGianKetThucObj instanceof LocalDateTime) {
//                    thoiGianKetThuc = (LocalDateTime) thoiGianKetThucObj;
//                }
//
//                double giaTriKhuyenMai = Double.parseDouble(tableKM.getValueAt(row, 5).toString());
//                double soTienGiamToiDa = Double.parseDouble(tableKM.getValueAt(row,6).toString());
//                boolean trangThai = Boolean.parseBoolean(tableKM.getValueAt(row, 7).toString());
//                String moTa = tableKM.getValueAt(row, 8).toString();
//                
//                ma=maKhuyenMai;
//                phanTram=giaTriKhuyenMai;
//                max=soTienGiamToiDa;
//                txtmaKM.setText(maKhuyenMai);
//                txtTenKM.setText(tenKhuyenMai);
//                txtNgayBD.setText(thoiGianBatDau != null ? thoiGianBatDau.toString() : ""); // Kiểm tra null
//                txtNgayKT.setText(thoiGianKetThuc != null ? thoiGianKetThuc.toString() : ""); // Kiểm tra null
//                
//                if(trangThai){
//                    comBoBoxNhapTT.setSelectedItem("Đang áp dụng");
//                }else{
//                     comBoBoxNhapTT.setSelectedItem("Ngưng áp dụng");
//                }
//                txtGiaTriKM.setText(String.valueOf(giaTriKhuyenMai));
//                jtxtMoTa.setText(moTa);
//                txtSoTienGiamToiDa.setText(String.valueOf(soTienGiamToiDa));
//                comboBoxLoaiKM.setSelectedItem(maLoaiKhuyenMai);
//                
//                DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//                model.setRowCount(0);
//                tableCTKM.repaint();
//                //System.out.println(model.getRowCount());
//                try {
//                    DocDuLieuDatabaseVaoTableCT(maKhuyenMai, giaTriKhuyenMai, soTienGiamToiDa);
//                } catch (Exception ex) {
//                    Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }else if(o.equals(tableCTKM)){
//            
//        }
//    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
//    public void DocDuLieuDatabaseVaoTable() throws Exception {
//       
//        ArrayList<entity.KhuyenMai> list = km_dao.getAll();
//        DefaultTableModel model = (DefaultTableModel) tableKM.getModel();
//        SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableKM.revalidate(); // Cập nhật JTable
//                    tableKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        model.setRowCount(0);
//        for (entity.KhuyenMai km : list) {     
//            model.addRow(new Object[]{
//               km.getMaKhuyenMai(), km.getTenKhuyenMai(),km.getLoaiKM().getTenLoai(), km.getThoiGianBatDau(),km.getThoiGianKetThuc(), km.getGiaTriKhuyenMai(),km.getSoTienGiamToiDa(),km.isTrangThai()?"Đang áp dụng":"Đã ngưng", km.getMoTa()
//            });
//        }
//    }
//    public void TimTheoMa(String maKM) throws Exception {
//       
//        ArrayList<entity.KhuyenMai> list = km_dao.timKMTheoMa(maKM);
//        if(list.size()<1){
//            JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi nào chữa mã "+maKM, "Thông báo",JOptionPane.INFORMATION_MESSAGE );
//        }
//        DefaultTableModel model = (DefaultTableModel) tableKM.getModel();
//        SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableKM.revalidate(); // Cập nhật JTable
//                    tableKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        model.setRowCount(0);
//        for (entity.KhuyenMai km : list) {
//            
//            model.addRow(new Object[]{
//               km.getMaKhuyenMai(), km.getTenKhuyenMai(),km.getLoaiKM().getTenLoai(), km.getThoiGianBatDau(),km.getThoiGianKetThuc(), km.getGiaTriKhuyenMai(),km.getSoTienGiamToiDa(),km.isTrangThai()?"Đang áp dụng":"Đã ngưng", km.getMoTa()
//            });
//        }
//    }
//    public void TimSP(String maSP) throws Exception {
//        TimThuoc(ma, phanTram, max, maSP);
//        TimTB(ma, phanTram, max, maSP);
//        if(tableCTKM.getRowCount()<1){
//            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có mã "+maSP, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//    public void DocDuLieuDatabaseVaoTableTT(String x) throws Exception {
//        ArrayList<entity.KhuyenMai> list = km_dao.getAlltheoTT(x);
//        DefaultTableModel model = (DefaultTableModel) tableKM.getModel();
//        SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableKM.revalidate(); // Cập nhật JTable
//                    tableKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        model.setRowCount(0);
//        for (entity.KhuyenMai km : list) {
//           
//            model.addRow(new Object[]{
//               km.getMaKhuyenMai(), km.getTenKhuyenMai(),km.getLoaiKM().getTenLoai(), km.getThoiGianBatDau(),km.getThoiGianKetThuc(), km.getGiaTriKhuyenMai(),km.getSoTienGiamToiDa(),km.isTrangThai()?"Đang áp dụng":"Đã ngưng", km.getMoTa()
//            });
//        }
//        model.fireTableDataChanged();
//        
//    }
//    
//    public void DocDuLieuDatabaseVaoTableCT(String ma,double phanTram, double max) throws Exception {
//       
//        ArrayList<entity.ChiTietKhuyenMai> list = ctkm_dao.getChiTietKhuyenMaiTheoMaKM(ma);
//        
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        model.setRowCount(0);   
//        for (entity.ChiTietKhuyenMai ctkm : list) {
//           
//            String maSP,tenSP;
//
//            if(ctkm.getThuoc().getMaThuoc()==null){
//                maSP=ctkm.getThietBiYTe().getMaThietBi();
//                tenSP=ctkm.getThietBiYTe().getTenThietBi();
//
//                
//            }else {
//                maSP=ctkm.getThuoc().getMaThuoc();
//                tenSP=ctkm.getThuoc().getTenThuoc();
//            }
//           
//            model.addRow(new Object[]{
//               maSP,tenSP,ctkm.getSoLuong(),true
//            });
//        }
//    }
//    public void ThemThuocKMVaoCT(String ma,double phanTram, double max) throws Exception {
//        ArrayList<entity.ChiTietKhuyenMai> list = ctkm_dao.getChiTietKhuyenMaiTheoMaKM(ma);
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        model.setRowCount(0);
//   //     System.out.println(list.size());
//        for (entity.ChiTietKhuyenMai ctkm : list) {
//            String maSP,tenSP;
//            if(ctkm.getThuoc().getMaThuoc()==null){
//            }else {
//                maSP=ctkm.getThuoc().getMaThuoc();
//                tenSP=ctkm.getThuoc().getTenThuoc();
//     //           System.out.println(maSP+tenSP+ctkm.getSoLuong());
//                model.addRow(new Object[]{
//                    maSP,tenSP,ctkm.getSoLuong(),true
//                 });
//            }
//        }
//    }
//    public void ThemTBKMVaoCT(String ma,double phanTram, double max) throws Exception {
//        ArrayList<entity.ChiTietKhuyenMai> list = ctkm_dao.getChiTietKhuyenMaiTheoMaKM(ma);
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        model.setRowCount(0);
//        for (entity.ChiTietKhuyenMai ctkm : list) {
//            String maSP,tenSP;
//            if(ctkm.getThuoc().getMaThuoc()==null){
//                maSP=ctkm.getThietBiYTe().getMaThietBi();
//                tenSP=ctkm.getThietBiYTe().getTenThietBi();
//                model.addRow(new Object[]{
//                    maSP,tenSP,ctkm.getSoLuong(),true
//                 });
//            }
//        }
//    }
//    public void ThemThuocKhongKMVaoCT(String ma,double phanTram, double max) throws Exception {
//       
//        ArrayList<entity.Thuoc> list = thuoc_dao.getThuocKhongApDungKm(ma);
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        model.setRowCount(0);
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        for (entity.Thuoc t : list) {
//           
//            String maSP,tenSP;
//            
//                maSP=t.getMaThuoc();
//                tenSP=t.getTenThuoc();
//               
//            model.addRow(new Object[]{
//               maSP,tenSP,10,false
//            });
//        }
//    }
//    public void ThemTBKhongKMVaoCT(String ma,double phanTram, double max) throws Exception {
//       
//        ArrayList<entity.ThietBiYTe> list =tb_dao.getTBYTKhongApDungKM(ma);
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        
//        for (entity.ThietBiYTe t : list) {
//           
//            String maSP,tenSP;
//                maSP=t.getMaThietBi();
//                tenSP=t.getTenThietBi();
//                
//            model.addRow(new Object[]{
//               maSP,tenSP,10,false
//            });
//        }
//    }
//    public void ThemALLThuocVaoCT(String maKM, double phanTram, double max) throws Exception {
//        // Lấy danh sách thuốc từ DAO và khuyến mãi
//        HashMap<entity.Thuoc, Integer> list = thuoc_dao.getAllThuocKTKM(maKM);
//       
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        model.setRowCount(0);
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        // Duyệt qua từng thuốc trong HashMap
//        for (Map.Entry<entity.Thuoc, Integer> entry : list.entrySet()) {
//     //      System.out.println(entry);
//            entity.Thuoc t = entry.getKey();
//            int sl = entry.getValue();  // Giá trị boolean cho biết thuốc có khuyến mãi hay không
//
//            String maSP = t.getMaThuoc();
//            String tenSP = t.getTenThuoc();
//            boolean kt=true;
//            if(sl==-1){
//                kt=false;
//                sl=10;
//            }
//            // Thêm dòng vào bảng
//            model.addRow(new Object[]{
//                maSP, tenSP, sl, kt
//            });
//        }
//    }
//    public void TimThuoc(String maKM, double phanTram, double max, String maT) throws Exception {
//        // Lấy danh sách thuốc từ DAO và khuyến mãi
//        HashMap<entity.Thuoc, Integer> list = thuoc_dao.getTimThuocKMTheoMa(maKM, maT);
//       
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        model.setRowCount(0);
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        // Duyệt qua từng thuốc trong HashMap
//        for (Map.Entry<entity.Thuoc, Integer> entry : list.entrySet()) {
//             System.out.println(entry);
//            entity.Thuoc t = entry.getKey();
//            int sl = entry.getValue();  // Giá trị boolean cho biết thuốc có khuyến mãi hay không
//
//            String maSP = t.getMaThuoc();
//            String tenSP = t.getTenThuoc();
//            boolean kt=true;
//            if(sl==-1){
//                kt=false;
//                sl=10;
//            }
//            // Thêm dòng vào bảng
//            model.addRow(new Object[]{
//                maSP, tenSP, sl, kt
//            });
//        }
//    }
//    public void ThemALLTBVaoCT(String maKM, double phanTram, double max) throws Exception {
//        // Lấy danh sách thuốc từ DAO và khuyến mãi
//        HashMap<entity.ThietBiYTe, Integer> list = tb_dao.getAllTBKTKM(maKM);
//        
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        //model.setRowCount(0);
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        // Duyệt qua từng thuốc trong HashMap
//        for (Map.Entry<entity.ThietBiYTe, Integer> entry : list.entrySet()) {
//            System.out.println(entry);
//            entity.ThietBiYTe t = entry.getKey();
//            int sl = entry.getValue();  // Giá trị boolean cho biết thuốc có khuyến mãi hay không
//
//            String maSP = t.getMaThietBi();
//            String tenSP = t.getTenThietBi();
//
//            boolean kt=true;
//            if(sl==-1){
//                kt=false;
//                sl=10;
//            }
//            // Thêm dòng vào bảng
//            model.addRow(new Object[]{
//                maSP, tenSP, sl, kt
//            });
//        }
//    }
//    public void TimTB(String maKM, double phanTram, double max, String maTB) throws Exception {
//        // Lấy danh sách thuốc từ DAO và khuyến mãi
//        HashMap<entity.ThietBiYTe, Integer> list = tb_dao.getTimTBKTKMTheoMa(maKM,maTB);
//        
//        DefaultTableModel model = (DefaultTableModel) tableCTKM.getModel();
//        //model.setRowCount(0);
//         SwingUtilities.invokeLater(() -> {
//                    model.fireTableDataChanged();
//                    tableCTKM.revalidate(); // Cập nhật JTable
//                    tableCTKM.repaint(); // Vẽ lại JTable
//                    JScrollPane scrollPane = (JScrollPane) tableCTKM.getParent().getParent(); // Lấy JScrollPane
//                    scrollPane.revalidate(); // Cập nhật JScrollPane
//                    scrollPane.repaint(); // Vẽ lại JScrollPane
//                });
//        // Duyệt qua từng thuốc trong HashMap
//        for (Map.Entry<entity.ThietBiYTe, Integer> entry : list.entrySet()) {
//            System.out.println(entry);
//            entity.ThietBiYTe t = entry.getKey();
//            int sl = entry.getValue();  // Giá trị boolean cho biết thuốc có khuyến mãi hay không
//
//            String maSP = t.getMaThietBi();
//            String tenSP = t.getTenThietBi();
//
//            boolean kt=true;
//            if(sl==-1){
//                kt=false;
//                sl=10;
//            }
//            // Thêm dòng vào bảng
//            model.addRow(new Object[]{
//                maSP, tenSP, sl, kt
//            });
//        }
//    }
//    public void DocDuLieuVaoCBBLoai() throws Exception {
//       
//        ArrayList<entity.LoaiKhuyenMai> list = lkm_dao.getAllLoaiKhuyenMai();
//        for (entity.LoaiKhuyenMai lkm : list) {
//            comboBoxLoaiKM.addItem(lkm.getTenLoai());
//        }
//    }
//    public entity.KhuyenMai getLayDLTuForm() {
//        
//        // Lấy dữ liệu từ các ô nhập liệu
//        String tenKhuyenMai = txtTenKM.getText().trim(); // Tên khuyến mãi
//        LocalDateTime thoiGianBatDau = null; // Ngày bắt đầu
//        LocalDateTime thoiGianKetThuc = null; // Ngày kết thúc
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // Định dạng cho LocalDateTime
//
//        // Chuyển đổi chuỗi ngày sang LocalDateTime nếu có giá trị
//        if (!txtNgayBD.getText().isEmpty()) {
//            try {
//                thoiGianBatDau = LocalDateTime.parse(txtNgayBD.getText(), formatter);
//            } catch (DateTimeParseException e) {
//                e.printStackTrace();
//                // Thông báo lỗi cho người dùng hoặc xử lý lỗi
//            }
//        }
//        if (!txtNgayKT.getText().isEmpty()) {
//            try {
//                thoiGianKetThuc = LocalDateTime.parse(txtNgayKT.getText(), formatter);
//            } catch (DateTimeParseException e) {
//                e.printStackTrace();
//                // Thông báo lỗi cho người dùng hoặc xử lý lỗi
//            }
//        }
//
//        // Trạng thái khuyến mãi
//        boolean trangThai = comBoBoxNhapTT.getSelectedItem().equals("Đang áp dụng");
//        System.out.println(trangThai);
// 
//        // Giá trị khuyến mãi
//        double giaTriKhuyenMai = 0; // Khởi tạo với giá trị mặc định
//        String giaTriKhuyenMaiText = txtGiaTriKM.getText().trim();
//        if (!giaTriKhuyenMaiText.isEmpty()) {
//            giaTriKhuyenMai = Double.parseDouble(giaTriKhuyenMaiText);
//        }
//
//        // Mô tả khuyến mãi
//        String moTa = jtxtMoTa.getText().trim();
//        
//        // Số tiền giảm tối đa
//        double soTienGiamToiDa = 0; // Khởi tạo với giá trị mặc định
//        String soTienGiamToiDaText = txtSoTienGiamToiDa.getText().trim();
//        if (!soTienGiamToiDaText.isEmpty()) {
//            soTienGiamToiDa = Double.parseDouble(soTienGiamToiDaText);
//        }
//
//
//        // Mã loại khuyến mãi
//        String maLoaiKhuyenMai = (String) comboBoxLoaiKM.getSelectedItem();
//        LoaiKhuyenMai lkm=null;
//        try {
//            lkm= lkm_dao.getTimLoaiKhuyenMaiTheoTen(maLoaiKhuyenMai);
//        } catch (Exception ex) {
//            Logger.getLogger(FormPromotion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       String maKhuyenMai = txtmaKM.getText().trim();
//        entity.KhuyenMai km= new entity.KhuyenMai(maKhuyenMai, tenKhuyenMai, thoiGianBatDau, thoiGianKetThuc, trangThai, giaTriKhuyenMai, moTa, soTienGiamToiDa, lkm);
//        return km;
//    }
//    public HashMap<ThietBiYTe, Integer> getAllTBKTKM(String maKM) throws SQLException {
//            HashMap<ThietBiYTe, Integer> dstb = new HashMap<>();
//            Connection con = ConnectDB.getInstance().getConnect();
//            PreparedStatement stmt = null;
//
//            try {
//                // Sửa câu lệnh SQL
//                String sql = "SELECT t.maThietBiYTe, t.TenThietBi, t.GiaBan,ct.SoLuong, "
//                           + "CASE WHEN ct.MaKhuyenMai = ? THEN 1 ELSE 0 END AS CoKhuyenMai "
//                           + "FROM ThietBiYTe t "
//                           + "LEFT JOIN ChiTietKhuyenMai ct ON t.MaThietBiYTe = ct.MaThietBiYTe";
//
//                stmt = con.prepareStatement(sql);
//                stmt.setString(1, maKM);
//                ResultSet rs = stmt.executeQuery();
//
//                while (rs.next()) {
//                String maTb = rs.getString("MaThietBiYTe");
//                String tenTb = rs.getString("TenThietBi");
//                double giaBan = rs.getDouble("GiaBan");
//                boolean kt = rs.getBoolean("CoKhuyenMai");
//                int sl = rs.getInt("SoLuong");
//                if (rs.wasNull()) {  // Nếu giá trị là NULL, rs.wasNull() sẽ trả về true
//                    sl = -1;  // Đặt sl thành -1 nếu SoLuong là NULL
//                }
//                // In ra thông tin
//                
//
//                ThietBiYTe tb = new ThietBiYTe(maTb, tenTb, giaBan);
//                dstb.put(tb, sl);
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (stmt != null) {
//                        stmt.close();
//                    }
//                    if (con != null) {
//                        con.close();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            return dstb;
//        }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
