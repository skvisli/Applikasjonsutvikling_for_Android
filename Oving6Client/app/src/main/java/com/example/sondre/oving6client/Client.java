package com.example.sondre.oving6client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.util.Log;

public class Client implements Runnable {
    private final static String TAG = "ClientThread";
    private final static String IP = "10.0.2.2";
    private final static int PORT = 12345;
    private String num1;
    private String num2;
    private ClientActivity clientActivity;

    public Client (ClientActivity clientActivity, String num1, String num2) {
        this.num1 = num1;
        this.num2 = num2;
        this.clientActivity = clientActivity;
    }

    public void run() {
        Socket s 			= null;
        PrintWriter out		= null;
        BufferedReader in 	= null;

        try {
            s = new Socket(IP, PORT);
            Log.v(TAG, "C: Connected to server" + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.println(num1 + "," + num2);

            String res = in.readLine();
            Log.i(TAG,res);
            clientActivity.setText(res);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{//close socket!!
            try{
                out.close();
                in.close();
                s.close();
            }catch(IOException e){}
        }
    }
}