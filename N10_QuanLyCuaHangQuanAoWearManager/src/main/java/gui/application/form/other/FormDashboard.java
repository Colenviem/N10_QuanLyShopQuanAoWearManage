package gui.application.form.other;

import bus.OrderBUS;
import bus.ProductBUS;
import com.formdev.flatlaf.FlatClientProperties;
import gui.model.ModelStaff;
import gui.model.Model_Card;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class FormDashboard extends javax.swing.JPanel {

    private OrderBUS orderBUS;
    private ProductBUS productBUS;
    private DecimalFormat decimalFormatter = new DecimalFormat("#,###.##");

    public FormDashboard() throws RemoteException {
        initComponents();
        orderBUS = new OrderBUS();
        productBUS = new ProductBUS();
        initCards();
        initData();
    }
    private void initCards() {
        try {
            // Lấy dữ liệu từ BUS
            double totalRevenue = orderBUS.getTotalRevenue("tháng");
            double previousTotalRevenue = orderBUS.getTotalRevenue("tháng trước");
            int totalProductsSold = productBUS.getTotalProductsSold("tháng");
            int previousTotalProductsSold = productBUS.getTotalProductsSold("tháng trước");
            double[] avgOrderData = orderBUS.getAverageOrderValueAndGrowth("tháng");
            double averageOrderValue = avgOrderData[0];
            double growthPercentage = avgOrderData[1];

            // Tính toán tăng trưởng
            double revenueGrowth = calculateGrowth(totalRevenue, previousTotalRevenue);
            double productSoldGrowth = calculateGrowth(totalProductsSold, previousTotalProductsSold);

            // Định dạng và hiển thị dữ liệu lên các card
            card1.setData(new Model_Card(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/icon/1.svg"))), "Tổng doanh thu", decimalFormatter.format(totalRevenue) + " VNĐ", getGrowthText(revenueGrowth)));
            card2.setData(new Model_Card(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/icon/2.svg"))), "Sản phẩm đã bán", String.valueOf(totalProductsSold) + " sản phẩm", getGrowthText(productSoldGrowth)));
            card3.setData(new Model_Card(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/icon/3.svg"))), "Giá trị đơn hàng TB", decimalFormatter.format(averageOrderValue) + " VNĐ", getGrowthText(growthPercentage)));
        } catch (RemoteException ex) {
            Logger.getLogger(FormDashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error fetching card data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private double calculateGrowth(double currentValue, double previousValue) {
        if (previousValue != 0) {
            return ((currentValue - previousValue) / previousValue) * 100;
        } else {
            return 0; // Or Double.NaN, tùy vào logic của bạn
        }
    }

    private String getGrowthText(double growthPercentage) {
        if (growthPercentage > 0) {
            return "Tăng trưởng " + decimalFormatter.format(growthPercentage) + "%";
        } else if (growthPercentage < 0) {
            return "Giảm " + decimalFormatter.format(Math.abs(growthPercentage)) + "%";
        } else {
            return "Tăng trưởng " + decimalFormatter.format(Math.abs(growthPercentage)) + "%";
        }
    }


    private void initData() {
        try {
            List<Object[]> productDetails = productBUS.getProductDetailsDashboard();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0); // Clear existing data
            for (Object[] rowData : productDetails) {
                String statusDisplay;
                String statusStr = rowData[5] != null ? rowData[5].toString().trim().toLowerCase() : "";
                if (statusStr.equals("còn") || statusStr.equals("active") || statusStr.equals("true")) {
                    statusDisplay = "Còn";
                } else if (statusStr.equals("ngưng") || statusStr.equals("inactive") ||
                        statusStr.equals("false") || statusStr.isEmpty()) {
                    statusDisplay = "Ngưng";
                } else {
                    statusDisplay = "Ngưng"; // Default for unknown values
                    Logger.getLogger(FormDashboard.class.getName()).log(Level.WARNING,
                            "Unknown status value: {0}", statusStr);
                }
                Object[] displayRowData = {
                        rowData[0], // Mã sản phẩm
                        rowData[1], // Tên sản phẩm
                        rowData[2], // Danh mục
                        rowData[3], // Số lượng tồn kho
                        decimalFormatter.format(rowData[4]) + " VNĐ", // Giá
                        statusDisplay
                };
                tableModel.addRow(displayRowData);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FormDashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card1 = new gui.component.Card();
        card2 = new gui.component.Card();
        card3 = new gui.component.Card();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new gui.table.TableCustom();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Dashboard");

        javax.swing.GroupLayout lbLayout = new javax.swing.GroupLayout(lb);
        lb.setLayout(lbLayout);
        lbLayout.setHorizontalGroup(
            lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbLayout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lbLayout.setVerticalGroup(
            lbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lbLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLayeredPane1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        jLayeredPane1.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        jLayeredPane1.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        jLayeredPane1.add(card3);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Số lượng tồn kho", "Giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
//        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hello sample message");

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Card card1;
    private gui.component.Card card2;
    private gui.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel lb;
    private gui.table.TableCustom table;
    // End of variables declaration//GEN-END:variables
}
