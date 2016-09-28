package com.example.BankClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TransactionsSendReceive extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.list);
        ListView accountList = (ListView) findViewById(R.id.listViewAccount);
        try
        {
            if(Integer.parseInt(getIntent().getExtras().getString("type")) == 1)
            {
                try
                {
                    String data = new Login().getFromSocket("/transfer/listSends?aid="+Data.getAccount_ID());
                    String[] list = data.split(";");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
                    accountList.setAdapter(adapter);
                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }else
            if(Integer.parseInt(getIntent().getExtras().getString("type")) == 2)
            {
                try
                {
                    String data = new Login().getFromSocket("/transfer/listReceives?aid="+Data.getAccount_ID());
                    String[] list = data.split(";");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
                    accountList.setAdapter(adapter);
                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
