package com.example.BankClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ListAccounts extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.list);
        String data = new Login().getFromSocket("/balance/accounts?pid="+Data.getPerson_ID());
        String data_name = new Login().getFromSocket("/balance/name?pid="+Data.getPerson_ID());
        Toast.makeText(getBaseContext(),"Welcome " + data_name + ".", Toast.LENGTH_LONG).show();
        final String[] list = data.split(":");
        try
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
            ListView accountList = (ListView) findViewById(R.id.listViewAccount);
            accountList.setAdapter(adapter);
            accountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Toast.makeText(getBaseContext(),
                            "You have selected : " + list[arg2],
                            Toast.LENGTH_SHORT).show();
                    Data.setAccount_ID(Long.parseLong(list[arg2]));
                    startActivity(new Intent(ListAccounts.this, Menu.class));
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
