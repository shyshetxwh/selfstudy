package cn.shyshetxwh.Thread.swingWorker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class SwingWorkerFrame extends JFrame{
    private JTextArea textArea;
    private JLabel statusLine;
    private SwingWorker<StringBuilder,ProgressData> textReader;
    public static final int TEXT_ROWS=20;
    public static final int TEXT_COLUMNS=60;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private JFileChooser chooser;

    public SwingWorkerFrame()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        openItem = new JMenuItem("Open");
        openItem.addActionListener(event->{
            int result = chooser.showOpenDialog(null);

            if(result==JFileChooser.APPROVE_OPTION)
            {
                textArea.setText("");
                openItem.setEnabled(false);
                textReader = new TextReader(chooser.getSelectedFile());
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });
        fileMenu.add(openItem);

        cancelItem = new JMenuItem("Cancel");
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(event->{
            textReader.cancel(true);
        });
        fileMenu.add(cancelItem);

        textArea=new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statusLine=new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        pack();


    }

    private class ProgressData
    {
        public int number;
        public String line;
    }

    private class TextReader extends SwingWorker<StringBuilder,ProgressData>
    {
        private File file;
        private StringBuilder text=new StringBuilder();

        public TextReader(File file)
        {
            this.file=file;
        }
        @Override
        public StringBuilder doInBackground() throws Exception {
            int lineNumber=0;
            try(Scanner in = new Scanner(new FileInputStream(file), "utf-8");)
            {
                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    ProgressData data = new ProgressData();
                    data.line=line;
                    data.number=lineNumber;
                    publish(data);
//                Thread.sleep(1);
                }
            }
            return text;
        }

        public void process(List<ProgressData> datas)
        {
            if(isCancelled())
            {
                return;
            }
            StringBuilder builder = new StringBuilder();
            statusLine.setText(""+datas.get(datas.size()-1).number);
            for (ProgressData data : datas) {
                builder.append(data.line).append("\n");
            }
            textArea.append(builder.toString());
        }

        @Override
        public void done() {
            try {
                StringBuilder result = get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                statusLine.setText(""+e.getCause());

            } catch (CancellationException ex){
                textArea.append("");
                statusLine.setText("Cancelled");
            }
            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }
    }
}
