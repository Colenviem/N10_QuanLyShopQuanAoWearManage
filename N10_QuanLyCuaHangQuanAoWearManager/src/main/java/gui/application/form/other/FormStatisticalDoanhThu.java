package gui.application.form.other;

import bus.OrderBUS;
import gui.chart.ModelChartLine;
import gui.chart.ModelChartPie;
import java.awt.Color;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class FormStatisticalDoanhThu extends javax.swing.JPanel {

    private OrderBUS order_bus;
    private Map<Integer, String> dayOfWeekMap = Map.of(
            1, "Chủ Nhật",
            2, "Thứ Hai",
            3, "Thứ Ba",
            4, "Thứ Tư",
            5, "Thứ Năm",
            6, "Thứ Sáu",
            7, "Thứ Bảy"
    );
    private LocalDate currentDate = LocalDate.now();
    private ButtonGroup periodGroup;
    private DefaultTableModel tableModel; // Lưu trữ model của bảng

    public FormStatisticalDoanhThu() throws RemoteException {
        initComponents();
        order_bus = new OrderBUS();
        periodGroup = new ButtonGroup();
        periodGroup.add(radioThisWeek);
        periodGroup.add(radioLastWeek);
        periodGroup.add(radioTwoWeeksAgo);
        radioThisWeek.setSelected(true);
        tableModel = (DefaultTableModel) table.getModel(); // Khởi tạo tableModel
        loadDataForSelectedPeriod();
    }

    private LocalDate[] getStartAndEndDate(String period) {
        LocalDate start = null;
        LocalDate end = null;
        switch (period) {
            case "this_week":
                start = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                end = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "last_week":
                LocalDate lastWeekStart = currentDate.minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                start = lastWeekStart;
                end = lastWeekStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "two_weeks_ago":
                LocalDate twoWeeksAgoStart = currentDate.minusWeeks(2).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                start = twoWeeksAgoStart;
                end = twoWeeksAgoStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
        }
        return new LocalDate[]{start, end};
    }

    private void loadDataForSelectedPeriod() {
        String selectedPeriod = "this_week";
        if (radioLastWeek.isSelected()) {
            selectedPeriod = "last_week";
        } else if (radioTwoWeeksAgo.isSelected()) {
            selectedPeriod = "two_weeks_ago";
        }
        loadData(selectedPeriod);
    }

    private void loadData(String period) {
        tableModel.setRowCount(0); // Reset bảng trước khi tải dữ liệu mới
        chartLine.setModel(new ArrayList<>()); // Reset biểu đồ đường
        chartPie.setModel(new ArrayList<>());   // Reset biểu đồ tròn
        chartLine.getPnlData().removeAll();
        chartLine.getPnlData().revalidate();
        chartLine.getPnlData().repaint();

        LocalDate[] dateRange = getStartAndEndDate(period);
        LocalDate startDate = dateRange[0];
        LocalDate endDate = dateRange[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String periodLabel = "";
        if (startDate != null && endDate != null) {
            periodLabel = String.format(" (%s - %s)", startDate.format(formatter), endDate.format(formatter));
        }
        jLabel1.setText("BẢNG DOANH THU SẢN PHẨM TRONG TUẦN" + periodLabel);

        try {
            // 1. Lấy doanh thu theo ngày trong tuần
            List<Object[]> revenueByDayOfWeek = order_bus.getStoreRevenueByDayOfWeek(startDate, endDate);
            System.out.println("Dữ liệu doanh thu theo ngày trong tuần (" + period + "): " + revenueByDayOfWeek); // In dữ liệu

            List<ModelChartLine> lineData = new ArrayList<>();
            if (revenueByDayOfWeek != null) {
                for (Object[] row : revenueByDayOfWeek) {
                    int dayOfWeek = ((Number) row[0]).intValue();
                    double totalRevenue = (row[1] != null) ? ((Number) row[1]).doubleValue() : 0.0;
                    lineData.add(new ModelChartLine(
                            dayOfWeekMap.get(dayOfWeek),
                            totalRevenue
                    ));
                    System.out.println("Ngày: " + dayOfWeekMap.get(dayOfWeek) + ", Doanh thu: " + totalRevenue); // In từng dòng dữ liệu
                }
            }
            chartLine.setModel(lineData);

            // 2. Lấy 5 sản phẩm bán chạy nhất
            List<Object[]> top5Products = order_bus.getTop5BestSellingProductsThisWeek(startDate, endDate);
            List<ModelChartPie> pieData = new ArrayList<>();
            Random rand = new Random();
            if (top5Products != null) {
                for (Object[] row : top5Products) {
                    String productName = (String) row[0];
                    double revenue = (row[1] != null) ? ((Number) row[1]).doubleValue() : 0.0;

                    pieData.add(new ModelChartPie(
                            productName,
                            revenue,
                            new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
                    ));
                }
            }
            chartPie.setModel(pieData);

            // 3. Lấy doanh thu tất cả sản phẩm trong tuần
            List<Object[]> productRevenueThisWeek = order_bus.getAllProductRevenueThisWeek(startDate, endDate);
            System.out.println("Doanh thu tất cả sản phẩm tuần (" + period + "): " + productRevenueThisWeek);
            if (productRevenueThisWeek != null) {
                for (Object[] row : productRevenueThisWeek) {
                    String productName = (String) row[0];
                    double revenue = (row[1] != null) ? ((Number) row[1]).doubleValue() : 0.0;

                    tableModel.addRow(new Object[]{
                            productName,
                            String.format("%.2f VNĐ", revenue)
                    });
                }
            }

            // Cấu hình bảng (vẫn giữ nguyên)
            SwingUtilities.invokeLater(() -> {
                try {
                    table.setRowHeight(40);
                    table.getColumnModel().getColumn(0).setPreferredWidth(300); // Tên sản phẩm
                    table.getColumnModel().getColumn(1).setPreferredWidth(150); // Doanh thu
                    table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        chartLine = new gui.chart.ChartLine();
        chartPie = new gui.chart.ChartPie();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new gui.table.TableCustom();
        nav = new gui.navbar.NavbarSearch();
        jPanel1 = new javax.swing.JPanel();
        radioThisWeek = new javax.swing.JRadioButton();
        radioLastWeek = new javax.swing.JRadioButton();
        radioTwoWeeksAgo = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("BẢNG DOANH THU SẢN PHẨM TRONG TUẦN");

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "Tên sản phẩm", "Doanh thu"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);

        nav.hideComponentSearch();

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        radioThisWeek.setText("Tuần này");
        radioThisWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioThisWeekActionPerformed(evt);
            }
        });
        jPanel1.add(radioThisWeek);

        radioLastWeek.setText("Tuần trước");
        radioLastWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioLastWeekActionPerformed(evt);
            }
        });
        jPanel1.add(radioLastWeek);

        radioTwoWeeksAgo.setText("2 tuần trước");
        radioTwoWeeksAgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTwoWeeksAgoActionPerformed(evt);
            }
        });
        jPanel1.add(radioTwoWeeksAgo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nav, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(chartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 800, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(chartPie, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                                        .addComponent(jLabel1)
                                        .addComponent(jScrollPane2)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(chartPie, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                        .addComponent(chartLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void radioThisWeekActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataForSelectedPeriod();
    }

    private void radioLastWeekActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataForSelectedPeriod();
    }

    private void radioTwoWeeksAgoActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataForSelectedPeriod();
    }

    // Variables declaration - do not modify
    private gui.chart.ChartLine chartLine;
    private gui.chart.ChartPie chartPie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private gui.navbar.NavbarSearch nav;
    private javax.swing.JRadioButton radioLastWeek;
    private javax.swing.JRadioButton radioThisWeek;
    private javax.swing.JRadioButton radioTwoWeeksAgo;
    private gui.table.TableCustom table;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                javax.swing.JFrame frame = new javax.swing.JFrame("Test FormStatisticalDoanhThu");
                frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null); // Center window

                FormStatisticalDoanhThu form = new FormStatisticalDoanhThu();
                frame.setContentPane(form);

                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}