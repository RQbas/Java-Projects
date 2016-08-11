package mouseListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;

public class FormulaBarMouseListener implements MouseListener{
	@Override
	public void mouseClicked(MouseEvent TextFieldClick) {
		
		if(FormulaBar.Coordinates.isFocusOwner()){
			FormulaBar.Coordinates.setText("");
		}
		else if(FormulaBar.InputLine.isFocusOwner()){
			FormulaBar.InputLine.setText("");
			FormulaBar.InputLine.setHorizontalAlignment(JTextField.LEFT);
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
}
