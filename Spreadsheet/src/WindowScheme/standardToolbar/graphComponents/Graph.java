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
    private int width = 425;
    private int height = 375;
    private final int gap = 15;
    private final int padding = 10;
    private int xLabelPadding = 25;
    private int yLabelPadding = 25;
    private final int pointWidth = 4;

    private Color lineColor;
    private Color pointColor;
    private Color gridColor = new Color(100, 100, 100);
    private ArrayList<GraphPoint> graphPoints;


    public Graph(ArrayList<GraphPoint> graphPoints, Color lineColor, Color pointColor) {
        Collections.sort(graphPoints);
        this.graphPoints = graphPoints;
        
        increaseScalableLabelPadding();
        setGraphColors(new Color(218, 218, 218), lineColor, pointColor);
        
        this.setPreferredSize(new Dimension(100, 100));
        this.setBounds(0, 0, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
        setBackground(g2);
        setVerticallAxis(g2);
        setHorizontalAxis(g2);
        setThickAxis(g2);
        setGraphLines(g2, oldStroke);
        setPointsOnGraph(g2, oldStroke);
    }
    
    void setBackground(Graphics2D g2){
    	g2.setColor(Color.WHITE);
        g2.fillRect(padding + xLabelPadding, padding, getWidth() - (2 * padding) - xLabelPadding,
                getHeight() - 2 * padding - yLabelPadding);
        g2.setColor(Color.BLACK);
    }
    
    void setVerticallAxis(Graphics2D g2) {
        int yDivision = 10;
        for (int i = 0; i < yDivision; i++) {
            int x0 = padding + xLabelPadding;
            int x1 = pointWidth + padding + xLabelPadding;
            int y0 = getHeight()
                    - ((i * (getHeight() - padding * 2 - yLabelPadding)) / yDivision + padding + yLabelPadding);
            int y1 = y0;
            if (graphPoints.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + xLabelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
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
                int x0 = i * (getWidth() - padding * 2 - xLabelPadding) / (graphPoints.size() - 1) + padding
                        + xLabelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - yLabelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((graphPoints.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - yLabelPadding - 1 - pointWidth, x1, padding);
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
        g2.drawLine(padding + xLabelPadding, getHeight() - padding - yLabelPadding, padding + xLabelPadding, padding);
        g2.drawLine(padding + xLabelPadding, getHeight() - padding - yLabelPadding, getWidth() - padding,
                getHeight() - padding - yLabelPadding);
    }

    void setGraphLines(Graphics2D g2, Stroke oldStroke){
    	g2.setColor(lineColor);
        g2.setStroke(new BasicStroke(2f));
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = padding + xLabelPadding
                    + (getWidth() - 2 * padding - xLabelPadding) * (i) / (graphPoints.size() - 1);
            double y1 = ((double) (getHeight() - 2 * padding - yLabelPadding)
                    * (getMaxValue() - graphPoints.get(i).getY()) / (getMaxValue() - getMinValue())) + padding;
            int x2 = padding + xLabelPadding
                    + (getWidth() - 2 * padding - xLabelPadding) * (i + 1) / (graphPoints.size() - 1);
            double y2 = ((double) (getHeight() - 2 * padding - yLabelPadding)
                    * (getMaxValue() - graphPoints.get(i + 1).getY()) / (getMaxValue() - getMinValue())) + padding;
            g2.drawLine(x1, (int) y1, x2, (int) y2);
        }
    }
    
    
    void setPointsOnGraph(Graphics2D g2, Stroke oldStroke) {
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = padding + xLabelPadding + (getWidth() - 2 * padding - xLabelPadding) * (i) / (graphPoints.size() - 1)
                    - pointWidth / 2;
            double y = ((double) (getHeight() - 2 * padding - yLabelPadding)
                    * (getMaxValue() - graphPoints.get(i).getY()) / (getMaxValue() - getMinValue())) + padding;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, (int) y, ovalW, ovalH);
        }

    }


    void setGraphColors(Color...colors) {
        this.setBackground(colors[0]);
        this.lineColor = colors[1];
        this.pointColor = colors[2];
        
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
    
    void increaseScalableLabelPadding(){
    	int scalableIncrement = 0;
    	int maxDigitNumber = 0;
    	maxDigitNumber = String.valueOf(graphPoints.get(0).getY()).length();
   
    	for(GraphPoint gp: graphPoints){
    		if(String.valueOf(gp.getY()).length() > maxDigitNumber)
    			maxDigitNumber = String.valueOf(gp.getY()).length();
    	}
    	if(maxDigitNumber >3){
    		for(int i = maxDigitNumber-3 ; i>= 0 ; i--){
    			scalableIncrement+=6;
    		}
    	}
    	
    	this.xLabelPadding += scalableIncrement;
    	this.width += scalableIncrement; 
    }

}
