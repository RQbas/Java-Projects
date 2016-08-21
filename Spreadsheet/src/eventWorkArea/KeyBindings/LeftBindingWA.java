package eventWorkArea.KeyBindings;

import java.awt.event.ActionEvent;

public class LeftBindingWA extends KeyBindingWA{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			getActualPosition();
			try{
				performKeyMovement(row,col-1);
				changeCellSelection(row, col);
				fillInputLine();
				setCoordinatesTextField();
				
			}catch(Exception OutOfBounds){}
	}

}
