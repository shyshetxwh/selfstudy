package cn.shyshetxwh.swing.tableCellRender;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class PlanetTableModel extends AbstractTableModel {
    public static final int PLANET_COLUMN = 0;
    public static final int MOONS_COLUMN = 2;
    public static final int GASEOUS_COLUMN = 3;
    public static final int COLOR_COLUMN = 4;

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
    @Override
    public int getRowCount() {
        return cells.length;
    }

    @Override
    public int getColumnCount() {
        return cells[0].length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return cells[r][c];
    }

    @Override
    public String getColumnName(int c) {
        return columnNames[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return cells[0][c].getClass();
    }

    @Override
    public void setValueAt(Object aValue, int r, int c) {
        cells[r][c]=aValue;
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return c == PLANET_COLUMN || c == MOONS_COLUMN || c == GASEOUS_COLUMN
                || c == COLOR_COLUMN;
    }
}
