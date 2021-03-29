package cn.shyshetxwh.swing.book;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class PrintPreviewDialog extends JDialog {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    private PrintPreviewCanvas canvas;

    public PrintPreviewDialog(Printable p, PageFormat pf,int pages) {
        Book book = new Book();
        book.append(p,pf,pages);
        layoutUI(book);
    }

    public PrintPreviewDialog(Book book) {
        layoutUI(book);
    }

    private void layoutUI(Book book) {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas=new PrintPreviewCanvas(book);
        add(canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton nextButton = new JButton("Next");
        buttonPanel.add(nextButton);
        nextButton.addActionListener(event -> canvas.flipPage(1));

        JButton previousButton = new JButton("Previous");
        buttonPanel.add(previousButton);
        previousButton.addActionListener(event -> canvas.flipPage(-1));

        JButton closeButton = new JButton("Close");
        buttonPanel.add(closeButton);
        closeButton.addActionListener(event -> setVisible(false));

        add(buttonPanel, BorderLayout.SOUTH);
    }


}
