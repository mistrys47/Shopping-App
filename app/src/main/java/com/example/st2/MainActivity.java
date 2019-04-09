package com.example.st2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isNetworkConnected()==false)
        {
            Toast.makeText(getApplicationContext(),"Internet connectivity required",Toast.LENGTH_LONG).show();
            this.finish();
            System.exit(0);
        }
        try{
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String s=spinner.getSelectedItem().toString();
                if(s.equals("Select-One"))
                {

                }
                else
                {
                    Intent i1=new Intent(getApplicationContext(),Main2Activity.class);
                    i1.putExtra("type",s);
                    startActivity(i1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
        }
    }
    void cart1(View view)
    {

        ArrayList<y> a = cart.getInstance();
        //Toast.makeText(getApplicationContext(),a.size()+"",Toast.LENGTH_LONG).show();
        if(a.size()==0)
        {
            Toast.makeText(getApplicationContext(),"Cart is Empty...!!!",Toast.LENGTH_LONG).show();
        }
        else
        {
            int k=0;
            String s="";
            int i=1;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            for (y y1 : a) {
                if(y1.getQuantity()>0) {
                    s += i + "  ";
                    s += y1.getS1() + "  ";
                    s += y1.getPrice() + "  ";
                    s += y1.getQuantity() + "  ";
                    s += y1.getPrice() * y1.getQuantity() + "  ";
                    s += "\n";
                    k += y1.getPrice() * y1.getQuantity();
                    i++;
                }
            }
            s+="Total : "+k;
            builder.setTitle("Cart");
            builder.setMessage(s);
            builder.setCancelable(true);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
