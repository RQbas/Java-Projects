package mouseListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;



public class WorkAreaMouseListener implements MouseListener{
	int ColumnPosition;
	int RowPosition;
	@Override
	public void mouseClicked(MouseEvent MouseClick) {
		 if(MouseClick.getClickCount()==1){
			 setCoordinatesTextField(MouseClick);
			 fillInputLine(MouseClick);
		
		 }
		
	}
	void setCoordinatesTextField(MouseEvent MouseClick){
		RowPosition=WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1;
		ColumnPosition=WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());
		
		String ActualRow=Integer.toString(WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1);
		String ActualColumn=(String) WorkArea.columnNames[ColumnPosition];
		
		FormulaBar.Coordinates.setText(ActualColumn+RowPosition);
	}
	void makeCellEditable(){
	
	}
	void fillInputLine(MouseEvent MouseClick){
		FormulaBar.InputLine.setText((String) WorkArea.WorkTable.getModel().getValueAt(RowPosition-1, ColumnPosition));   
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
