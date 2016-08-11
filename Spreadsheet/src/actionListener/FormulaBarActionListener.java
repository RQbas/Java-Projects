package actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;

public class FormulaBarActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent Event) {
		if(FormulaBar.Coordinates.isFocusOwner()){
			
		}
		else if(FormulaBar.InputLine.isFocusOwner()){
			String Input=FormulaBar.InputLine.getText();
			int row=WorkArea.WorkTable.getSelectedRow();
			int col=WorkArea.WorkTable.getSelectedColumn();
			WorkArea.WorkTable.setValueAt(Input, row, col);
			WorkArea.WorkTable.requestFocus();
		}
	}

}
