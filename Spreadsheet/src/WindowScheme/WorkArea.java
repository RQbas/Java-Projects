package WindowScheme;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


import mouseListener.WorkAreaMouseListener;

public class WorkArea {
static JPanel panel;
static public JTable WorkTable;
static Object[][] rowData;
static public Object[] columnNames;
static final int RowNumber=151;
static final int ColumnNumber=27;

	static JPanel createWorkArea(Dimension ScreenSize){
		panel= new JPanel();
		panel.setBackground(new Color(216,216,216));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		panel.add(createScrollPane(ScreenSize));
		return panel;
	}
	static JScrollPane createScrollPane(Dimension Size){
		createTable();
		 JScrollPane scrollPane= new JScrollPane(createTable());
		 scrollPane.setPreferredSize(new Dimension((int) (0.98*Size.width), (int) (0.82*Size.height)));
		 scrollPane.getViewport().setBackground(new Color(255, 255, 255));
		return scrollPane;
	}
		static JTable createTable(){
			setRowData();
			setColumnNames();
			DefaultTableModel model = new  DefaultTableModel(rowData, columnNames);
			 WorkTable = new JTable();
			 	WorkTable.setModel(model);
			    WorkTable.setBackground(new Color(255, 255, 255));
			    WorkTable.setRowSelectionAllowed(true);
			    WorkTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			    WorkTable.setDefaultEditor(Object.class, null); //Blocks editing
			    WorkTable.addMouseListener(new WorkAreaMouseListener());
			    
			    return WorkTable;
		}
			static void setRowData(){
				rowData= new Object[RowNumber][ColumnNumber];
				for(int i=0; i<RowNumber; i++){
					rowData[i][0]=Integer.toString((i+1));
				}
			}
			static void setColumnNames(){
				columnNames= new Object[ColumnNumber];
				int LetterASCII=64;
				columnNames[0]="";
				for(int i=1; i<ColumnNumber; i++){
					columnNames[i]= Character.toString((char)(LetterASCII+i));
				}
			}
}
