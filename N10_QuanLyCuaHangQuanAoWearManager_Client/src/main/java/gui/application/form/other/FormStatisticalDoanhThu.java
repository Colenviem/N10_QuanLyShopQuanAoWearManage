package gui.application.form.other;

import gui.chart.ModelChartLine;
import gui.chart.ModelChartPie;
import interfaces.IOrderService;

import java.awt.Color;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class FormStatisticalDoanhThu extends javax.swing.JPanel {

    private IOrderService orderService;
    private static final String HOST = "localhost";
    private static final int PORT = 9090;
    private static Context ctx;

    public FormStatisticalDoanhThu() throws RemoteException {
        initComponents();
        initData();
    }

    private void initData() {
        try {
            ctx = new InitialContext();
            orderService = (IOrderService) ctx.lookup("rmi://" + HOST + ":" + PORT + "/OrderBUS");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing data
            int year = 2025; // Năm cần lấy dữ liệu
            // Lấy dữ liệu doanh thu theo tháng từ OrderBUS cho năm 2024
            List<Object[]> monthlyRevenueData = orderService.getOrderSummariesForYear(year);

            // Duyệt qua dữ liệu và thêm vào bảng
            if (monthlyRevenueData != null) {
                for (Object[] row : monthlyRevenueData) {
                    // Chuyển đổi kiểu dữ liệu và xử lý nếu có giá trị null
                    LocalDate orderDate = (LocalDate) row[1]; // Lấy ngày từ kết quả truy vấn
                    double totalAmount = (row[9] != null) ? (double) row[9] : 0.0;

                    // Định dạng ngày tháng
                    String formattedDate = orderDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));

                    model.addRow(new Object[]{
                            formattedDate, // Tháng/Năm
                            String.format("%.2f VNĐ", totalAmount) // Tổng doanh thu
                    });
                }
            }

            // Cấu hình bảng
            SwingUtilities.invokeLater(() -> {
                try {
//                    table.fixTable(jScrollPane2);
                    table.setRowHeight(150);
                    table.getColumnModel().getColumn(0).setPreferredWidth(150); // Tháng/Năm
                    table.getColumnModel().getColumn(1).setPreferredWidth(200); // Doanh thu
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Populate chartPie (Tháng và Doanh thu)
            List<ModelChartPie> pieData = new ArrayList<>();
            Random rand = new Random();
            if (monthlyRevenueData != null) {
                for (Object[] row : monthlyRevenueData) {
                    LocalDate orderDate = (LocalDate) row[1];
                    double revenue = (row[9] != null) ? (double) row[9] : 0.0;
                    pieData.add(new ModelChartPie(
                            orderDate.format(DateTimeFormatter.ofPattern("MM/yyyy")), // Label tháng
                            revenue,
                            new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
                    ));
                }
            }
            chartPie.setModel(pieData);

            // Populate chartLine (Tháng và Doanh thu)
            List<ModelChartLine> lineData = new ArrayList<>();
            if (monthlyRevenueData != null) {
                for (Object[] row : monthlyRevenueData) {
                    LocalDate orderDate = (LocalDate) row[1];
                    double revenue = (row[9] != null) ? (double) row[9] : 0.0;
                    lineData.add(new ModelChartLine(
                            orderDate.format(DateTimeFormatter.ofPattern("MM/yyyy")), // Label tháng
                            revenue
                    ));
                }
            }
            chartLine.setModel(lineData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartLine = new gui.chart.ChartLine();
        chartPie = new gui.chart.ChartPie();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new gui.table.TableCustom();
        nav = new gui.navbar.NavbarSearch();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("TABLE DOANH THU");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);
        nav.hideComponentSearch();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(nav, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chartPie, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(nav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chartPie, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(chartLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.chart.ChartLine chartLine;
    private gui.chart.ChartPie chartPie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private gui.navbar.NavbarSearch nav;
    private gui.table.TableCustom table;
    // End of variables declaration//GEN-END:variables
}
