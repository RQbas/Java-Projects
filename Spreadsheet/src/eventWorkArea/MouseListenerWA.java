package eventWorkArea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
			 setCoordinatesTextField(MouseClick);
			 fillInputLine(MouseClick);
		
		 }
		
	}
	void setCoordinatesTextField(MouseEvent MouseClick){
		Start_RowPosition=WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1;
		Start_ColumnPosition=WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());
		
		String ActualRow=Integer.toString(WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1);
		String ActualColumn=(String) WorkArea.columnNames[Start_ColumnPosition];
		
		FormulaBar.Coordinates.setText(ActualColumn+Start_RowPosition);
	}
	void fillInputLine(MouseEvent MouseClick){
		FormulaBar.InputLine.setText((String) WorkArea.WorkTable.getModel().getValueAt(Start_RowPosition-1, Start_ColumnPosition));   
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