package WindowScheme;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MenuBar {
	static JPanel panel;
	
	static JPanel createMenuBar(){
		panel= new JPanel();
		panel.setBackground(new Color(172,186,221));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		return panel;
	}
}
