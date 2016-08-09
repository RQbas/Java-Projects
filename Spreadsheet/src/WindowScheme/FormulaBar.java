package WindowScheme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class FormulaBar {
static JPanel panel;
static JTextField Coordinates;
static JTextField InputLine;

	static JPanel CreateFormulaBar(Dimension ScreenSize){
		panel= new JPanel();
		panel.setBackground(new Color(216,216,216));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		MainWindow.SetRigidArea(panel, ScreenSize, 0.01, 0.04);
		SetCoordinates(ScreenSize);
		panel.add(Coordinates);
		
		MainWindow.SetRigidArea(panel, ScreenSize, 0.01, 0.04);
		SetInputLine(ScreenSize);
		panel.add(InputLine);
		return panel;
	}
	
static void SetCoordinates(Dimension Size){
	Coordinates= new JTextField("Position");
	Coordinates.setHorizontalAlignment(JTextField.CENTER);
	Coordinates.setMaximumSize(new Dimension((int) (0.15*Size.width), (int) (0.025*Size.height)));
}
static void SetInputLine(Dimension Size){
	InputLine= new JTextField("Input line");
	InputLine.setMaximumSize(new Dimension((int) (0.85*Size.width), (int) (0.025*Size.height)));
}

}
