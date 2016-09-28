package com.example.BankClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class Login extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText userText = (EditText) findViewById(R.id.editText);
        final EditText passText = (EditText) findViewById(R.id.editText1);
        final Button loginBtn = (Button) findViewById(R.id.button);
        final Button endBtn = (Button) findViewById(R.id.endBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String un = userText.getText().toString();
                String pw = passText.getText().toString();
                if (un.length() > 20 || pw.length() > 20)
                {
                    Toast.makeText(Login.this,"Username or Password is too long ...", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String pid = getFromSocket("/authentication/login?user="+un+"&pass="+pw);
                    long tmp = 1;
                    try
                    {
                        tmp = Long.parseLong(pid);
                    }catch (Exception e){}
                    if (tmp == 0)
                    {
                        Toast.makeText(Login.this,"Authentication Failed ...", Toast.LENGTH_LONG).show();
                    }else if(pid == "")
                    {
                        Toast.makeText(Login.this,"Connection Failed ...", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Login.this,"Login Successful ...", Toast.LENGTH_LONG).show();
                        Data.setPerson_ID(Long.parseLong(pid));
                        startActivity(new Intent(Login.this,ListAccounts.class));
                    }

                }
            }
        });
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("Do you want to exit ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Login.this.finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ////////////////////
                builder.show();
            }
        });

    }


    public String getFromSocket(String fullUrl) {
        String hostAddress = "http://10.0.2.2:9998";
        InputStreamReader input = null;
        String content = "";
        int reader;
        URL url = null;
        try
        {
            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            url = new URL(hostAddress + fullUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Charset charset = Charset.forName("UTF8");
            input = new InputStreamReader(urlConnection.getInputStream(), charset);
            reader = input.read();
            while (reader != -1)
            {
                content += (char) reader;
                reader = input.read();
            }
            input.close();
        } catch (Exception e)
        {
            Toast.makeText(Login.this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return content;
    }

}
