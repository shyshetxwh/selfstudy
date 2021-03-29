package cn.shyshetxwh.swing.table;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

public class PlanetTableFrame extends JFrame {
    private String[] columnNames={"Planet","Radius","Moons","Gaseous","Color"};
    private Object[][] cells=
            {
                    {"Mercury",2440.0,0,false, Color.YELLOW},
                    {"Venus",6052.0,0,false, Color.YELLOW},
                    {"Earth",6378.0,1,false, Color.BLUE},
                    { "Mars", 3397.0, 2, false, Color.RED },
                    { "Jupiter", 71492.0, 16, true, Color.ORANGE },
                    { "Saturn", 60268.0, 18, true, Color.ORANGE },
                    { "Uranus", 25559.0, 17, true, Color.BLUE },
                    { "Neptune", 24766.0, 8, true, Color.BLUE },
                    { "Pluto", 1137.0, 1, false, Color.BLACK }
            };

    public PlanetTableFrame()  {
        JTable table = new JTable(cells, columnNames);
        table.setAutoCreateRowSorter(true);
        add(new JScrollPane(table),BorderLayout.CENTER);
        JButton printButton = new JButton("Print");
        printButton.addActionListener(event->{
            try {
                table.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        add(buttonPanel,BorderLayout.SOUTH);
        pack();
    }
}
