package gui.chart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class PanelChartLine extends javax.swing.JPanel {

    private final List<ModelChartLine> list;
    private Color lineColor = new Color(6, 131, 212);  // Màu đường
    private Color pointColor = new Color(6, 131, 212);  // Màu điểm
    private Color labelColor = Color.DARK_GRAY;       // Màu chữ
    private int pointSize = 8;                   // Kích thước điểm
    private int labelOffsetY = 20;                // Khoảng cách từ điểm đến chữ
    private float lineWidth = 2f;                  // Độ dày của đường

    public PanelChartLine() {
        list = new ArrayList<>();
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private double getMax() {
        double max = 0;
        for (ModelChartLine d : list) {
            if (d.getValue() > max) {
                max = d.getValue();
            }
        }
        return max;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (list.size() > 1) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int margin = 50; // Chừa rộng thêm để ghi số
            int width = getWidth() - margin * 2;
            int height = getHeight() - margin * 2;
            double max = getMax();
            double space = width / (list.size() - 1);

            // ======= Vẽ các đường kẻ ngang (nét đứt) =======
            Stroke oldStroke = g2.getStroke();
            g2.setColor(new Color(220, 220, 220));
            float[] dashPattern = {5, 5};
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));

            int numLines = 5;
            for (int i = 0; i <= numLines; i++) {
                int y = margin + (height * i / numLines);
                g2.drawLine(margin, y, getWidth() - margin, y);
            }
            g2.setStroke(oldStroke);

            // ======= Vẽ trục Y và ghi số =======
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            for (int i = 0; i <= numLines; i++) {
                int y = margin + (height * i / numLines);
                double value = max * (1 - (double)i / numLines); // Giá trị doanh thu tương ứng
                String label = String.format("%.0f", value);
                int labelWidth = g2.getFontMetrics().stringWidth(label);
                g2.drawString(label, margin - labelWidth - 5, y + 5);
            }

            // ======= Vẽ đường biểu đồ chính =======
            Path2D.Float p = new Path2D.Float();
            p.moveTo(margin, getHeight() - margin);
            int x = margin;
            for (ModelChartLine data : list) {
                double location = data.getValue() * height / max;
                location = height - location; // chỉ height thôi, không getHeight
                p.lineTo(x, margin + location); // cộng lại margin khi vẽ
                x += space;
            }
            g2.setColor(lineColor);
            g2.setStroke(new BasicStroke(lineWidth));
            g2.draw(p);

            // ======= Tô nền dưới đường =======
            GradientPaint gra = new GradientPaint(0, margin,
                    new Color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), 100),
                    0, getHeight(),
                    new Color(255, 255, 255, 0));
            g2.setPaint(gra);
            g2.fill(p);

            // ======= Vẽ điểm và tên ngày =======
            drawPointsAndNames(g2, margin, height, max, space);
        }
        super.paintComponent(grphcs);
    }


    private void drawPointsAndNames(Graphics2D g2, int margin, int height, double max, double space) {
        g2.setColor(pointColor);
        for (int i = 0; i < list.size(); i++) {
            ModelChartLine data = list.get(i);
            int x = (int) (margin + i * space);
            double location = data.getValue() * height / max;
            int y = (int) (margin + height - location); // thêm margin

            // Vẽ chấm tròn
            g2.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);

            // Vẽ tên ngày
            if (data.getName() != null && !data.getName().isEmpty()) {
                g2.setColor(labelColor);
                Font font = g2.getFont();
                g2.setFont(font.deriveFont(Font.BOLD, 12f));
                int stringWidth = g2.getFontMetrics().stringWidth(data.getName());
                int xName = x - stringWidth / 2;

                int yName;
                if (data.getValue() == 0) {
                    // Nếu giá trị = 0, thì đẩy tên lên trên chấm một chút
                    yName = y - 10;
                } else {
                    // Nếu có giá trị, vẽ tên bên dưới chấm
                    yName = y + labelOffsetY;
                }

                g2.drawString(data.getName(), xName, yName);
                g2.setFont(font);
            }
        }
    }


    public void addItem(ModelChartLine data) {
        list.add(data);
        repaint();
    }

    public void removeAllData() {
        list.clear();
        repaint();
    }

    // Các phương thức setter để tùy chỉnh màu sắc và kiểu dáng
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        repaint();
    }

    public void setPointColor(Color pointColor) {
        this.pointColor = pointColor;
        repaint();
    }

    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
        repaint();
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
        repaint();
    }

    public void setLabelOffsetY(int labelOffsetY) {
        this.labelOffsetY = labelOffsetY;
        repaint();
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        repaint();
    }
}

