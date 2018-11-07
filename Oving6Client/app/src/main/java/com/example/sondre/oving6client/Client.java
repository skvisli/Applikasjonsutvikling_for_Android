package com.example.sondre.oving6client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;

public class Client extends Thread {
    private final static String TAG = "ClientThread";
    private final static String IP = "10.0.2.2";
    private final static int PORT = 12345;

    public void run() {
        Socket socket 			= null;
        PrintWriter out		= null;
        BufferedReader in 	= null;

        try {
            socket = new Socket(IP, PORT);
            Log.i(TAG, "C: Connected to server" + socket.toString());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /*String res = in.readLine();
            Log.i(TAG,res);
            out.println("PING to server from client");*/
        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close socket!!
            try{
                out.close();
                in.close();
                socket.close();
            }catch(IOException e){}
        }
    }
}