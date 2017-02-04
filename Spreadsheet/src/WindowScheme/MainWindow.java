package WindowScheme;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;

public class MainWindow {
	
		JFrame MainFrame;
		JPanel MainPanel;
	
		public MainWindow(){
			
			CreateMainFrame(GetScreenSize());
			CreateMainPanel();
			
			AddMenuBar(MenuBar.createMenuBar(), GetScreenSize());
			
			AddStandardToolbar(StandardToolbar.createStandardToolbar(),  GetScreenSize());
			
			AddFormattingToolbar(FormattingToolbar.createFormattingToolbar(),  GetScreenSize());
			
			AddFormulaBar(FormulaBar.createFormulaBar(GetScreenSize()),  GetScreenSize());
			
			AddWorkArea(WorkArea.createWorkArea(GetScreenSize()),  GetScreenSize());
			
			SetJframe();
		}
		
		Dimension GetScreenSize(){
			Dimension ScreenSize;
			Toolkit tk = Toolkit.getDefaultToolkit();
			ScreenSize=tk.getScreenSize();
			
			return ScreenSize;
		}
		void CreateMainFrame(Dimension ScreenSize){
			
			MainFrame=new JFrame("Spreadsheet");
			MainFrame.setSize(ScreenSize.width, ScreenSize.height);
			MainFrame.setBounds(0, 0, (int) (ScreenSize.width), (int) (ScreenSize.height));
			MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		}
		void CreateMainPanel(){
			
			
			
			MainPanel= new JPanel();
			MainPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
			MainPanel.setBackground(Color.GRAY);	
			MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
			
		}
		
		
		void AddMenuBar(JPanel MenuBar, Dimension ScreenSize){
			MenuBar.setMaximumSize(new Dimension((int) ScreenSize.width, (int) (0.03*ScreenSize.height)));
			MainPanel.add(MenuBar);
		}
		void AddStandardToolbar(JPanel StandardToolbar, Dimension ScreenSize){
			StandardToolbar.setMaximumSize(new Dimension((int) ScreenSize.width, (int) (0.04*ScreenSize.height)));
			MainPanel.add(StandardToolbar);
		}
		void AddFormulaBar(JPanel FormulaBar, Dimension ScreenSize) {
			FormulaBar.setMaximumSize(new Dimension((int) ScreenSize.width, (int) (0.04*ScreenSize.height)));
			MainPanel.add(FormulaBar);
			
		}
		void AddFormattingToolbar(JPanel FormattingToolbar, Dimension ScreenSize) {
			FormattingToolbar.setMaximumSize(new Dimension((int) ScreenSize.width, (int) (0.04*ScreenSize.height)));
			MainPanel.add(FormattingToolbar);
			
		}
		void AddWorkArea(JScrollPane WorkArea, Dimension ScreenSize) {
			WorkArea.setMaximumSize(new Dimension((int) ScreenSize.width, (int) (0.85*ScreenSize.height)));
			MainPanel.add(WorkArea);
		}
		
		
		
		static void SetRigidArea(JPanel Panel, Dimension Size, double x, double y){
			Panel.add(Box.createRigidArea(new Dimension((int) (x*Size.width), (int) (y*Size.height))));
		}
		
		
		void SetJframe(){
			MainFrame.add(MainPanel);
			MainFrame.setVisible(true);
		}
	
	
	
	
}
