package WindowScheme;

import java.awt.Color;
import javax.swing.JPanel;

public class MenuBar {
	static JPanel panel;
	
	static JPanel CreateMenuBar(){
		panel= new JPanel();
		panel.setBackground(Color.RED);
		return panel;
	}
}
