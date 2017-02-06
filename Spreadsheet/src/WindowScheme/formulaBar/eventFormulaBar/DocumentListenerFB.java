package WindowScheme.formulaBar.eventFormulaBar;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import WindowScheme.formulaBar.FormulaBar;
import WindowScheme.workArea.WorkArea;
import WindowScheme.workArea.eventWorkArea.FormulaMode;

public class DocumentListenerFB  implements DocumentListener{
	 private JTextField textField;

	 
	 
	 public DocumentListenerFB(JTextField textField){
		 this.textField = textField; this.textField = textField;
	 }
	@Override
	public void changedUpdate(DocumentEvent ChangeEvent) {
		sendTextToTable();
		
	}
	@Override
	public void insertUpdate(DocumentEvent InsertEvent) {
		sendTextToTable();
	}

	@Override
	public void removeUpdate(DocumentEvent RemoveEvent) {
		try{
			sendTextToTable();
		}catch(Exception OutOfBounds){}
		}
	public static void sendTextToTable(){
		String Input=FormulaBar.InputLine.getText();
		 int row=WorkArea.WorkTable.getSelectedRow();
		 int col=WorkArea.WorkTable.getSelectedColumn();
		WorkArea.WorkTable.setValueAt(Input, row, col);
		FormulaMode.trySetFormulaMode(Input);
		
	}
}