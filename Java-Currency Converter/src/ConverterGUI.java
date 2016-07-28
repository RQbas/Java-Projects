import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ConverterGUI implements ActionListener {
	
	public ConverterGUI(ConverterCore core){
		SetJPanelGUI();
		SetRatioTable(core);
		SetListScroll(core);
		SetJScrollPanes(core);
		SetReverseButton();
		SetUserInput();
		SetOutput();
		
	}
	
	Object[] ListScroll;
	
	JList LeftList;
	JList RightList;
	
	JScrollPane ListScrollerLeft;
	JScrollPane ListScrollerRight;
	
	JButton ReverseButton;
	JButton AcceptButton;
	
	JTextField ValueInput;
	
	JLabel OutputLabel;
	
	JPanel PanelGUI;
	GridBagConstraints Constraints;
	
	int Initial_index;
	int Target_index;
	Boolean InitialClick=false;
	
	double Input;
	double Output;
	
	double[][]RatioTable;
	
void SetJPanelGUI(){
		PanelGUI= new JPanel(new GridBagLayout());
		PanelGUI.setBackground(new Color(157,157,157));
		PanelGUI.setPreferredSize(new Dimension(240, 710));
		PanelGUI.setVisible(true);
		Constraints= new GridBagConstraints();
		Constraints.anchor= GridBagConstraints.NORTH;
		
		Constraints.insets= new Insets(5, 5, 5, 5);
	}
	void SetListScroll(ConverterCore core)
	{
		ListScroll= new Object[core.CurrencyN];
			for(int i=0; i<core.CurrencyN; i++){
				ListScroll[i]=core.getCode(i);
			}
	}
	void SetRatioTable(ConverterCore core){
		RatioTable=core.setRatioTable(RatioTable);
	}
	
	public void SetJScrollPanes(ConverterCore core){
		
		LeftList = new JList(ListScroll); //data has type Object[]
		LeftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LeftList.setLayoutOrientation(JList.VERTICAL);
		LeftList.setVisibleRowCount(-1);
		LeftList.setBackground(new Color(240, 103, 11));
		
		DefaultListCellRenderer LeftRenderer =  (DefaultListCellRenderer)LeftList.getCellRenderer();  
		LeftRenderer.setHorizontalAlignment(SwingConstants.CENTER);  
		
		MouseListener LeftListListener = new MouseAdapter(){
			 public void mouseClicked(MouseEvent mouseEvent) {
			       if(mouseEvent.getClickCount()==1){
			    	   Initial_index = LeftList.locationToIndex(mouseEvent.getPoint());
			    	   	if(InitialClick==true){
			    		   ConverterApplet.table.getSelectionModel().clearSelection();
			    		   ConverterApplet.table.getSelectionModel().addSelectionInterval((core.CurrencyN+Target_index+1), (core.CurrencyN+Target_index+1));   
			    	   ConverterApplet.table.getSelectionModel().addSelectionInterval((core.CurrencyN+Initial_index+1), (core.CurrencyN+Initial_index+1));
			    	   ConverterApplet.table.scrollRectToVisible(new Rectangle(ConverterApplet.table.getCellRect(core.CurrencyN+Initial_index+1, 0, true)));
			    	   	}
			    	   		else{
			    	   ConverterApplet.table.getSelectionModel().addSelectionInterval((core.CurrencyN+Initial_index+1), (core.CurrencyN+Initial_index+1));
			    	   ConverterApplet.table.scrollRectToVisible(new Rectangle(ConverterApplet.table.getCellRect(core.CurrencyN+Initial_index+1, 0, true)));
			    	   InitialClick=true;
			    	   	}
			       	}
			      }	      
			   };
		LeftList.addMouseListener(LeftListListener);
		
		
		
		
		
		
		RightList = new JList(ListScroll); //data has type Object[]
		RightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		RightList.setLayoutOrientation(JList.VERTICAL);
		RightList.setVisibleRowCount(-1);
		RightList.setBackground(new Color(240, 103, 11));
		DefaultListCellRenderer RightRenderer =  (DefaultListCellRenderer)RightList.getCellRenderer();  
		RightRenderer.setHorizontalAlignment(SwingConstants.CENTER); 
		
		MouseListener RightListListener = new MouseAdapter(){
			 public void mouseClicked(MouseEvent mouseEvent) {
			       if(mouseEvent.getClickCount()==1){
			    	   Target_index = LeftList.locationToIndex(mouseEvent.getPoint());
			    		if(InitialClick==true){
			    		   ConverterApplet.table.getSelectionModel().clearSelection();
			    		   ConverterApplet.table.getSelectionModel().addSelectionInterval((core.CurrencyN+Initial_index+1), (core.CurrencyN+Initial_index+1));
			    	   ConverterApplet.table.getSelectionModel().addSelectionInterval((core.CurrencyN+Target_index+1), (core.CurrencyN+Target_index+1));
			           ConverterApplet.table.scrollRectToVisible(new Rectangle(ConverterApplet.table.getCellRect(core.CurrencyN+Target_index+1, 0, true)));
			    		}
			    		else{
				    	   ConverterApplet.table.getSelectionModel().addSelectionInterval((core.CurrencyN+Target_index+1), (core.CurrencyN+Target_index+1));
				           ConverterApplet.table.scrollRectToVisible(new Rectangle(ConverterApplet.table.getCellRect(core.CurrencyN+Target_index+1, 0, true)));
				           InitialClick=true;
			    		}
			       }
			     }	      
			    };
		RightList.addMouseListener(RightListListener);
		
		
		
		ListScrollerLeft = new JScrollPane(LeftList);
		ListScrollerLeft.setPreferredSize(new Dimension(60, 100));
		Constraints.gridx=0;
		Constraints.gridy=0;
		PanelGUI.add(ListScrollerLeft, Constraints);
		
		BufferedImage ArrowPicture;
		try {
			ArrowPicture = ImageIO.read(new File("arrow.png"));
			JLabel PictureLabel = new JLabel(new ImageIcon(ArrowPicture));
			PictureLabel.setPreferredSize(new Dimension(90, 85));
			Constraints.gridx=1;
			Constraints.gridy=0;
			PanelGUI.add(PictureLabel, Constraints);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		ListScrollerRight = new JScrollPane(RightList);
		ListScrollerRight.setPreferredSize(new Dimension(60, 100));
		Constraints.gridx=2;
		Constraints.gridy=0;
		PanelGUI.add(ListScrollerRight, Constraints);
		
	}
	
	public void SetReverseButton(){
		ReverseButton= new JButton();
		ReverseButton.setText("Reverse");
		ReverseButton.addActionListener(this);
		Constraints.gridx=1;
		Constraints.gridy=1;
		
		PanelGUI.add(ReverseButton, Constraints);
	}
	public void SetUserInput(){
		ValueInput = new JTextField("", 5);
		Constraints.gridx=0;
		Constraints.gridwidth=2;
		Constraints.gridy=2;
		PanelGUI.add(ValueInput, Constraints);
		
		AcceptButton= new JButton();
		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(this);
		Constraints.gridx=1;
		Constraints.gridy=2;
		PanelGUI.add(AcceptButton, Constraints);
	}
	public void SetOutput(){
	OutputLabel= new JLabel();
	OutputLabel.setBackground(new Color(240, 103, 11));
	OutputLabel.setPreferredSize(new Dimension(100, 30));
	OutputLabel.setOpaque(true);
	 Border border = BorderFactory.createLineBorder(Color.BLACK);
	OutputLabel.setBorder(border);
	OutputLabel.setPreferredSize(new Dimension(150, 30));
	OutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
	Constraints.gridx=0;
	Constraints.gridy=3;
	Constraints.gridwidth=3;
	PanelGUI.add(OutputLabel, Constraints);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ReverseButton) {
				int Index_Buffor;
				Index_Buffor= Target_index;
				Target_index=Initial_index;
				Initial_index=Index_Buffor;
				
				LeftList.setSelectedIndex(Initial_index);
				LeftList.ensureIndexIsVisible(LeftList.getSelectedIndex());
				
				RightList.setSelectedIndex(Target_index);
				RightList.ensureIndexIsVisible(RightList.getSelectedIndex());
				
				
		}
			
	  if(e.getSource() == AcceptButton){
		 String getValue= ValueInput.getText();
		 String processValue;
		 try{
			 processValue=getValue.replaceAll(",", ".");
			 Input=Double.parseDouble(processValue);
			 
			 Output=Input*RatioTable[Initial_index+1][Target_index+1];
			 OutputLabel.setText(Double.toString(ConverterCore.round(Output, 5)));
		 }catch(Exception exc){
			 OutputLabel.setText("Enter valid number");
			 ValueInput.setText("");
		 }
		 
		 
	  }
			
	}

}
