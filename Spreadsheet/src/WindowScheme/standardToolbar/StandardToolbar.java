package WindowScheme.standardToolbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class StandardToolbar {
static JPanel panel;
static JButton graphButton;
	
	public static JPanel createStandardToolbar(){
		panel= new JPanel();
		panel.setBackground(new Color(211,218,237));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new GraphButton());
		
		return panel;
	}
	
}
