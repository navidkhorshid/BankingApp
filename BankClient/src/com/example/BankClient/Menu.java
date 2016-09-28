package com.example.BankClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.menu);
        final Button balanceBtn = (Button) findViewById(R.id.balanceBtn);
        final Button transferBtn = (Button) findViewById(R.id.transferBtn);
        final Button transBtn = (Button) findViewById(R.id.transBtn);
        final Button exitBtn = (Button) findViewById(R.id.exitBtn);

        balanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String data = new Login().getFromSocket("/balance/money?aid="+Data.getAccount_ID());
                Toast.makeText(getBaseContext(),"Your Balance is: " + data + " Dollar(s)", Toast.LENGTH_LONG).show();
            }
        });

        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Menu.this,Transfer.class));
            }
        });

        transBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Menu.this,Transactions.class));
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ///////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                builder.setMessage("Do you want to logout ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Menu.this,Login.class));
                        //Menu.this.finish();
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
}
