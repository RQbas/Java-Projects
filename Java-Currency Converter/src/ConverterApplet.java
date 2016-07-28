import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConverterApplet extends JApplet {
	ConverterCore core;
	ConverterGUI gui;
	
	JPanel MainPanel;
	static JTable table;
	
	
	Object rowData[][];
	DefaultTableModel model;
	public void init(){
		
	 core=new ConverterCore();
	 CreateTable(); //Create Table with currencies
	 
	 this.add(CreateTablePanel()); //Set JPanel
	 this.add(CreateScrollPane(), BorderLayout.LINE_START); //Create JscrollPane and adds it to Jpanel
	}
	
	public JPanel CreateTablePanel(){
		
		MainPanel = new JPanel();
		MainPanel.setBounds(10, 10, 230, 710);
		MainPanel.setBackground(new Color(57,57,57));
		MainPanel.setVisible(true);
		
		gui= new ConverterGUI(core);
		MainPanel.add(gui.PanelGUI, BorderLayout.LINE_END);
		
		return MainPanel;
	}
	public JScrollPane CreateScrollPane(){
		CreateTable();
		 JScrollPane scrollPane= new JScrollPane(CreateTable());
		 scrollPane.setPreferredSize(new Dimension(230, 710));
		 scrollPane.getViewport().setBackground(new Color(240, 103, 11));
		 
		return scrollPane;
}	
	public JTable CreateTable(){
			rowData = SetRowData();
		    Object[] columnNames = {"Foreign Currency", "PLN Equivalent"};
		    model = new DefaultTableModel(rowData, columnNames);
		    table = new JTable(model);
		    table.setBackground(new Color(240, 103, 11));
		    table.setRowSelectionAllowed(true);
		    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    table.setDefaultEditor(Object.class, null); //Blocks editing
		    table.addMouseListener(TableClick);
		    return table;
	}
			public Object[][] SetRowData(){
					Object[][] Array = new Object[2*core.CurrencyN+1][2];
						for(int i=0; i<core.CurrencyN; i++){
								Array[i][0]=core.DisplayLeftSide(i);
								Array[i][1]=core.DisplayRightSide(i);
								}	
						for(int i=core.CurrencyN; i<core.CurrencyN+1; i++){
								Array[i][0]="";
								Array[i][1]="";
								}
						for(int i=core.CurrencyN+1; i<2*core.CurrencyN+1; i++){
								Array[i][0]=core.getCode(i-(core.CurrencyN+1));
								Array[i][1]=core.getName(i-(core.CurrencyN+1));
								}
						return Array;
						}
	

MouseListener TableClick= new MouseAdapter(){
	 public void mouseClicked(MouseEvent mouseEvent) {
		 	String CellResult;
	       if(mouseEvent.getClickCount()==1){
	    	   int row = table.rowAtPoint(mouseEvent.getPoint());
	    	   int col = table.columnAtPoint(mouseEvent.getPoint());
	    	  
	    	    if(row<core.CurrencyN){
	    	    	
	    	    	 try{
	    	  			 String getValue= gui.ValueInput.getText();
	    		  		 String processValue;
	    	  			 processValue=getValue.replaceAll(",", ".");
	    	  			 gui.Input=Double.parseDouble(processValue);
	    	  		 }catch(Exception exc){
	    	  			 gui.OutputLabel.setText("Enter valid number");
	    	  			 CellResult="Enter valid number";
	    	  		 }
	    	    	
	    	    	if(model.getValueAt(row, col).equals(core.DisplayRightSide(row))){
	    	    		CellResult=Double.toString(ConverterCore.round(gui.Input*core.getRate(row)/core.getConverter(row), 4))+"[Conv.]";
	    	    		
	    	    		try{
	    	    			model.setValueAt((Object) (gui.Input +" "+core.getCode(row)), row, col-1);
	    	    			model.setValueAt((Object) (CellResult), row, col);
	    	    			}
	    	    		catch(Exception e){
	    	    			model.setValueAt((Object) (CellResult), row, col);
	    	    			model.setValueAt((Object) (gui.Input +" "+core.getCode(row)), row, col);
	    	    			}
	    	    	}
	    	    	else{
	    	    		CellResult=core.DisplayRightSide(row);

	    	    		try{
	    	    			model.setValueAt((Object) (core.DisplayLeftSide(row)), row, col-1);
	    	    			model.setValueAt((Object) (CellResult), row, col);
	    	    			}
	    	    		catch(Exception e){
	    	    			model.setValueAt((Object) (CellResult), row, col);
	    	    			model.setValueAt((Object) (core.DisplayLeftSide(row)), row, col);
	    	    			}
	    	    		
	    	    	}
	    	    }
	       }
	       }
	   };
	
	   }
