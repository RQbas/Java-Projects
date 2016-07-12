
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class AppWindow extends JFrame implements ActionListener
{

	private JFrame BaseFrame;
	
	private JPanel BasePanel;
	private JPanel ButtonPanel;
	private JPanel BoardPanel;
	
	private JButton start;
	private JButton end;
	
	private JLabel[][] Space;
	
	public int length=10;
	public int width=10;
	
	public AppWindow() {
		
		CreateJframe();
		CreateJbutton();
		CreateJpanel();
		AddJpanel(BaseFrame, BasePanel);
		CreateSpace();
		
		
	}

	void CreateJframe(){
		BaseFrame=new JFrame("Game of Life");
		BaseFrame.setSize(1024,768);
		BaseFrame.setVisible(true);
		BaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void CreateJbutton(){
		 start = new JButton("Perform Cycle");
		 end = new JButton("Close");
		 
		 AddJbuttonListener(start);
		 AddJbuttonListener(end);
		 
	}
	
	void CreateJpanel(){
		BasePanel= new JPanel(new BorderLayout());
		BasePanel.setBackground(Color.WHITE);
		
		ButtonPanel=new JPanel(new GridBagLayout());
		ButtonPanel.setBackground(Color.GRAY);
				
		AddJbutton(ButtonPanel);
		
		BoardPanel=new JPanel();
		BoardPanel.setLayout(new GridLayout(length, width));
		BoardPanel.setBackground(Color.lightGray);
		
		BasePanel.add(BoardPanel, BorderLayout.CENTER);
		BasePanel.add(ButtonPanel, BorderLayout.PAGE_END);
		
		
	}
	
	
	
	
	void AddJbuttonListener(JButton Button){
		Button.addActionListener(this);
	}
	
	void AddJbutton(JPanel Panel){
		GridBagConstraints Constraints= new GridBagConstraints();
		
		Constraints.insets= new Insets(10, 10, 10, 10);
			
		Constraints.gridx=0;
		Constraints.gridy=1;
		ButtonPanel.add(start, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=2;
		ButtonPanel.add(end, Constraints);
	}
	
	
	void AddJpanel(JFrame frame, JPanel panel){
		frame.add(panel);
	}
	
	void CreateSpace(){
		Space= new JLabel[length][width];
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				SetJLabel(Space[i][j], BoardPanel);
			}
		}
	}
	void SetJLabel(JLabel label, JPanel panel){
		label= new JLabel();
		label.setBorder(new LineBorder(Color.BLACK));
		//label.setBackground(Color.darkGray);
		label.setOpaque(true);
		label.setSize(10, 10);
		panel.add(label);
	}
	/*
	void ChangeFields(){
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				Space[i][j].setText("Kek");
			}
		}
	}
	*/

	@Override
	public void actionPerformed(ActionEvent E) {
		String ButtonName=E.getActionCommand();
		if(ButtonName.equals("Perform Cycle")){
			System.out.println("Method for performing cycle");
			
		}
		if(ButtonName.equals("Close"))
			System.exit(0);
			
		
	}
	
	
	

}
