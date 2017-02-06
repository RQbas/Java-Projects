package WindowScheme.workArea.eventWorkArea;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import WindowScheme.formulaBar.FormulaBar;
import WindowScheme.workArea.WorkArea;

public class DocumentListenerWA implements DocumentListener{
 private JTextField textField;
	 
	 
	 public DocumentListenerWA(JTextField textField){
		 this.textField = textField; 
	 }
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		WorkArea.CellContent=textField.getText();
		FormulaBar.InputLine.setText(WorkArea.CellContent);
		FormulaMode.trySetFormulaMode(WorkArea.CellContent);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		WorkArea.CellContent=textField.getText();
		FormulaBar.InputLine.setText(WorkArea.CellContent);
		FormulaMode.trySetFormulaMode(WorkArea.CellContent);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		WorkArea.CellContent=textField.getText();
		FormulaBar.InputLine.setText(WorkArea.CellContent);
		FormulaMode.trySetFormulaMode(WorkArea.CellContent);
	}
	}

