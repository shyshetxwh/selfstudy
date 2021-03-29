package cn.shyshetxwh.Net.interruptible;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InterruptibleSocketFrame extends JFrame {
    private JTextArea message;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private Scanner in;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame()
    {
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        final int TEXT_ROWS=20;
        final int TEXT_COLUMNS=60;
        message = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(message));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");
        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);
        northPanel.add(cancelButton);

        interruptibleButton.addActionListener(event->{
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread=new Thread(()->{
                try {
                    connectInterruptibly();
                } catch (IOException e) {
                    message.append("\nInterruptibleSocketTest.connectInterruptibly: "+e);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(event->{
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread=new Thread(()->{
                try {
                    connectBlocking();
                } catch (IOException e) {
                    message.append("\nInterruptibleSocketTest.connectBlocking: "+e);
                }
            });
            connectThread.start();
        });

        cancelButton.addActionListener(event->{
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });

        server = new TestServer();
        new Thread(server).start();


        pack();
    }

    private void connectBlocking() throws IOException {
        message.append("Blocking:\n");
        try(Socket s=new Socket("localhost",8189)){
            in = new Scanner(s.getInputStream(), "utf-8");
            while(!Thread.currentThread().isInterrupted())
            {

                message.append("Block Reading");
                if (in.hasNextLine())
                {

                    message.append(in.nextLine()+"\n");
                }
            }
        }
        finally {
            EventQueue.invokeLater(() ->
            {
                message.append("Socket closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    private void connectInterruptibly() throws IOException {
        message.append("Interruptible:\n");
        try(SocketChannel channel=SocketChannel.open(new InetSocketAddress("localhost",8189))){
            in = new Scanner(channel, "utf-8");
            while(!Thread.currentThread().isInterrupted())
            {

                message.append("Interr Reading");
                if (in.hasNextLine())
                {

                    message.append(in.nextLine()+"\n");
                }
            }
        }
        finally {
            EventQueue.invokeLater(() ->
            {
                message.append("Channnel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    class TestServer implements Runnable
    {
        public void run()
        {
            try (ServerSocket s = new ServerSocket(8189))
            {
                while (true)
                {
                    Socket incoming = s.accept();
                    Runnable r = new TestServerHandler(incoming);
                    new Thread(r).start();
                }
            }
            catch (IOException e)
            {
                message.append("\nTestServer.run: " + e);
            }
        }
    }

    class TestServerHandler implements Runnable
    {
        private Socket incoming;
        private int counter;


        public TestServerHandler(Socket s)
        {
            incoming = s;
        }

        public void run()
        {
            try
            {
                try
                {
                    OutputStream os = incoming.getOutputStream();
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true);
                    while(counter<100)
                    {
                        counter++;
                        if (counter<=10)
                        {
                            out.println(counter);
                        }
                        Thread.sleep(100);
                    }
                }
                finally
                {
                    incoming.close();
                    message.append("Closing server\n");
                }
            }
            catch (Exception e)
            {
                message.append("\nTestServerHandler.run: " + e);
            }
        }
    }
}
