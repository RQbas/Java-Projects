package WindowScheme.workArea.KeyBindings;

import java.awt.event.ActionEvent;

public class RightBindingWA extends KeyBindingWA{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			getActualPosition();
			try{
				performKeyMovement(row, col+1);
				changeCellSelection(row, col);
				fillInputLine();
				setCoordinatesTextField();
				
			}catch(Exception OutOfBounds){}
	}

}
