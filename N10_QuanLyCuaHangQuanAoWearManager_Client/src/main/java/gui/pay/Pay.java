package gui.pay;

import gui.button.ButtonCustom;
import gui.textfield.TextPay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pay extends JPanel {

    private ButtonCustom btnMoney1;
    private ButtonCustom btnMoney2;
    private ButtonCustom btnMoney3;
    private ButtonCustom btnMoney4;
    private ButtonCustom btnPay;
//    private ButtonCustom btnSaveTemp;
    private ButtonCustom point;
    private DefaultComboBoxModel<String> defaultComboBoxModel;

    public Pay() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTongTien = new gui.textfield.TextPay();
        cmd2 = new javax.swing.JPanel();
        txtDTL = new gui.textfield.TextPay();
        cmd3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtThanhTien = new gui.textfield.TextPay();
        cmd4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxPT = new gui.combobox.ComboBoxSuggestion();
        cmd5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtKhachDua = new gui.textfield.TextPay();
        cmd6 = new javax.swing.JPanel();
        cmd7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTienThua = new gui.textfield.TextPay();
        cmd8 = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(8, 1));

        cmd1.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Tổng tiền");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 16));
        cmd1.add(jLabel1, java.awt.BorderLayout.LINE_START);
        cmd1.add(txtTongTien, java.awt.BorderLayout.CENTER);

        add(cmd1);

        cmd2.setLayout(new java.awt.BorderLayout(5, 0));

        txtDTL.setText("0");
        cmd2.add(txtDTL, java.awt.BorderLayout.CENTER);

        add(cmd2);

        cmd3.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Thành tiền");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 16));
        cmd3.add(jLabel2, java.awt.BorderLayout.LINE_START);
        cmd3.add(txtThanhTien, java.awt.BorderLayout.CENTER);

        add(cmd3);

        cmd4.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Phương thức");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 16));
        cmd4.add(jLabel3, java.awt.BorderLayout.LINE_START);
        cmd4.add(comboBoxPT, java.awt.BorderLayout.CENTER);

        add(cmd4);

        cmd5.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Khách đưa");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 16));
        cmd5.add(jLabel4, java.awt.BorderLayout.LINE_START);
        cmd5.add(txtKhachDua, java.awt.BorderLayout.CENTER);

        add(cmd5);

        cmd6.setLayout(new java.awt.GridLayout(1, 0, 10, 0));
        add(cmd6);

        cmd7.setLayout(new java.awt.BorderLayout(5, 0));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tiền thừa");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 16));
        cmd7.add(jLabel5, java.awt.BorderLayout.LINE_START);
        cmd7.add(txtTienThua, java.awt.BorderLayout.CENTER);

        add(cmd7);

        cmd8.setLayout(new java.awt.GridLayout(1, 0, 50, 0));
        add(cmd8);
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        setOpaque(false);
        setBorder(new EmptyBorder(0, 5, 0, 5));
        cmd1.setOpaque(false);
        cmd1.setBorder(new EmptyBorder(5, 0, 5, 0));
        jLabel1.setOpaque(false);

        point = new ButtonCustom("Sử dụng điểm tích lũy", "");
        point.setPreferredSize(new Dimension(150, 40));
        cmd2.setOpaque(false);
        cmd2.setBorder(new EmptyBorder(5, 0, 5, 0));
        cmd2.add(point, BorderLayout.WEST);

        cmd3.setOpaque(false);
        cmd3.setBorder(new EmptyBorder(5, 0, 5, 0));

        cmd4.setOpaque(false);
        cmd4.setBorder(new EmptyBorder(5, 0, 5, 0));
        String[] pays = new String[]{"Tiền mặt", "Chuyển khoản"};
        defaultComboBoxModel = new DefaultComboBoxModel<>(pays);

        // Set model cho comboBoxSuggestion1
        comboBoxPT.setModel(defaultComboBoxModel);

        cmd5.setOpaque(false);
        cmd5.setBorder(new EmptyBorder(5, 0, 5, 0));

        btnMoney1 = new ButtonCustom("50K", "");
        btnMoney2 = new ButtonCustom("100K", "");
        btnMoney3 = new ButtonCustom("200K", "");
        btnMoney4 = new ButtonCustom("500K", "");
        cmd6.setOpaque(false);
        cmd6.setBorder(new EmptyBorder(5, 0, 5, 0));
        cmd6.add(btnMoney1);
        cmd6.add(btnMoney2);
        cmd6.add(btnMoney3);
        cmd6.add(btnMoney4);

        cmd7.setOpaque(false);
        cmd7.setBorder(new EmptyBorder(5, 0, 5, 0));

        btnPay = new ButtonCustom("Thanh toán", "");
//        btnSaveTemp = new ButtonCustom("Lưu tạm", "");
        cmd8.setOpaque(false);
        cmd8.setBorder(new EmptyBorder(5, 0, 5, 0));
//        cmd8.add(btnSaveTemp);
        cmd8.add(btnPay);

        txtTongTien.setEditable(false);
        txtTongTien.setFocusable(false);
        txtDTL.setEditable(false);
        txtDTL.setFocusable(false);
        txtThanhTien.setEditable(false);
        txtThanhTien.setFocusable(false);
        txtTienThua.setEditable(false);
        txtTienThua.setFocusable(false);
    }

    public ButtonCustom getBtnMoney1() {
        return btnMoney1;
    }

    public ButtonCustom getBtnMoney2() {
        return btnMoney2;
    }

    public ButtonCustom getBtnMoney3() {
        return btnMoney3;
    }

    public ButtonCustom getBtnMoney4() {
        return btnMoney4;
    }

    public ButtonCustom getBtnPay() {
        return btnPay;
    }

//    public ButtonCustom getBtnSaveTemp() {
//        return btnSaveTemp;
//    }

    public TextPay getTxtDTL() {
        return txtDTL;
    }

    public TextPay getTxtKhachDua() {
        return txtKhachDua;
    }

    public TextPay getTxtThanhTien() {
        return txtThanhTien;
    }

    public TextPay getTxtTienThua() {
        return txtTienThua;
    }

    public TextPay getTxtTongTien() {
        return txtTongTien;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 500);
        Pay t = new Pay();
        frame.add(t);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        // Vẽ viền
        g2.setColor(new Color(206, 212, 218));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 16, 16);
        super.paintComponent(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cmd1;
    private javax.swing.JPanel cmd2;
    private javax.swing.JPanel cmd3;
    private javax.swing.JPanel cmd4;
    private javax.swing.JPanel cmd5;
    private javax.swing.JPanel cmd6;
    private javax.swing.JPanel cmd7;
    private javax.swing.JPanel cmd8;
    private gui.combobox.ComboBoxSuggestion comboBoxPT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private gui.textfield.TextPay txtDTL;
    private gui.textfield.TextPay txtKhachDua;
    private gui.textfield.TextPay txtThanhTien;
    private gui.textfield.TextPay txtTienThua;
    private gui.textfield.TextPay txtTongTien;
    // End of variables declaration//GEN-END:variables
}
