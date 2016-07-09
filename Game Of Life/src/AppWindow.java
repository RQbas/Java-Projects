
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AppWindow extends JFrame implements ActionListener
{

	private JFrame BaseFrame;
	
	private JPanel BasePanel;
	
	private JButton start;
	private JButton end;
	

	
	public AppWindow() {
		
		CreateJframe();
		CreateJbutton();
		CreateJpanel(BasePanel);
		AddJpanel(BaseFrame, BasePanel);
		
		
		
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
	
	void CreateJpanel(JPanel Panel){
		BasePanel= new JPanel(new GridBagLayout());
		BasePanel.setBackground(Color.WHITE);
		
		AddJbutton(BasePanel);
		
		
	}
	
	
	
	
	void AddJbuttonListener(JButton Button){
		Button.addActionListener(this);
	}
	
	void AddJbutton(JPanel Panel){
		GridBagConstraints Constraints= new GridBagConstraints();
		
		Constraints.insets= new Insets(10, 10, 10, 10);
			
		Constraints.gridx=0;
		Constraints.gridy=1;
		Panel.add(start, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=2;
		Panel.add(end, Constraints);
	}
	
	void AddJpanel(JFrame frame, JPanel panel){
		frame.add(panel, BorderLayout.SOUTH);
	}
	

	@Override
	public void actionPerformed(ActionEvent E) {
		String ButtonName=E.getActionCommand();
		if(ButtonName.equals("Perform Cycle"))
			System.out.println("Method for performing cycle");
		if(ButtonName.equals("Close"))
			System.exit(0);
			
		
	}
	

}
