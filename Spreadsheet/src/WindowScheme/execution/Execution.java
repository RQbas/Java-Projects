package WindowScheme.execution;

import WindowScheme.workArea.eventWorkArea.CursorThread;

public class Execution {

    public static void main(String[] args) {
        new MainWindow();
        CursorThread.createCursorTracking().start();
    }
}
