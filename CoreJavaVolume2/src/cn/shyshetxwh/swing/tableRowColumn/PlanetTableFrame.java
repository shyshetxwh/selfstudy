package cn.shyshetxwh.swing.tableRowColumn;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class PlanetTableFrame extends JFrame {
    private static final int DEFAULT_WIDTH=600;
    private static final int DEFAULT_HEIGHT=500;

    public static final int COLOR_COLUMN=4;
    public static final int IMAGE_COLUMN=5;

    private JTable table;
    private HashSet<Integer> removedRowIndices;
    private ArrayList<TableColumn> removedColumns;
    private JCheckBoxMenuItem rowItem;
    private JCheckBoxMenuItem columnsItem;
    private JCheckBoxMenuItem cellsItem;

    private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color", "Image" };
    private Object[][] cells = {
            { "Mercury", 2440.0, 0, false, Color.YELLOW,
                    new ImageIcon(getClass().getClassLoader().getResource("Mercury.gif")) },
            { "Venus", 6052.0, 0, false, Color.YELLOW,
                    new ImageIcon(getClass().getClassLoader().getResource("Venus.gif")) },
            { "Earth", 6378.0, 1, false, Color.BLUE,
                    new ImageIcon(getClass().getClassLoader().getResource("Earth.gif")) },
            { "Mars", 3397.0, 2, false, Color.RED,
                    new ImageIcon(getClass().getClassLoader().getResource("Mars.gif")) },
            { "Jupiter", 71492.0, 16, true, Color.ORANGE,
                    new ImageIcon(getClass().getClassLoader().getResource("Jupiter.gif")) },
            { "Saturn", 60268.0, 18, true, Color.ORANGE,
                    new ImageIcon(getClass().getClassLoader().getResource("Saturn.gif")) },
            { "Uranus", 25559.0, 17, true, Color.BLUE,
                    new ImageIcon(getClass().getClassLoader().getResource("Uranus.gif")) },
            { "Neptune", 24766.0, 8, true, Color.BLUE,
                    new ImageIcon(getClass().getClassLoader().getResource("Neptune.gif")) },
            { "Pluto", 1137.0, 1, false, Color.BLACK,
                    new ImageIcon(getClass().getClassLoader().getResource("Pluto.gif")) } };

    public PlanetTableFrame()  {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        TableModel model=new DefaultTableModel(cells,columnNames)
        {
            @Override
            public Class<?> getColumnClass(int c) {
                return cells[0][c].getClass();
            }
        };

        table=new JTable(model);
        table.setRowHeight(100);
        table.getColumnModel().getColumn(COLOR_COLUMN).setMinWidth(250);
        table.getColumnModel().getColumn(IMAGE_COLUMN).setMinWidth(100);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        sorter.setComparator(COLOR_COLUMN, Comparator.comparing(Color::getBlue).thenComparing(Color::getGreen).thenComparing(Color::getRed));
        sorter.setSortable(IMAGE_COLUMN,false);
        add(new JScrollPane(table),BorderLayout.CENTER);

        removedRowIndices=new HashSet<>();
        removedColumns=new ArrayList<>();

        RowFilter<TableModel,Integer>filter=new RowFilter<TableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                return !removedRowIndices.contains(entry.getIdentifier());
            }
        };

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu selectionMenu = new JMenu("Selection");
        menuBar.add(selectionMenu);

        rowItem=new JCheckBoxMenuItem("Rows");
        columnsItem = new JCheckBoxMenuItem("Columns");
        cellsItem = new JCheckBoxMenuItem("Cells");

        rowItem.setSelected(table.getRowSelectionAllowed());
        columnsItem.setSelected(table.getColumnSelectionAllowed());
        cellsItem.setSelected(table.getCellSelectionEnabled());

        rowItem.addActionListener(event->{
            table.clearSelection();
            table.setRowSelectionAllowed(rowItem.isSelected());
            updateCheckboxMenuItems();
        });
        selectionMenu.add(rowItem);

        columnsItem.addActionListener(event ->
        {
            table.clearSelection();
            table.setColumnSelectionAllowed(columnsItem.isSelected());
            updateCheckboxMenuItems();
        });
        selectionMenu.add(columnsItem);

        cellsItem.addActionListener(event ->
        {
            table.clearSelection();
            table.setCellSelectionEnabled(cellsItem.isSelected());
            updateCheckboxMenuItems();
        });
        selectionMenu.add(cellsItem);

        JMenu tableMenu = new JMenu("Edit");
        menuBar.add(tableMenu);

        JMenuItem hcItem = new JMenuItem("Hide Columns");
        hcItem.addActionListener(event->{
            int[] selected = table.getSelectedColumns();
            //System.out.println(Arrays.toString(selected));
            TableColumnModel columnModel = table.getColumnModel();
            for (int i = selected.length-1; i >=0; i--) {
                TableColumn column = columnModel.getColumn(selected[i]);
                //System.out.println(column.getIdentifier());
                table.removeColumn(column);
                removedColumns.add(column);
            }
        });
        tableMenu.add(hcItem);

        JMenuItem scItem = new JMenuItem("Show Columns");
        scItem.addActionListener(event->{
            for (TableColumn column : removedColumns) {
                table.addColumn(column);
            }
            removedColumns.clear();
        });
        tableMenu.add(scItem);

        JMenuItem hrItem = new JMenuItem("Hide Rows");
        hrItem.addActionListener(event->{
            int[] selected = table.getSelectedRows();
            for (int i : selected) {
                removedRowIndices.add(table.convertRowIndexToModel(i));
            }
            sorter.setRowFilter(filter);
        });
        tableMenu.add(hrItem);

        JMenuItem srItem = new JMenuItem("Show Rows");
        srItem.addActionListener(event->{
            removedRowIndices.clear();
            sorter.setRowFilter(filter);
        });
        tableMenu.add(srItem);

        JMenuItem psItem = new JMenuItem("Print Selection");
        psItem.addActionListener(event->{
            int[] selected = table.getSelectedRows();
            System.out.println("Selected rows: "+ Arrays.toString(selected));
            selected=table.getSelectedColumns();
            System.out.println("Selected columns: "+ Arrays.toString(selected));

        });
        tableMenu.add(psItem);
    }

    private void updateCheckboxMenuItems() {
        rowItem.setSelected(table.getRowSelectionAllowed());
        columnsItem.setSelected(table.getColumnSelectionAllowed());
        cellsItem.setSelected(table.getCellSelectionEnabled());
    }
}
