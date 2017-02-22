package WindowScheme.workArea.eventWorkArea;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.Timer;
import java.util.TimerTask;

import WindowScheme.workArea.WorkArea;

public class CursorThread extends Thread {
    public static CursorThread createCursorTracking() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PointerInfo pointer = MouseInfo.getPointerInfo();
                Point cursor = pointer.getLocation();
                WorkArea.cursorXPosition = (int) cursor.getX();
                WorkArea.cursorYPosition = (int) cursor.getY();
            }
        }, 0, 100);
        return (new CursorThread());
    }

}
