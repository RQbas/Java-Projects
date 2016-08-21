package eventWorkArea.KeyBindings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;
import eventWorkArea.FormulaMode;

public class EnterBindingWA extends KeyBindingWA {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
				getActualPosition();
				if(FormulaMode.FormulaModeOn){
					FormulaMode.setFullFormula(row, col);
					}
				try{
					
					performKeyMovement(row+1, col);
					changeCellSelection(row, col);
					fillInputLine();
					setCoordinatesTextField();
				}catch(Exception OutOfBounds){}
		}
}
