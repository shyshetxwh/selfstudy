package cn.shyshetxwh.Net.threaded;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadedEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
       try(InputStream is = incoming.getInputStream();
           OutputStream os = incoming.getOutputStream();
           Scanner in=new Scanner(is,"utf-8");
           PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true/*autoFlush*/))
       {
            out.println("Hello! Enter BYE to exit:");

           boolean done=false;
           while(!done&&in.hasNextLine())
           {
               String line = in.nextLine();
               out.println("Echo: "+line);
               if (line.trim().equals("BYE"))
               {
                   done=true;
               }
           }
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }
}
