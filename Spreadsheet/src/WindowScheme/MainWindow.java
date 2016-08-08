package WindowScheme;
import javax.swing.*;
import java.awt.*;

public class MainWindow {
	
		JFrame MainFrame;
		JPanel MainPanel;
	
		public MainWindow(){
			
			CreateMainFrame(GetScreenSize());
			CreateMainPanel();
			AddMenuBar(MenuBar.CreateMenuBar());
			//AddStandardToolbar();
			//AddFormattingToolbar();
			//AddFormulaBar();
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
			MainPanel.setBackground(Color.CYAN);	
		}
		
		
		void AddMenuBar(JPanel MenuBar){
			MainPanel.add(MenuBar);
		}
		void AddStandardToolbar(JPanel StandardToolbar){
			MainPanel.add(StandardToolbar);
		}
		void AddFormulaBar(JPanel FormulaBar) {
			MainPanel.add(FormulaBar);
			
		}
		void AddFormattingToolbar(JPanel FormattingToolbar) {
			MainPanel.add(FormattingToolbar);
			
		}
		
		
		
		
		
		
		void SetJframe(){
			MainFrame.add(MainPanel);
			MainFrame.setVisible(true);
		}
	
	
	
	
}
