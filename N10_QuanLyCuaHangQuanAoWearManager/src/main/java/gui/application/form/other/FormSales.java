package gui.application.form.other;

import bus.*;
import dto.*;
import gui.button.ButtonCustom;
import gui.button.NavButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.table.TableProductCellRender;
import gui.table.TableRemoveCellRender;
import gui.textfield.TextPay;

import java.awt.*;

import static java.awt.Frame.MAXIMIZED_BOTH;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FormSales extends JPanel implements ActionListener, MouseListener {

    private ButtonCustom btnAdd;
    private ButtonCustom btnSearch;
    private ButtonCustom btnSearch1;
    private NavButtonCustom btnAddItem;
    private TextPay txtTongTien;
    private TextPay txtThanhTien;
    private DefaultTableModel tableModel;
    private DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
    private ComboBoxSuggestion comboxDVT;
    private ButtonCustom btnThanhToan;
    private OrderBUS orderBUS;
    private OrderDetailBUS orderDetailBUS;
    private EmployeeBUS employeeBUS;
    private CustomerBUS customerBUS;
    private ProductBUS productBUS;

    public FormSales(Employee employee) {
        initComponents();
        init(employee);
    }

    private void init(Employee employee) {
        txtMaNV.setText(String.valueOf(employee.getId()));
        txtTenNV.setText(employee.getFullName());
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
//        pnlHoaDon.add(txtMaHD);
//        pnlHoaDon.add(new JLabel("Đơn vị tính"));
//        comboxDVT = new ComboBoxSuggestion();
//        comboxDVT.setPreferredSize(new Dimension(100, 40));
//        pnlHoaDon.add(comboxDVT);
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
//        txtMaHD.setEditable(false);
//        txtMaHD.setFocusable(false);
        txtDTLMoi.setText("0");
        txtDTL.setText("0");
        txtThanhTien = pay1.getTxtThanhTien();
        txtThanhTien.setText(txtTTHD.getText());
        tableOrder.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = new JLabel();

                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                    label.setText(""); // Không có chữ
                } else if (value != null) {
                    label.setText(value.toString());
                }

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setOpaque(true);
                }
                return label;
            }
        });

        tableOrder.getColumnModel().getColumn(5).setCellRenderer(new TableRemoveCellRender(tableOrder));
        tableModel = (DefaultTableModel) tableOrder.getModel();
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

        pay1.getBtnMoney1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay1.getTxtKhachDua().setText("50,000 VNĐ");

                String tienKhachDua = pay1.getTxtKhachDua().getText().replace(" VNĐ", "").replace(",", "");

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

        try {
            orderBUS = new OrderBUS();
            orderDetailBUS = new OrderDetailBUS();
            employeeBUS = new EmployeeBUS();
            customerBUS = new CustomerBUS();
            productBUS = new ProductBUS();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void sumAmount() {
        int total = 0;
        for (int i = 0; i < tableOrder.getRowCount(); i++) {
            String value = tableOrder.getValueAt(i, 4).toString();

            value = value.replaceAll("[^0-9]", "");
            try {
                total += Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing value: " + value);
            }
        }
        txtTTHD.setText(df.format(total));
        pay1.getTxtTongTien().setText(df.format(total));
        pay1.getTxtThanhTien().setText(df.format(total));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(206, 212, 218));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 18, 18);
        super.paintComponent(g);
    }

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

