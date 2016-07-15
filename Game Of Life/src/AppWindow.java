
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
import java.util.Timer;
import java.util.TimerTask;

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
	private JButton TimeButton;
	private JButton end;
	
	private JLabel[][] Space;
	private JLabel Info;

	
	private Timer timer;
	
	Board GoLBoard;
	
	public int length;
	public int width;
	
	public static int CycleNumber=0;
	public static int AddedUnits=0;
	public AppWindow(int BoardLength, int BoardWidth) {
		SetSpaceSize( BoardLength, BoardWidth);
		CreateJframe();
		
		CreateJbutton();
		CreateJpanel();
		AddJpanel(BaseFrame, BasePanel);
		InitialFields();
		
		CreateSpace();
		ChangeFields();
		
		SetJframe();
	}
	void CreateJframe(){
		BaseFrame=new JFrame("Game of Life");
		BaseFrame.setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		BaseFrame.setBounds(0, 0, (int) (0.8*getToolkit().getScreenSize().width), (int) (0.8*getToolkit().getScreenSize().height));
		BaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	void SetJframe(){
		BaseFrame.setVisible(true);
	}
	
	void CreateJbutton(){
		 start = new JButton("Perform 1 Cycle");
		 end = new JButton("Close");
		 TimeButton = new JButton("Run");
		 
		 
		 AddJbuttonListener(start);
		 AddJbuttonListener(end);
		 AddJbuttonListener(TimeButton);
	}
	
	void CreateJpanel(){
		BasePanel= new JPanel(new BorderLayout());
		BasePanel.setBackground(Color.WHITE);
		
		ButtonPanel=new JPanel(new GridBagLayout());
		ButtonPanel.setBackground(Color.GRAY);	
		SetButtonPanel(ButtonPanel);
		
		
		BoardPanel=new JPanel();
		BoardPanel.setLayout(new GridLayout(length+1, width+1));
		BoardPanel.setBackground(Color.lightGray);
		
		
		
		BasePanel.add(BoardPanel, BorderLayout.CENTER);
		BasePanel.add(ButtonPanel, BorderLayout.PAGE_END);
	}
	
	
	
	
	void AddJbuttonListener(JButton Button){
		Button.addActionListener(this);
	}
	
	void SetButtonPanel(JPanel Panel){
		Info= new JLabel();
		Info.setBackground(Color.lightGray);
		Info.setText("<html>Cycles performed: 0 <br> Units added: 0</html>");
		Info.setBorder(new LineBorder(Color.BLACK));
		Info.setOpaque(true);
	
		
		
		GridBagConstraints Constraints= new GridBagConstraints();
		Constraints.insets= new Insets(5, 5, 5, 5);
			
		Constraints.gridx=0;
		Constraints.gridy=0;
		ButtonPanel.add(start, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=1;
		ButtonPanel.add(TimeButton, Constraints);
		
		Constraints.gridx=0;
		Constraints.gridy=2;
		ButtonPanel.add(end, Constraints);
		
		Constraints.gridx=1;
		Constraints.gridy=1;
		ButtonPanel.add(Info, Constraints);
	}
	

	void AddJpanel(JFrame frame, JPanel panel){
		frame.add(panel);
	}
	
	//CREATE 2D JLABEL ARRAY
	void SetSpaceSize(int len, int wid){
		this.length=len;
		this.width=wid;
	}
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
		    	
		    	if(GoLBoard.GameBoard[x][y].isAlive()){
		    		AddedUnits--;
		    		GoLBoard.GameBoard[x][y].UnitDie();
				    DetermineUnitColor(GoLBoard.HowManyNeighbors(x, y), x, y);
				    ChangeFields();
		    	}
		    	else{
		    		AddedUnits++;
		    		GoLBoard.GameBoard[x][y].UnitAlive();
				    DetermineUnitColor(GoLBoard.HowManyNeighbors(x, y), x, y);
				    ChangeFields();
		    	}
		    	Info.setText("<html>Cycles performed: " +CycleNumber+ "<br> Units added: " +AddedUnits+"</html>");
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
	
	void PerformCycle(){
		GoLBoard.NextTurn();
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
					if(GoLBoard.NextTurnBoard[i][j].isAlive())
						DetermineUnitColor(GoLBoard.HowManyNeighbors(i, j), i, j);
						else
						Space[i][j].setBackground(new Color(200,0,0));
					}
			}
		CycleNumber++;
		Info.setText("<html>Cycles performed: " +CycleNumber+ "<br> Units added: " +AddedUnits+"</html>");
		
	}

	//BUTTON ACTIONS
	@Override
	public void actionPerformed(ActionEvent E) {
		String ButtonName=E.getActionCommand();
		
		if(ButtonName.equals("Perform 1 Cycle"))
				PerformCycle();
		if(ButtonName.equals("Run")){
			
			timer= new Timer();
			timer.schedule(new TimerTask() {
			    @Override
			    public void run() {
			       PerformCycle();
			    }
			}, 0, 500);
			TimeButton.setText("Stop");
			}
		if(ButtonName.equals("Stop")){
			timer.cancel();
			TimeButton.setText("Run");
		}
		
		if(ButtonName.equals("Close"))
			System.exit(0);
			
		
	}
	
	
	

}
