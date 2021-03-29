package cn.shyshetxwh.swing.print;


import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintFrame extends JFrame{
    private PrintComponent canvas;
    private PrintRequestAttributeSet attributes;

    public PrintFrame()  {
        canvas=new PrintComponent();
        add(canvas,BorderLayout.CENTER);

        attributes=new HashPrintRequestAttributeSet();

        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("打印");
        buttonPanel.add(printButton);
        printButton.addActionListener(event->{
            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(canvas);
                if (job.printDialog(attributes))job.print(attributes);
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(PrintFrame.this,e);
            }
        });

        JButton pageSetButton = new JButton("页面设置");
        buttonPanel.add(pageSetButton);
        pageSetButton.addActionListener(event->{
            PrinterJob job = PrinterJob.getPrinterJob();
            job.pageDialog(attributes);
        });

        add(buttonPanel,BorderLayout.NORTH);
        pack();
    }
}

