
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	Board GoLBoard;
	
	public int length=30;
	public int width=30;
	
	
	public AppWindow() {
		
		CreateJframe();
		CreateJbutton();
		CreateJpanel();
		AddJpanel(BaseFrame, BasePanel);
		InitialFields();
		CreateSpace();
		ChangeFields();
	}

	void CreateJframe(){
		BaseFrame=new JFrame("Game of Life");
		BaseFrame.setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		BaseFrame.setVisible(true);
		BaseFrame.setBounds(10, 10, (int) (0.8*getToolkit().getScreenSize().width), (int) (0.8*getToolkit().getScreenSize().height));
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
		BoardPanel.setLayout(new GridLayout(length+1, width+1));
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
	
	//CREATE 2D JLABEL ARRAY
	void CreateSpace(){
		this.Space= new JLabel[length][width];
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				Space[i][j]=SetJLabel(this.Space[i][j], BoardPanel, i, j);			
			}
		}
	}
	//SET JLABELS PARAMETERS
	JLabel SetJLabel(JLabel label, JPanel panel, int x, int y){
		
		label= new JLabel();
		label.setBorder(new LineBorder(Color.BLACK));
		label.setMinimumSize(new Dimension(10,10));
		//label.setBackground(Color.darkGray);
		label.setOpaque(true);
		label.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    { 	
		    GoLBoard.GameBoard[x][y].UnitAlive();
		    DetermineUnitColor(GoLBoard.HowManyNeighbors(x, y), x, y);
		    ChangeFields();
		    }  
		}); 
		panel.add(label);
			return label;		
	}
	
	
	//INITIALIZE BOARD
	void InitialFields(){
		 GoLBoard= new Board(length, width);
		}
	
	//DRAWING BOARD ON SCREEN FROM BOARD OBJECT
	void ChangeFields()
	{
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
					if(GoLBoard.GameBoard[i][j].isAlive())
					
						DetermineUnitColor(GoLBoard.HowManyNeighbors(i, j), i, j);
						else
						Space[i][j].setBackground(new Color(200,0,0));
					}
			}		
	}
	
	void DetermineUnitColor(int value, int x, int y){
		switch(value){
		case 0: Space[x][y].setBackground(new Color(1, 249, 13));
			break;
		case 1: Space[x][y].setBackground(new Color(1, 229, 15));;
			break;	
		case 2: Space[x][y].setBackground(new Color(1, 209, 15));;
			break;
		case 3: Space[x][y].setBackground(new Color(1, 189, 13));
			break;
		case 4: Space[x][y].setBackground(new Color(1, 169, 13));
			break;
		case 5: Space[x][y].setBackground(new Color(1, 149, 13));
			break;
		case 6: Space[x][y].setBackground(new Color(1, 129, 13));
			break;
		case 7: Space[x][y].setBackground(new Color(1, 109, 13));
			break;
		case 8: Space[x][y].setBackground(new Color(1, 89, 13));
			break;
		
		}
		
	}

	//BUTTON ACTIONS
	@Override
	public void actionPerformed(ActionEvent E) {
		String ButtonName=E.getActionCommand();
		
		if(ButtonName.equals("Perform Cycle")){
			GoLBoard.NextTurn();
			
			for(int i=0; i<length; i++){
				for(int j=0; j<width; j++){
						if(GoLBoard.NextTurnBoard[i][j].isAlive())
							DetermineUnitColor(GoLBoard.HowManyNeighbors(i, j), i, j);
							else
							Space[i][j].setBackground(new Color(200,0,0));
						}
				}
		}
		if(ButtonName.equals("Close"))
			System.exit(0);
			
		
	}
	
	
	

}
