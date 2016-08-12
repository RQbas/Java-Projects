package WindowScheme;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import actionListener.FormulaBarActionListener;
import mouseListener.FormulaBarMouseListener;

public class FormulaBar {
static JPanel panel;
static public JTextField Coordinates;
static public JTextField InputLine;

	static JPanel createFormulaBar(Dimension ScreenSize){
		panel= new JPanel();
		panel.setBackground(new Color(216,216,216));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		MainWindow.SetRigidArea(panel, ScreenSize, 0.01, 0.04);
		setCoordinates(ScreenSize);
		panel.add(Coordinates);
		
		MainWindow.SetRigidArea(panel, ScreenSize, 0.01, 0.04);
		setInputLine(ScreenSize);
		panel.add(InputLine);
		return panel;
	}
	
static void setCoordinates(Dimension Size){
	Coordinates= new JTextField("Position");
	Coordinates.setHorizontalAlignment(JTextField.CENTER);
	Coordinates.setMaximumSize(new Dimension((int) (0.15*Size.width), (int) (0.025*Size.height)));
	
	Coordinates.addMouseListener(new FormulaBarMouseListener());
	Coordinates.addActionListener(new FormulaBarActionListener());
}
static void setInputLine(Dimension Size){
	InputLine= new JTextField("Input line");
	InputLine.setHorizontalAlignment(JTextField.CENTER);
	InputLine.setMaximumSize(new Dimension((int) (0.85*Size.width), (int) (0.025*Size.height)));
	
	InputLine.addMouseListener(new FormulaBarMouseListener());
	InputLine.addActionListener(new FormulaBarActionListener());
}

}
