package WindowScheme.workArea.KeyBindings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import WindowScheme.workArea.WorkArea;
import WindowScheme.workArea.eventWorkArea.FormulaMode;



public class EnterBindingWA extends KeyBindingWA {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
				getActualPosition();
				try{
						performKeyMovement(row+1, col);
						changeCellSelection(row, col);
						fillInputLine();
						setCoordinatesTextField();
						performFormulaExecution();
				}catch(Exception OutOfBounds){}
				}
		
	void performFormulaExecution(){
		FormulaMode.trySetFormulaMode(WorkArea.WorkTable.getModel().getValueAt(row-1, col).toString());
		if(FormulaMode.FormulaModeOn){
		FormulaMode.setFullFormula(row-1, col);
	}
	}
}
