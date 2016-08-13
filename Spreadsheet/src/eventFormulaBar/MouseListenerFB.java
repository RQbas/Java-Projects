package eventFormulaBar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import WindowScheme.FormulaBar;

public class MouseListenerFB implements MouseListener{
	@Override
	public void mouseClicked(MouseEvent TextFieldClick) {
		
		if(FormulaBar.Coordinates.isFocusOwner()){
			FormulaBar.Coordinates.setText("");
		}
		else if(FormulaBar.InputLine.isFocusOwner()){
			changeInputLine();
			}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	static void changeInputLine(){
		if(FormulaBar.InputLine.getText().equals("Input line")){
			FormulaBar.InputLine.setHorizontalAlignment(JTextField.LEFT);
			FormulaBar.InputLine.setText("");}
		else{
			FormulaBar.InputLine.setText(FormulaBar.InputLine.getText());
			FormulaBar.InputLine.setHorizontalAlignment(JTextField.LEFT);
		}
		
	}
}
