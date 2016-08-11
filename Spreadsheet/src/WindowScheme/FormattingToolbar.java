package WindowScheme;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class FormattingToolbar {
static JPanel panel;
	
	static JPanel createFormattingToolbar(){
		panel= new JPanel();
		panel.setBackground(new Color(211,218,237));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		return panel;
	}
}
