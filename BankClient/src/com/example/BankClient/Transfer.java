package com.example.BankClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Transfer extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.transfer);
        final EditText accountTxt = (EditText) findViewById(R.id.accountTxt);
        final EditText amountTxt = (EditText) findViewById(R.id.amountTxt);
        final Button submitBtn = (Button) findViewById(R.id.submitBtn);
        try
        {
            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    String account = accountTxt.getText().toString().trim();
                    String amount = amountTxt.getText().toString().trim();
                    try
                    {
                        Long.parseLong(account);
                        Long.parseLong(amount);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Transfer.this, "Enter numbers ..." , Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(account.length() != 9)
                    {
                        Toast.makeText(Transfer.this, "Format of the account number is not right ...", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (amount.length() > 9)
                    {
                        Toast.makeText(Transfer.this, "Amount of money you have entered is too much", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Intent intent  = new Intent(Transfer.this, FinalizeTransfer.class);
                    intent.putExtra("account", account);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(Transfer.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
