package WindowScheme.standardToolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

import WindowScheme.workArea.WorkArea;
import WindowScheme.workArea.eventWorkArea.*;
import WindowScheme.standardToolbar.graphComponents.*;


public class GraphButton extends JButton{
	
	public GraphButton(){
		super();
		createGraphButton();
	}
	
	
	
	public void createGraphButton(){
		this.setText("Graph");
		this.addActionListener(buttonActionListener());
	}

	private ActionListener buttonActionListener() {
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				createGraph();
			}	
		};
	}
	 void createGraph(){
		WorkArea.graphsCount++;
		WorkArea.workAreaLayers.add(new Graph(), new Integer(WorkArea.graphsCount), 0);
		WorkArea.workAreaLayers.repaint();
	}
}
