package com.example.sondre.oving5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;

/*
    This app uses the Volley HTTP-library.
    Volley uses Android's HTTPURLConnection class to make HTTP-requests under the hood.
    HTTPURLConnection will save cookies to the CookieManager implicitly.
     */

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText cardNum;
    private EditText num;
    private TextView mTextView;
    final String server = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    CookieManager cookieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextName);
        cardNum = findViewById(R.id.editTextCardNum);
        num = findViewById(R.id.editTextNum);
        mTextView = findViewById(R.id.textView);
        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    public void login(View view) {
        String url = server + "?navn=" + name.getText().toString() + "&kortnummer=" + cardNum.getText().toString();
        sendHTTPRequest(url);
    }

    public void sendNum(View view) {
        String url = server + "?tall=" + num.getText().toString();
        sendHTTPRequest(url);
    }

    private void sendHTTPRequest(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
