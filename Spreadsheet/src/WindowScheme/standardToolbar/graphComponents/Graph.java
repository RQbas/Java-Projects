package WindowScheme.standardToolbar.graphComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import WindowScheme.workArea.WorkArea;

public class Graph extends JPanel {
    private final int width = 425;
    private final int height = 375;
    private final int gap = 15;
    private final int padding = 10;
    private final int labelPadding = 25;
    private final int pointWidth = 3;

    private Color lineColor = new Color(46, 133, 204);
    private Color pointColor = new Color(255, 0, 0);
    private Color gridColor = new Color(100, 100, 100);
    private ArrayList<GraphPoint> graphPoints;


    public Graph(ArrayList<GraphPoint> graphPoints) {
        Collections.sort(graphPoints);
        this.graphPoints = graphPoints; // sort required
        this.setBackground(properColor());
        this.setPreferredSize(new Dimension(100, 100));
        this.setBounds(0, 0, width, height);


        String xOrder = "";
        String yOrder = "";
        for (GraphPoint gp : graphPoints) {
            xOrder += gp.getX() + " ";
            yOrder += gp.getY() + " ";
        }
        WorkArea.WorkTable.setValueAt(xOrder, 15, 15);
        WorkArea.WorkTable.setValueAt(yOrder, 15, 16);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
                getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        setVerticallAxis(g2);
        setHorizontalAxis(g2);
        setThickAxis(g2);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(new BasicStroke(2f));
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = padding + labelPadding
                    + (getWidth() - 2 * padding - labelPadding) * (i) / (graphPoints.size() - 1);
            double y1 = ((double) (getHeight() - 2 * padding - labelPadding)
                    * (getMaxValue() - graphPoints.get(i).getY()) / (getMaxValue() - getMinValue())) + padding;
            int x2 = padding + labelPadding
                    + (getWidth() - 2 * padding - labelPadding) * (i + 1) / (graphPoints.size() - 1);
            double y2 = ((double) (getHeight() - 2 * padding - labelPadding)
                    * (getMaxValue() - graphPoints.get(i + 1).getY()) / (getMaxValue() - getMinValue())) + padding;
            g2.drawLine(x1, (int) y1, x2, (int) y2);
        }

        setPointsOnGraph(g2, oldStroke);

    }

    void setVerticallAxis(Graphics2D g2) {
        int yDivision = 10;
        for (int i = 0; i < yDivision; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight()
                    - ((i * (getHeight() - padding * 2 - labelPadding)) / yDivision + padding + labelPadding);
            int y1 = y0;
            if (graphPoints.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel =
                        ((int) ((getMinValue() + (getMaxValue() - getMinValue()) * ((i * 1.0) / yDivision)) * 100))
                                / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }
    }

    void setHorizontalAxis(Graphics2D g2) {
        for (int i = 0; i < graphPoints.size(); i++) {
            if (graphPoints.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (graphPoints.size() - 1) + padding
                        + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((graphPoints.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = graphPoints.get(i).getX() + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

    }

    void setThickAxis(Graphics2D g2) {
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding,
                getHeight() - padding - labelPadding);
    }

    void setPointsOnGraph(Graphics2D g2, Stroke oldStroke) {
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = padding + labelPadding + (getWidth() - 2 * padding - labelPadding) * (i) / (graphPoints.size() - 1)
                    - pointWidth / 2;
            double y = ((double) (getHeight() - 2 * padding - labelPadding)
                    * (getMaxValue() - graphPoints.get(i).getY()) / (getMaxValue() - getMinValue())) + padding;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, (int) y, ovalW, ovalH);
        }

    }


    Color properColor() {
        Color panelColor = Color.red;
        if (Math.abs(WorkArea.ActualCol - WorkArea.ReleasedCol) == 1
                || Math.abs(WorkArea.ActualRow - WorkArea.ReleasedRow) == 1)
            panelColor = new Color(218, 218, 218);
        return panelColor;
    }

    double getMaxValue() {
        double maxValue = graphPoints.get(0).getY();
        for (GraphPoint g : graphPoints) {
            if (g.getY() > maxValue)
                maxValue = g.getY();
        }
        return maxValue;
    }

    double getMaxValueArgument() {
        double maxValue = graphPoints.get(0).getY();
        double maxValueArgument = graphPoints.get(0).getX();
        for (GraphPoint g : graphPoints) {
            if (g.getY() > maxValue) {
                maxValue = g.getY();
                maxValueArgument = g.getX();
            }
        }

        return maxValueArgument;
    }

    double getMinValue() {
        double minValue = graphPoints.get(0).getY();
        for (GraphPoint g : graphPoints) {
            if (g.getY() < minValue)
                minValue = g.getY();
        }
        return minValue;
    }

    double getMinValueArgument() {
        double minValue = graphPoints.get(0).getY();
        double minValueArgument = graphPoints.get(0).getX();
        for (GraphPoint g : graphPoints) {
            if (g.getY() < minValue) {
                minValue = g.getY();
                minValueArgument = g.getX();
            }
        }
        return minValueArgument;
    }

}
