package WindowScheme.standardToolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import WindowScheme.workArea.WorkArea;
import WindowScheme.workArea.eventWorkArea.*;
import WindowScheme.standardToolbar.graphComponents.*;


public class GraphButton extends JButton{
	
	public GraphButton(){
		super();
		createGraphButton();
	}
	
	
	
	public void createGraphButton(){
		this.setText("Graph");
		this.addActionListener(buttonActionListener());
	}

	private ActionListener buttonActionListener() {
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				createGraph();
			}	
		};
	}
	 void createGraph(){
		WorkArea.graphsCount++;
		WorkArea.workAreaLayers.add(new Graph(getGraphPoints()), new Integer(WorkArea.graphsCount), 0);
		WorkArea.workAreaLayers.repaint();
	}
	 
	 
	 private ArrayList<GraphPoint> getGraphPoints() {
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
			double x, y;
			
				if((WorkArea.ActualCol - WorkArea.ReleasedCol) == -1){
					if(WorkArea.ActualRow < WorkArea.ReleasedRow)
						graphPoints = pointsFromDirectionSouthEastVertical();
					else
						graphPoints = pointsFromDirectionNorthEastVertical();
				}
				if((WorkArea.ActualCol - WorkArea.ReleasedCol) == 1 ){
					if(WorkArea.ActualRow > WorkArea.ReleasedRow)
						graphPoints = pointsFromDirectionNorthWestVertical();
					else
						graphPoints = pointsFromDirectionSouthWestVertical();				
				}
				if((WorkArea.ActualRow - WorkArea.ReleasedRow) == -1){
					if(WorkArea.ActualCol < WorkArea.ReleasedCol)
						graphPoints = pointsFromDirectionSouthEastHorizontal();
					else
						graphPoints = pointsFromDirectionSouthWestHorizontal();
				}
				if((WorkArea.ActualRow - WorkArea.ReleasedRow) == 1){
					if(WorkArea.ActualCol > WorkArea.ReleasedCol)
						graphPoints = pointsFromDirectionNorthWestHorizontal();
					else
						graphPoints = pointsFromDirectionNorthEastHorizontal();
				}
				
				
				int size = graphPoints.size();
				WorkArea.WorkTable.setValueAt(graphPoints.get(size-1).toString(), 15, 5);
					return graphPoints;
				}
	 
	 ArrayList<GraphPoint> pointsFromDirectionSouthEastVertical(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualRow; i<=WorkArea.ReleasedRow; i++){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ActualCol).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ReleasedCol).toString());
				}catch(Exception e){
					x=0;
					y=0;
				}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionNorthWestVertical(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualRow; i>=WorkArea.ReleasedRow; i--){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ActualCol).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ReleasedCol).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionSouthEastHorizontal(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualCol; i<=WorkArea.ReleasedCol; i++){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(WorkArea.ActualRow, i).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt( WorkArea.ReleasedRow, i).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionNorthWestHorizontal(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualCol; i>=WorkArea.ReleasedCol; i--){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(WorkArea.ActualRow, i).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt(WorkArea.ReleasedRow, i).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionSouthWestVertical(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualRow; i<=WorkArea.ReleasedRow; i++){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ActualCol).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ReleasedCol).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionNorthEastVertical(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualRow; i>=WorkArea.ReleasedRow; i--){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ActualCol).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt(i, WorkArea.ReleasedCol).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionNorthEastHorizontal(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualCol; i<=WorkArea.ReleasedCol; i++){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(WorkArea.ActualRow, i).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt(WorkArea.ReleasedRow, i).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
	 
	 ArrayList<GraphPoint> pointsFromDirectionSouthWestHorizontal(){
		 ArrayList<GraphPoint> graphPoints = new ArrayList<GraphPoint>();
		 double x, y;
		 for(int i = WorkArea.ActualCol; i>=WorkArea.ReleasedCol; i--){
				try{
				x = Double.parseDouble(WorkArea.WorkTable.getValueAt(WorkArea.ActualRow, i).toString());
				y = Double.parseDouble(WorkArea.WorkTable.getValueAt( WorkArea.ReleasedRow, i).toString());
			}catch(Exception e){
				x=0;
				y=0;
			}
				graphPoints.add(new GraphPoint(x, y));
			}
		 return graphPoints;
	 }
}
