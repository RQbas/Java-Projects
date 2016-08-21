package eventWorkArea.KeyBindings;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;
import eventWorkArea.DocumentListenerWA;
import eventWorkArea.FormulaMode;
import eventWorkArea.MouseListenerWA;

public class KeyBindingWA extends AbstractAction {
	int row;
	int col;
	@Override
	public void actionPerformed(ActionEvent arg0) {}
	
	
	
	
	void getActualPosition(){
		
		this.row=WorkArea.WorkTable.getSelectedRow();
		this.col=WorkArea.WorkTable.getSelectedColumn();

	}
	void performKeyMovement(int row, int col){
		this.row=row;
		this.col=col;
		
		WorkArea.ActualRow=row;
		WorkArea.ActualCol=col;
		
		WorkArea.CellContent=(String) WorkArea.WorkTable.getModel().getValueAt(row, col);
		FormulaMode.trySetFormulaMode(WorkArea.CellContent);
	}
	void changeCellSelection(int row, int col){

		
		WorkArea.WorkTable.changeSelection(row, col, false, false);
		WorkArea.WorkTable.editCellAt(row, col);	
		
	}
	
	void setCoordinatesTextField(){
		String ActualRow=Integer.toString(row);
		String ActualColumn=(String) WorkArea.columnNames[col];
		FormulaBar.Coordinates.setText(ActualColumn+ActualRow);
	}
	
	void fillInputLine(){
		
		FormulaBar.InputLine.setText((String) WorkArea.WorkTable.getModel().getValueAt(row, col));
		
	}
}