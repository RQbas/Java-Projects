package WindowScheme.workArea.eventWorkArea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import WindowScheme.formulaBar.FormulaBar;
import WindowScheme.workArea.WorkArea;



public class MouseListenerWA implements MouseListener {
    static int selectedColIndex;
    static int selectedRowIndex;

    @Override
    public void mouseClicked(MouseEvent MouseClick) {
        if (MouseClick.getClickCount() == 1) {
            if (FormulaMode.FormulaModeOn) {
                setCoordinatesTextField(MouseClick);
                FormulaMode.getCoordinatesFormulaMode(MouseClick);
            } else {
                getActualPosition(MouseClick);
                setCoordinatesTextField(MouseClick);
                fillInputLine(MouseClick);
                FormulaMode.trySetFormulaMode(WorkArea.CellContent);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent MouseReleased) {
        WorkArea.ReleasedCol = WorkArea.WorkTable.columnAtPoint(MouseReleased.getPoint());
        WorkArea.ReleasedRow = WorkArea.WorkTable.rowAtPoint(MouseReleased.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent MousePressed) {
        if (!FormulaMode.FormulaModeOn)
            getActualPosition(MousePressed);

    }

    void setCoordinatesTextField(MouseEvent MouseClick) {
        selectedRowIndex = WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint()) + 1;
        selectedColIndex = WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());

        String ActualColumn = (String) WorkArea.columnNames[selectedColIndex];

        FormulaBar.Coordinates.setText(ActualColumn + selectedRowIndex);
    }

    void fillInputLine(MouseEvent MouseClick) {
        FormulaBar.InputLine.setHorizontalAlignment(JTextField.LEFT);
        WorkArea.CellContent =
                (String) WorkArea.WorkTable.getModel().getValueAt(selectedRowIndex - 1, selectedColIndex);
        FormulaBar.InputLine.setText(WorkArea.CellContent);
    }

    void getActualPosition(MouseEvent MouseClick) {
        WorkArea.ActualCol = WorkArea.WorkTable.columnAtPoint(MouseClick.getPoint());
        WorkArea.ActualRow = WorkArea.WorkTable.rowAtPoint(MouseClick.getPoint());
    }


    @Override
    public void mouseEntered(MouseEvent MouseEntered) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }



}
