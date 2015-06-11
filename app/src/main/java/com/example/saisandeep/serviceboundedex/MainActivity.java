package com.example.saisandeep.serviceboundedex;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText txt;
    BoundService boundService;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt= (EditText) findViewById(R.id.editText);
    }


    public void bindMethod(View view)
    {
        Intent i=new Intent(this,BoundService.class);
        bindService(i,sc, Context.BIND_AUTO_CREATE);
        status=true;

        Toast.makeText(this,"Service binded successfully ",Toast.LENGTH_SHORT).show();
    }

    public void unbindMethod(View view)
    {

        if(status)
        {
            unbindService(sc);
            Toast.makeText(this,"Service unbinded successfully ",Toast.LENGTH_SHORT).show();
            status=false;

        }
        else
        {
            Toast.makeText(this,"Service already unbinded ",Toast.LENGTH_SHORT).show();
        }
    }

    public void factorialMethod(View view)
    {
        if(status)
        {
            int num=Integer.parseInt(txt.getText().toString());

            int result=boundService.factorial(num);

            Toast.makeText(this,"Factorial is "+result,Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this,"First Bind the service ",Toast.LENGTH_SHORT).show();
        }

    }

    private ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            BoundService.LocalBinder localBinder= (BoundService.LocalBinder) service;

            boundService=localBinder.getService();
            status=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
