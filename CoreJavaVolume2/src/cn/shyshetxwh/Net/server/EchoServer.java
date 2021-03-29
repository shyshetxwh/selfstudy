package cn.shyshetxwh.Net.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket sc=new ServerSocket(8189)){
            try(Socket incoming=sc.accept()){
                InputStream is = incoming.getInputStream();
                OutputStream os = incoming.getOutputStream();
                try(Scanner in=new Scanner(is,"utf-8")){
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true/*autoFlush*/);
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
            }

        }
    }
}
