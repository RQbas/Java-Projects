package eventWorkArea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;

public class MouseListenerWA implements MouseListener{
	int Start_ColumnPosition;
	int Start_RowPosition;
	
	int Finish_ColumnPosition;
	int Finish_RowPosition;
	
	
	@Override
	public void mouseClicked(MouseEvent MouseClick) {
		 if(MouseClick.getClickCount()==1){
			 if(FormulaMode.FormulaModeOn){
				setCoordinatesTextField(MouseClick);
				FormulaMode.getCoordinatesFormulaMode(MouseClick);
			 }else{
				 getActualPosition(MouseClick);
				 setCoordinatesTextField(MouseClick);
				 fillInputLine(MouseClick);
				 FormulaMode.trySetFormulaMode(WorkArea.CellContent);
			 }
			 
			 
		 }
		 
		
	}
	void setCoordinatesTextField(MouseEvent MouseClick){
		Start_RowPosition=WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1;
		Start_ColumnPosition=WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());
		
		String ActualColumn=(String) WorkArea.columnNames[Start_ColumnPosition];
		
		FormulaBar.Coordinates.setText(ActualColumn+Start_RowPosition);
	}
	void fillInputLine(MouseEvent MouseClick){
		FormulaBar.InputLine.setHorizontalAlignment(JTextField.LEFT);
		WorkArea.CellContent=(String) WorkArea.WorkTable.getModel().getValueAt(Start_RowPosition-1, Start_ColumnPosition);
		FormulaBar.InputLine.setText(WorkArea.CellContent);
	}
	void getActualPosition(MouseEvent MouseClick){
		WorkArea.ActualCol=WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());
		WorkArea.ActualRow=WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint());
	}
	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent MouseReleased) {
		Finish_ColumnPosition=WorkArea.WorkTable.columnAtPoint(MouseReleased.getPoint())+1;
		Finish_RowPosition=WorkArea.WorkTable.rowAtPoint(MouseReleased.getPoint())+1;
	}
}