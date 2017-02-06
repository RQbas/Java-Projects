package WindowScheme.standardToolbar.graphComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import WindowScheme.workArea.WorkArea;

public class Graph extends JPanel{
	private final int width = 400;
	private final int height = 350;
	private final int gap = 15;
	private int argumentsNumber;
	private int maxValue;
	
	
	
	public Graph(){
		getArgumentsNumber();
		getMaxValue();
		this.setBackground(properColor());
		this.setPreferredSize(new Dimension(100, 100));
		this.setBounds(0, 0, width, height);
	}



	@Override
	   protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D)g;
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	      double xScale = ((double) getWidth() - 2 * gap) / (argumentsNumber - 1);
	      double yScale = ((double) getHeight() - 2 * gap) / (maxValue - 1);
	      
		
	}

Color properColor(){
	Color panelColor = Color.red;
			if(Math.abs(WorkArea.ActualCol - WorkArea.ReleasedCol) == 1|| Math.abs(WorkArea.ActualRow - WorkArea.ReleasedRow ) == 1)
				panelColor = new Color(238, 238, 238);
			return panelColor;
}

private void getArgumentsNumber() {
	if(Math.abs(WorkArea.ActualCol - WorkArea.ReleasedCol) == 1 ){
		argumentsNumber = Math.abs(WorkArea.ActualRow - WorkArea.ReleasedRow );
	}
	if(Math.abs(WorkArea.ActualRow - WorkArea.ReleasedRow ) == 1){
		argumentsNumber = Math.abs(WorkArea.ActualCol - WorkArea.ReleasedCol);
	}
	
}

private void getMaxValue() {
	maxValue = 100;
}

}