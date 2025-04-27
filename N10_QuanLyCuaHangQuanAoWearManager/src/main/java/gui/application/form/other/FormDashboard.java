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
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0); // Clear the table before adding new data
//        DecimalFormat decimalFormat = new DecimalFormat("#,###.## VNĐ"); // Định dạng giá
//        int pageSize = 10; // Số lượng sản phẩm trên mỗi trang
//        int pageNumber = 1; // Trang hiện tại (bắt đầu từ 1)
//        boolean hasMoreData = true; // Biến kiểm tra xem còn dữ liệu để tải không
//
//        try {
//            // Assuming you have a ProductBUS instance, adjust as needed
//            ProductBUS productBUS = new ProductBUS();
//
//            while (hasMoreData) {
//                // Get the product details from the database for the current page
//                List<Object[]> productList = productBUS.getProductDetailsDashboard(pageNumber, pageSize);
//
//                // Nếu không có dữ liệu trả về, kết thúc vòng lặp
//                if (productList.isEmpty()) {
//                    hasMoreData = false;
//                    break;
//                }
//
//                // Add each product to the table
//                for (Object[] product : productList) {
//                    //status is boolean in Product, String in table
//                    String statusString = (boolean) product[5] ? "Còn hàng" : "Hết hàng";
//                    String formattedPrice;
//                    if (product.length > 4) {
//                        formattedPrice = decimalFormat.format((double) product[4]); // Format giá
//                    } else {
//                        formattedPrice = "N/A";
//                    }
//                    model.addRow(new Object[]{
//                            product[0],       // Product ID
//                            product[1],       // Product Name
//                            product[2],       // Category Name
//                            product[3],       // Stock Quantity
//                            formattedPrice,       // Price (Formatted)
//                            statusString // Status
//                    });
//                }
//
//                // Kiểm tra xem còn dữ liệu để tải tiếp không (ví dụ: so sánh số lượng trả về với pageSize)
//                if (productList.size() < pageSize) {
//                    hasMoreData = false;
//                } else {
//                    pageNumber++; // Tăng số trang để tải tiếp
//                }
//                // In trang hiện tại
//                System.out.println("Loading page: " + pageNumber);
//            }
//
//            SwingUtilities.invokeLater(() -> {
//                table.fixTable(jScrollPane1);
//                table.setRowHeight(150);
//                table.getColumnModel().getColumn(0).setPreferredWidth(150);
//                table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error fetching product data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new gui.swing.Table();

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
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(250);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(250);
            table.getColumnModel().getColumn(4).setPreferredWidth(250);
            table.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents
//        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hello sample message");

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Card card1;
    private gui.component.Card card2;
    private gui.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lb;
    private gui.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
