package WindowScheme.workArea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import WindowScheme.workArea.KeyBindings.DownBindingWA;
import WindowScheme.workArea.KeyBindings.EnterBindingWA;
import WindowScheme.workArea.KeyBindings.LeftBindingWA;
import WindowScheme.workArea.KeyBindings.RightBindingWA;
import WindowScheme.workArea.KeyBindings.TabBindingWA;
import WindowScheme.workArea.KeyBindings.UpBindingWA;
import WindowScheme.workArea.eventWorkArea.DocumentListenerWA;
import WindowScheme.workArea.eventWorkArea.MouseListenerWA;

public class WorkArea {
    public static JLayeredPane workAreaLayers;
    static JPanel panel;
    static public JTable WorkTable;
    static public DefaultCellEditor JTableEditor;
    static Object[][] rowData;
    static public Object[] columnNames;
    static final int RowNumber = 151;
    static final int ColumnNumber = 27;


    public static int ActualRow;
    public static int ActualCol;

    public static int ReleasedRow;
    public static int ReleasedCol;

    public static int cursorXPosition;
    public static int cursorYPosition;


    public static String CellContent;

    public static JLayeredPane createWorkArea(Dimension ScreenSize) {
        panel = new JPanel();
        workAreaLayers = new JLayeredPane();
        workAreaLayers.setLayout(null);
        workAreaLayers.setPreferredSize((new Dimension((int) ScreenSize.width, (int) (0.85 * ScreenSize.height))));

        panel.setBackground(new Color(216, 216, 216));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        panel.add(createScrollPane(ScreenSize));
        panel.setBounds(0, 0, (int) ScreenSize.width, (int) ScreenSize.height);

        workAreaLayers.add(panel, new Integer(0), 0);



        return workAreaLayers;
    }

    static JScrollPane createScrollPane(Dimension Size) {
        createTable();
        JScrollPane scrollPane = new JScrollPane(createTable());
        scrollPane.setPreferredSize(new Dimension((int) (0.98 * Size.width), (int) (0.83 * Size.height)));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        return scrollPane;
    }

    static JTable createTable() {
        setRowData();
        setColumnNames();
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTableEditor = new DefaultCellEditor(setJTableCell());
        WorkTable = new JTable();
        WorkTable.setModel(model);
        WorkTable.setBackground(new Color(255, 255, 255));
        WorkTable.setRowSelectionAllowed(true);
        WorkTable.setCellSelectionEnabled(true);
        WorkTable.setDefaultEditor(Object.class, JTableEditor);
        WorkTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        WorkTable.addMouseListener(new MouseListenerWA());
        setKeyBinding();
        return WorkTable;
    }

    static void setRowData() {
        rowData = new Object[RowNumber][ColumnNumber];
        for (int i = 0; i < RowNumber; i++) {
            rowData[i][0] = Integer.toString((i + 1));
        }
    }

    static void setColumnNames() {
        columnNames = new Object[ColumnNumber];
        int LetterASCII = 64;
        columnNames[0] = "";
        for (int i = 1; i < ColumnNumber; i++) {
            columnNames[i] = Character.toString((char) (LetterASCII + i));
        }
    }

    public static int getColumnNameIndex(String s) {
        int index = 0;
        for (int i = 0; i < columnNames.length; i++) {
            if (s.equals(WorkArea.columnNames[i])) {
                index = i;
            }
        }
        return index;
    }

    public static JTextField setJTableCell() {
        JTextField CellTextField;
        CellTextField = new JTextField("");
        CellTextField.getDocument().addDocumentListener(new DocumentListenerWA(CellTextField));
        return CellTextField;
    }

    public static void setKeyBinding() {
        // Enter
        WorkTable.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "handleEnter");
        WorkTable.getActionMap().put("handleEnter", new EnterBindingWA());
        // Right
        WorkTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "handleRight");
        WorkTable.getActionMap().put("handleRight", new RightBindingWA());
        // Left
        WorkTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "handleLeft");
        WorkTable.getActionMap().put("handleLeft", new LeftBindingWA());
        // Up
        WorkTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "handleUP");
        WorkTable.getActionMap().put("handleUP", new UpBindingWA());
        // Down
        WorkTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "handleDown");
        WorkTable.getActionMap().put("handleDown", new DownBindingWA());
        // Tab
        WorkTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "handleTab");
        WorkTable.getActionMap().put("handleTab", new TabBindingWA());
    }



}
