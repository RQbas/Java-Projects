package WindowScheme.workArea.KeyBindings;

import java.awt.event.ActionEvent;

public class DownBindingWA extends KeyBindingWA{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			getActualPosition();
			try{
				performKeyMovement(row+1, col);
				changeCellSelection(row, col);
				fillInputLine();
				setCoordinatesTextField();
				
			}catch(Exception OutOfBounds){}
	}

}
