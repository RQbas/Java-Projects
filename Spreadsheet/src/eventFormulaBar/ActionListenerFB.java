package eventFormulaBar;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;

public class ActionListenerFB implements ActionListener{
	static int row;
	static int col;
	static Rectangle VisibleArea;
	@Override
	public void actionPerformed(ActionEvent Event) {
		if(FormulaBar.Coordinates.isFocusOwner()){
			String Input=FormulaBar.Coordinates.getText();
			checkCoordinatesCorrect(Input);
				
			
		}
		else if(FormulaBar.InputLine.isFocusOwner()){
			WorkArea.WorkTable.requestFocus();
		}
	}
		public static void checkCoordinatesCorrect(String input){
			String ColumnName;
			int Row = 0;
			int Column = 0;
			try{
				if(Character.isUpperCase(input.charAt(0))){   
					ColumnName=String.valueOf(input.charAt(0)); //Saving first letter
					input=input.substring(1);   //Remove first letter from input (rest should be a number)
						try{
							 Row= Integer.parseInt(input)-1;
							 Column= WorkArea.getColumnNameIndex(ColumnName);
									 try{
										VisibleArea= WorkArea.WorkTable.getCellRect(Row+1, Column+1, true);
									 }catch(Exception OutOfIndex){
										 VisibleArea= WorkArea.WorkTable.getCellRect(Row, Column, true);
									 }
									 	WorkArea.WorkTable.setColumnSelectionInterval(Column, Column);
									 	WorkArea.WorkTable.setRowSelectionInterval(Row, Row);
									 	WorkArea.WorkTable.scrollRectToVisible(VisibleArea); 
						}catch(Exception WrongNumberPart){
						FormulaBar.Coordinates.setText("Wrong coordinates");
						}
				}else{
					FormulaBar.Coordinates.setText("Wrong coordinates");
				}
			}catch(Exception WrongInput){
				FormulaBar.Coordinates.setText("Wrong coordinates");
			}
		}
		
}


