package com.example.sondre.oving6server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class Server extends Thread{
    private final static String TAG = "ServerThread";
    private final static int PORT = 12345;

    public void run() {
        ServerSocket ss 	= null;
        Socket s 			= null;
        PrintWriter out 	= null;
        BufferedReader in 	= null;

        try{
            Log.i(TAG,"start server....");
            ServerSocket serverSocket = new ServerSocket(PORT);
            Log.i(TAG, serverSocket.getInetAddress().toString());
            Log.i(TAG,"serversocket created, wait for client....");
            Socket clientSocket = serverSocket.accept();
            Log.v(TAG, "client connected...");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            /*out.println("Welcome client...");//send text to client

            String res = in.readLine();//receive text from client
            Log.i(TAG,"Message from client: " + res);*/
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close sockets!!
            try{
                out.close();
                in.close();
                s.close();
                ss.close();
            }catch(Exception e){}
        }
    }
}
