package eventWorkArea;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import WindowScheme.FormulaBar;
import WindowScheme.WorkArea;

public class FormulaMode {
	 public static boolean FormulaModeOn;
	public static String FullFormula;
	public static List<String> FormulaParts;
	public static void trySetFormulaMode(String UserInput){
		char ModeInserter= '=';
		try{
			char isEqualSign= UserInput.charAt(0);
			if(isEqualSign==(ModeInserter)){
				FormulaModeOn=true;
					FormulaBar.InputLine.setBackground(new Color(102,215,102));
				}
			else{
				FormulaModeOn=false;
				FormulaBar.InputLine.setBackground(new Color(255,255,255));
			}
		}catch(Exception EmptyInput){
			FormulaModeOn=false;
			FormulaBar.InputLine.setBackground(new Color(255,255,255));
			
		}
		
	}
	
	public static void getCoordinatesFormulaMode(MouseEvent MouseClick){
		int Start_RowPosition=WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1;
		int Start_ColumnPosition=WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());
		
		
		String ActualRow=Integer.toString(WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint())+1);
		String ActualColumn=(String) WorkArea.columnNames[Start_ColumnPosition];
		String SaveCellContent=((String) WorkArea.WorkTable.getModel().getValueAt(WorkArea.ActualRow, WorkArea.ActualCol));

		WorkArea.WorkTable.getModel().setValueAt((SaveCellContent+""+ActualColumn+""+ActualRow), WorkArea.ActualRow, WorkArea.ActualCol);
		WorkArea.WorkTable.changeSelection(WorkArea.ActualRow, WorkArea.ActualCol, false, false);
		WorkArea.WorkTable.editCellAt(WorkArea.ActualRow,  WorkArea.ActualCol);
		
}
	public static void setFullFormula(int row, int col){
		FormulaMode.FullFormula=(String) WorkArea.WorkTable.getModel().getValueAt(row, col);
		FullFormula=FullFormula.substring(1);
		parseFullFormula(row, col);
		
	}
	public static void parseFullFormula(int row, int col){
		FormulaParts=parseValues();
		parseMathSigns(FormulaParts);
		performMathOperation(FormulaParts, row, col);
	
	}	
	public static List<String> parseValues(){
		String MathSymbols="[+\\-*/ ]+";
		List<String> Values = new ArrayList<String>();
		String[] FormulaTokens = FullFormula.split(MathSymbols);
			for(int i=0; i<FormulaTokens.length; i++){
				Values.add(FormulaTokens[i]);
			}
			
			return convertValue(Values);
	}
	
	public static void parseMathSigns(List<String> values){
		List<List<Integer>> listOfSigns = new ArrayList<List<Integer>>();
		int OperationNumber=0;
		int MinValue=Integer.MAX_VALUE;
		int ActualMin=-1;
		int MinIndex_i=0;
		int MinIndex_j=0;
		for(int i=0; i<4; i++)
			listOfSigns.add(new ArrayList<Integer>());

		//Fill Array with sign occurence
		for(int index=0; index<FullFormula.length(); index++){
			if(FullFormula.charAt(index) == '+'){
				listOfSigns.get(0).add(index);
				OperationNumber++;
			}else if(FullFormula.charAt(index) == '-'){
				listOfSigns.get(1).add(index);
				OperationNumber++;
			}
			else if(FullFormula.charAt(index) == '*'){
				listOfSigns.get(2).add(index);
				OperationNumber++;
			}
			else if(FullFormula.charAt(index) == '/'){
				listOfSigns.get(3).add(index);
				OperationNumber++;
			}
		}
		
		for(int k=0; k<OperationNumber; k++){
			for(int i=0; i<listOfSigns.size(); i++){
					for(int j=0; j<listOfSigns.get(i).size(); j++){
						if(listOfSigns.get(i).get(j) < MinValue){
							if(listOfSigns.get(i).get(j) > ActualMin){
								MinValue=listOfSigns.get(i).get(j);
								MinIndex_i=i;
								MinIndex_j=j;
							}
								
						}
					}
			}
			ActualMin=MinValue;
			insertSign(values, MinIndex_i, k);
			MinValue=Integer.MAX_VALUE;
		}
	}
	
		public static void insertSign(List<String> values, int index, int order){
				switch(index){
				case 0:{
					values.add((2*order+1), "+");
					break;
				}
				case 1:{
					values.add((2*order+1), "-");
					break;
				}
				case 2:{
					values.add((2*order+1), "*");
					break;
				}
				case 3:{
					values.add((2*order+1), "/");
					break;
				}
				}
				
			}
		
		public static List<String> convertValue(List<String> list){
			String ColName;
			List<String> CellValues =new ArrayList();
			int Row=0;
			int Col = 0;
			for(String FormulaComponent: list){
				Pattern p = Pattern.compile("[a-zA-Z]"); //check input for coordinates
				Matcher m = p.matcher(FormulaComponent);
				int index=list.indexOf(FormulaComponent);
				
				if(m.find()){ //If coordinates is found
					ColName=Character.toString(FormulaComponent.charAt(0));
					FormulaComponent=FormulaComponent.substring(1); //cut uppercase letter
					
					Row=Integer.parseInt(FormulaComponent);
					Col=WorkArea.getColumnNameIndex(ColName);
		       
					String CellValue=(String) WorkArea.WorkTable.getModel().getValueAt(Row-1, Col);
						if(CellValue==null)
							CellValue="0";
		       
						CellValues.add(CellValue);
				}
		    else{
		    	CellValues.add(FormulaComponent);
		    	}
			}
			return CellValues;	
		}
		
			
			public static void performMathOperation(List<String> list, int row, int col){
				int FirstMultiplyIndex = 0;
				int FirstDivisionIndex = 0;
				int FirstAddIndex=0;
				int FirstDiffIndex=0;
			
				FirstMultiplyIndex=list.indexOf("*");
				FirstDivisionIndex =list.indexOf("/");
				FirstAddIndex=list.indexOf("+");
				FirstDiffIndex =list.indexOf("-");
				
				// * or /
				do
				{
						if(FirstMultiplyIndex>=0 && FirstDivisionIndex<0){
							multiplication(list, FirstMultiplyIndex);}
						
						if(FirstMultiplyIndex<0 && FirstDivisionIndex>=0){
							division(list, FirstDivisionIndex);}
						
						
						if(FirstMultiplyIndex>=0 && FirstDivisionIndex>=0){
							if(FirstMultiplyIndex<FirstDivisionIndex){
								multiplication(list, FirstMultiplyIndex);}	
							else{
								division(list, FirstDivisionIndex);
								}
							}
						FirstMultiplyIndex=list.indexOf("*");
						FirstDivisionIndex =list.indexOf("/");
						
				}while(FirstMultiplyIndex!=-1 || FirstDivisionIndex!=-1);
				
				
				// + or -
				FirstAddIndex=list.indexOf("+");
				FirstDiffIndex =list.indexOf("-");
				
				
				do
				{	
						if(FirstAddIndex>=0 && FirstDiffIndex>=0){
							if(FirstAddIndex<FirstDiffIndex){
								addition(list, FirstAddIndex);}	
							else{
								subtraction(list, FirstDiffIndex);
								}
							}
						
						if(FirstAddIndex>=0 && FirstDiffIndex<0){
							addition(list, FirstAddIndex);}
						
						if(FirstAddIndex<0 && FirstDiffIndex>=0){
							subtraction(list, FirstDiffIndex);}
						
						FirstAddIndex=list.indexOf("+");
						FirstDiffIndex =list.indexOf("-");
						
				}while(FirstAddIndex!=-1 || FirstDiffIndex!=-1);			
		
				WorkArea.WorkTable.getModel().setValueAt(list.get(0), row, col);
		}
			
				public static void multiplication(List<String> list, int index){
					double LeftFactor=Double.parseDouble(list.get(index-1));
					double RightFactor=Double.parseDouble(list.get(index+1));
					double Result=LeftFactor*RightFactor;
					
					list.remove(index-1);
					list.remove(index-1);
					list.remove(index-1);
					list.add(index-1, String.valueOf(Result));
				}
				public static void division(List<String> list, int index){
					double LeftFactor=Double.parseDouble(list.get(index-1));
					double RightFactor=Double.parseDouble(list.get(index+1));
					double Result=LeftFactor/RightFactor;
					
					list.remove(index-1);
					list.remove(index-1);
					list.remove(index-1);
					list.add(index-1, String.valueOf(Result));
				}
				public static void addition(List<String> list, int index){
					double LeftFactor=Double.parseDouble(list.get(index-1));
					double RightFactor=Double.parseDouble(list.get(index+1));
					double Result=LeftFactor+RightFactor;
					
					list.remove(index-1);
					list.remove(index-1);
					list.remove(index-1);
					list.add(index-1, String.valueOf(Result));
				}
				public static void subtraction(List<String> list, int index){
					double LeftFactor=Double.parseDouble(list.get(index-1));
					double RightFactor=Double.parseDouble(list.get(index+1));
					double Result=LeftFactor-RightFactor;
					
					list.remove(index-1);
					list.remove(index-1);
					list.remove(index-1);
					list.add(index-1, String.valueOf(Result));
				}
				
		
}
