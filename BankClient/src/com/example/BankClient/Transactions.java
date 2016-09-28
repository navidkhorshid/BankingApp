package com.example.BankClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transactions extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.transactions);
        final Button sendBtn = (Button) findViewById(R.id.sendBtn);
        final Button receiveBtn = (Button) findViewById(R.id.receiveBtn);

        sendBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(Transactions.this, TransactionsSendReceive.class);
                intent.putExtra("type", "1");
                startActivity(intent);
            }
        });

        receiveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(Transactions.this, TransactionsSendReceive.class);
                intent.putExtra("type", "2");
                startActivity(intent);
            }
        });
    }
}
