package WindowScheme.standardToolbar.graphComponents;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import WindowScheme.execution.MainWindow;
import WindowScheme.workArea.WorkArea;

public class MouseListenerGraph extends Thread implements MouseListener {
    Graph gp;
    Timer timer;
    private int toolbarHeight;

    public MouseListenerGraph(Graph gp) {
        this.timer = new Timer();
        this.gp = gp;
        this.toolbarHeight = (int) (0.15 * MainWindow.getScreenSize().getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent mouseClicked) {
        if (SwingUtilities.isMiddleMouseButton(mouseClicked))
            WorkArea.workAreaLayers.moveToFront(mouseClicked.getComponent());

        if (SwingUtilities.isRightMouseButton(mouseClicked)) {
            gp.setGraphColors(new Color(218, 218, 218), gp.getRandomColor(), gp.getRandomColor());
            gp.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.timer.cancel();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent mousePressed) {
        if (SwingUtilities.isLeftMouseButton(mousePressed)) {
            int xPressed = mousePressed.getX();
            int yPressed = mousePressed.getY();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (mousePressed.getComponent().getLocationOnScreen().getY() < toolbarHeight) {
                        gp.setLocation((WorkArea.cursorXPosition - xPressed), 0);
                    } else if (WorkArea.cursorYPosition - yPressed > toolbarHeight) {
                        gp.setLocation(WorkArea.cursorXPosition - xPressed,
                                (WorkArea.cursorYPosition - yPressed - toolbarHeight));
                    } else {
                        gp.setLocation(WorkArea.cursorXPosition - xPressed, 0);
                    }
                    gp.repaint();
                }
            }, 0, 50);

        }
    }
}
