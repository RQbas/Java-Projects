import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuWindow extends JFrame implements ActionListener{

	JFrame MenuFrame;
	JPanel MenuPanel;
	
	JButton Default;
	JButton Start;
	JButton Exit;
	
	JLabel InfoInput;
	
	JTextField Input;
	
	private String UserInput;
	
	public MenuWindow() {
		CreateJframe();
		CreateJbutton();
		CreateTextField();
		CreateJpanel();
		AddJpanel(MenuFrame, MenuPanel);
		
		SetJframe();
	}

	
	void CreateJframe(){

		
		MenuFrame=new JFrame("Game of Life");
		MenuFrame.setLocation(0, 0);
		MenuFrame.setSize(250, 300);
		MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

void CreateJbutton(){
	Start = new JButton("Start");
	Default = new JButton("Default");
	Exit = new JButton("Exit");
	 
	 
	 AddJbuttonListener(Start);
	 AddJbuttonListener(Exit);
	 AddJbuttonListener(Default);
	}
void AddJbuttonListener(JButton Button){
	Button.addActionListener(this);
}
	void CreateJpanel(){
		MenuPanel= new JPanel(new GridBagLayout());
		MenuPanel.setBackground(new Color(176, 207, 220));
		SetMenuPanel();
	}
	
	void SetMenuPanel(){
		InfoInput= new JLabel();
		InfoInput.setText("Length of side");
		InfoInput.setVisible(false);
		GridBagConstraints Constraints= new GridBagConstraints();
		Constraints.insets= new Insets(5, 5, 5, 5);
		
		Constraints.gridx=0;
		Constraints.gridy=0;
		MenuPanel.add(InfoInput, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=1;
		MenuPanel.add(Input, Constraints);
		
		
		Constraints.gridx=0;
		Constraints.gridy=2;
		MenuPanel.add(Start, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=3;
		MenuPanel.add(Default, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=4;
		MenuPanel.add(Exit, Constraints);
	}
	
	void AddJpanel(JFrame frame, JPanel panel){
		frame.add(panel);
	}
	void CreateTextField(){
		Input= new JTextField(5);
		Input.setVisible(false);
	}
	
	void SetJframe(){
		MenuFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String ButtonName=e.getActionCommand();
		
		if(ButtonName.equals("Start")){
			InfoInput.setVisible(true);
			Input.setVisible(true);
			Start.setText("Accept");
		}
		if(ButtonName.equals("Accept")){
			UserInput=Input.getText();
			try{
				int input=Integer.parseInt(UserInput);
				new AppWindow(input, input);
				MenuFrame.dispose();
			}catch(NumberFormatException nfe){
				{
					InfoInput.setText("Enter integer number!");
					Input.setText("");
				}
			}
		}
		if(ButtonName.equals("Default")){
			new AppWindow(50, 50);
			MenuFrame.dispose();
		}
		if(ButtonName.equals("Exit"))
			System.exit(0);
	}

	
	
}
