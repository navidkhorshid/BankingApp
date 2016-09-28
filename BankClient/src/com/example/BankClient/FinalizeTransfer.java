package com.example.BankClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinalizeTransfer extends Activity
{
    private String account;
    private String amount;
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.checkperson);
        final Button transferBtn = (Button) findViewById(R.id.transferMoneyBtn);
        try
        {
            account = getIntent().getExtras().getString("account");
            amount = getIntent().getExtras().getString("amount");
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(), "Previous Page Failure ...", Toast.LENGTH_LONG).show();
            transferBtn.setEnabled(false);
        }

        transferBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    String data = new Login().getFromSocket("/transfer/transferMoney?aid_sender="+Data.getAccount_ID()
                    + "&aid_receiver=" + account +"&amnt=" + amount);
                    if(Long.parseLong(data) == 0)
                    {
                        Toast.makeText(getBaseContext(), "There was not enough money !", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(FinalizeTransfer.this,Transfer.class));
                    }else if(Long.parseLong(data) == 1)
                    {
                        Toast.makeText(getBaseContext(), "Transaction Successful !", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(FinalizeTransfer.this,Menu.class));
                    }else  if (Long.parseLong(data) == 2)
                    {
                        Toast.makeText(getBaseContext(), "There was an error in the system !", Toast.LENGTH_LONG).show();
                    }else if(data == "")
                    {
                        Toast.makeText(getBaseContext(), "Connection Failure !", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        final TextView nameTxtVw = (TextView) findViewById(R.id.nameLbl);
        final TextView moneyTxtVw = (TextView) findViewById(R.id.moneyLbl);
        try
        {
            moneyTxtVw.setText(amount);
            String data = new Login().getFromSocket("/transfer/checkPerson?aid="+account);
            long tmp = 1;
            try
            {
                tmp = Long.parseLong(data);
            }catch (Exception e)
            {}
            if(tmp == 0)//data == 0
            {
                Toast.makeText(getBaseContext(), "No Account Match ...", Toast.LENGTH_LONG).show();
                transferBtn.setEnabled(false);
            }else if(data == "")
            {
                Toast.makeText(getBaseContext(), "Connection Failed ...", Toast.LENGTH_LONG).show();
                transferBtn.setEnabled(false);
            }else
            {
                nameTxtVw.setText(data.toString());
                String data2 = new Login().getFromSocket("/transfer/checkBalance?aid="+Data.getAccount_ID()+"&amnt="+amount);
                if(Long.parseLong(data2) == 0)
                {
                    Toast.makeText(getBaseContext(), "You don't have enough money.", Toast.LENGTH_LONG).show();
                    transferBtn.setEnabled(false);
                }else if(data2 == "")
                {
                    Toast.makeText(getBaseContext(), "Connection Failed ...", Toast.LENGTH_LONG).show();
                    transferBtn.setEnabled(false);
                }
                else if (Long.parseLong(data2) == 1)
                {
                    Toast.makeText(getBaseContext(), "You have enough money.", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
