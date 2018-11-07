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
            ss = new ServerSocket(PORT);
            Log.i(TAG,"serversocket created, wait for client....");
            s = ss.accept();
            Log.v(TAG, "client connected...");
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
                Log.i(TAG,"Message from client: " + inputLine);
                String[] parts = inputLine.split(",");
                String answer = Integer.toString(Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]));
                outputLine = answer;
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
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