//        lblMaHD.setText("Mã hóa đơn");
//        lblMaHD.setPreferredSize(new java.awt.Dimension(90, 40));

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
                "Ảnh", "Tên sản phẩm", "Giá bán", "Số lượng", "Tổng giá tiền", "Xóa"
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

        tabHoaDon.addTab("Hóa đơn", pnlOrder);

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

        txtTenKH.setEditable(true);

        pnlTop.add(pnlHoaDon, java.awt.BorderLayout.PAGE_END);

        add(pnlTop, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        EmployeeBUS employeeBUS1 = new EmployeeBUS();
        Employee employee = employeeBUS1.getEmployeeById(2);
        FormSales banHang = new FormSales(employee);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnSearch)){
            timKiemSanPham();
        }
        if(o.equals(btnSearch1)){
            timKiemKhachHang();
        }
        if(o.equals(btnThanhToan)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Chờ ngẫu nhiên từ 10 đến 20 giây
                        int randomWaitTime = 1 + (int)(Math.random() * 3);  // Random từ 10 đến 20
                        System.out.println("Chờ " + randomWaitTime + " giây trước khi thanh toán");
                        Thread.sleep(randomWaitTime * 1000);  // Chuyển đổi giây thành mili giây

                        // Sau khi thời gian chờ kết thúc, thực hiện thanh toán
                        thanhToan();  // Gọi phương thức thanh toán trong thread

                        // Sau khi thanh toán xong, thực hiện hành động khác (nếu cần)
                        System.out.println("Thanh toán xong");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void thanhToan() {
        try {
            if(kiemTra()){
                if (tinhTienThua()) {
                    Order order = new Order();
                    order.setOrderDate(LocalDate.now());
                    order.setStatus(Status.COMPLETED);

                    List<Customer> customers = customerBUS.getCustomerByPhone(txtSDT.getText());
                    Customer customer = (customers != null && !customers.isEmpty()) ? customers.get(0) : null;

                    int point = Integer.parseInt( txtTTHD.getText().replaceAll("[^0-9]", ""));
                    if (customer != null) {
                        point += customer.getPoint();
                        customer.setPoint(point);
                        customerBUS.updateCustomer(customer);
                        customer.getOrders().add(order);
                    }else{
                        customer = new Customer();
                        customer.setName(txtTenKH.getText());
                        customer.setPhone(txtSDT.getText());
                        customerBUS.addCustomer(customer);
                    }

                    order.setCustomer(customer);

                    order.setEmployee(employeeBUS.getEmployeeById(Integer.parseInt(txtMaNV.getText())));

                    Set<OrderDetail> orderDetails = new HashSet<>();

                    for (int i = 0; i < tableOrder.getRowCount(); i++) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setQuantity(Integer.parseInt(tableOrder.getValueAt(i, 3).toString()));
                        orderDetail.setPrice(Double.parseDouble(tableOrder.getValueAt(i, 2).toString().replaceAll("[^0-9]", "")));
                        Product product = productBUS.getProductByName(tableOrder.getValueAt(i, 1).toString()).get(0);
                        int quality = product.getStockQuantity() - Integer.parseInt(tableOrder.getValueAt(i, 3).toString());
                        System.out.println("Quality: " + quality);
                        product.setStockQuantity(quality);
                        if(productBUS.updateProduct(product)){
                            System.out.println("Update thành công");
                        }
                        product = productBUS.getProductByName(tableOrder.getValueAt(i, 1).toString()).get(0);
                        System.out.println(product);
                        orderDetail.setProduct(product);
                        orderDetail.setOrder(order);
                        orderDetails.add(orderDetail);
                    }

                    order.setOrderDetails(orderDetails);

                    try {
                        orderBUS.addOrder(order);

                        JOptionPane.showMessageDialog(this, "Thanh toán thành công. Bạn có muốn tiếp tục?", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Thanh toán thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                    tableModel.setRowCount(0);
                    txtSDT.setText("");
                    txtTenKH.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình thanh toán.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean kiemTra() {
        if ((!txtSDT.getText().matches("0(1|3|5|7|9)\\d{8}") || txtSDT.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtSDT.requestFocus();
            return false;
        }

        String tienKhachDua = pay1.getTxtKhachDua().getText().replace(" VNĐ", "").replace(",", "");
        if (!tienKhachDua.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại số tiền thanh toán");
            pay1.getTxtKhachDua().requestFocus();
            return false;
        }
        return true;
    }

    private void timKiemSanPham() {
        try {
            String searchText = txtSearch.getText();
            ArrayList<Product> products = new ArrayList<>();

            if (searchText.matches("\\d+")) {
                Product product = productBUS.getProductById(Integer.parseInt(searchText));
                if (product != null) {
                    products.add(product);
                }
            }

            if (productBUS.getProductByName(searchText) != null) {
                products.add(productBUS.getProductByName(searchText).get(0));
            }

            products.stream().forEach(product -> {
                try {
                    boolean found = false;
                    int rowIndex = -1;
                    int currentQuantityInTable = 0;

                    // Kiểm tra sản phẩm đã có trong table chưa
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String productNameInTable = tableModel.getValueAt(i, 1).toString();
                        if (productNameInTable.equals(product.getProductName())) {
                            found = true;
                            rowIndex = i;
                            currentQuantityInTable = (int) tableModel.getValueAt(i, 3);
                            break;
                        }
                    }

                    int quantityInStock = product.getStockQuantity();

                    if (found) {
                        if (currentQuantityInTable + 1 > quantityInStock) {
                            JOptionPane.showMessageDialog(null, "Sản phẩm '" + product.getProductName() + "' đã hết hàng!");
                        } else {
                            int newQuantity = currentQuantityInTable + 1;
                            tableModel.setValueAt(newQuantity, rowIndex, 3);

                            double price = Double.parseDouble(
                                    tableModel.getValueAt(rowIndex, 4).toString().replaceAll("[^0-9]", "")
                            );
                            tableModel.setValueAt(df.format(price * newQuantity), rowIndex, 4);
                        }
                    } else {
                        if (quantityInStock <= 0) {
                            JOptionPane.showMessageDialog(null, "Sản phẩm '" + product.getProductName() + "' đã hết hàng!");
                        } else {
                            URL imageURL = getClass().getClassLoader().getResource(product.getImageUrl());
                            ImageIcon imageIcon = null;
                            if (imageURL != null) {
                                ImageIcon originalIcon = new ImageIcon(imageURL);
                                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                                imageIcon = new ImageIcon(scaledImage);
                            } else {
                                System.out.println("Hình ảnh không tìm thấy!");
                            }

                            tableModel.addRow(new Object[]{
                                    imageIcon,
                                    product.getProductName(),
                                    df.format(product.getPrice()),
                                    1,
                                    df.format(product.getPrice()),
                                    "Xóa"
                            });
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            SwingUtilities.invokeLater(() -> {
                tableModel.fireTableDataChanged();
                tableOrder.revalidate();
                tableOrder.repaint();
                JScrollPane scrollPane = (JScrollPane) tableOrder.getParent().getParent();
                scrollPane.revalidate();
                scrollPane.repaint();
            });

            sumAmount();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void timKiemKhachHang() {
        try {
            if ((!txtSDT.getText().matches("0(1|3|5|7|9)\\d{8}") || txtSDT.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số điện thoại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                txtSDT.requestFocus();
                return;
            }
            String searchText = txtSDT.getText();
            if (customerBUS.getCustomerByPhone(searchText) != null){
                Customer customer = customerBUS.getCustomerByPhone(searchText).get(0);
                if (customer != null) {
                    txtTenKH.setText(customer.getName());
                    txtDTL.setText(String.valueOf(customer.getPoint()));
                    pay1.getTxtDTL().setText(String.valueOf(customer.getPoint()));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với số điện thoại này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        sumAmount();
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
    // End of variables declaration//GEN-END:variables
}
