package eventWorkArea.KeyBindings;

import java.awt.event.ActionEvent;

public class TabBindingWA extends KeyBindingWA{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			getActualPosition();
			try{
				
				performKeyMovement(row, col+1);
				changeCellSelection(row, col);
				fillInputLine();
				setCoordinatesTextField();
				
			}catch(Exception OutOfColumn){
				try{
					performKeyMovement(row+1, 1);
					changeCellSelection(row, col);
					fillInputLine();
					setCoordinatesTextField();
				}catch(Exception OutOfRow){}
			}
	}

}