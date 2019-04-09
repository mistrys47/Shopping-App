package com.example.st2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private DatabaseReference db1;
    private RecyclerView r1;
    private adapter adp;
    private List<x> detail_value=new ArrayList<x>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final String value = getIntent().getExtras().getString("type");
        r1=(RecyclerView)findViewById(R.id.recycler_view);
        adp=new adapter(detail_value);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        r1.setLayoutManager(layoutManager);
        r1.setItemAnimator(new DefaultItemAnimator());
        r1.setAdapter(adp);


        db1=FirebaseDatabase.getInstance().getReference();
        DatabaseReference detail=db1.child("detail");
        Query q=detail;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detail_value.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String name=(String)postSnapshot.child("name").getValue().toString();
                    String img=(String)postSnapshot.child("image").getValue().toString();
                    String origin=(String)postSnapshot.child("origin").getValue().toString();
                    String brief=(String)postSnapshot.child("brief").getValue().toString();
                    String price=(String)postSnapshot.child("price").getValue().toString();
                    String type=(String)postSnapshot.child("type").getValue().toString();
                    String qr=(String)postSnapshot.child("qr").getValue().toString();
                    int id=Integer.parseInt(postSnapshot.child("id").getValue().toString());
                    Log.d("deep",name);
                    if(type.equals(value))
                    detail_value.add(new x(img,name,type,origin,price,brief,qr,id));
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
}
