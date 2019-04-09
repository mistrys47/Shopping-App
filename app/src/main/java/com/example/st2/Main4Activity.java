package com.example.st2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    private DatabaseReference db1;
    private x obj;
    private ImageView i1;
    private TextView name,desc,price,count;
    private ArrayList<y> detail_value;
    private ImageButton b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final int value = getIntent().getExtras().getInt("id");
        i1=(ImageView)findViewById(R.id.imageView);
        name=(TextView)findViewById(R.id.name);
        desc=(TextView)findViewById(R.id.description);
        count=(TextView)findViewById(R.id.count);
        price=(TextView)findViewById(R.id.price);
        b=(ImageButton)findViewById(R.id.button3);
        db1= FirebaseDatabase.getInstance().getReference();
        DatabaseReference detail=db1.child("detail");
        Query q=detail;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("asdfghjkl",obj.getName());
                cart.getInstance();
                cart.set(obj.getId(),Integer.parseInt(count.getText().toString()),obj.getName(),Integer.parseInt(obj.getPrice()));
                Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_LONG).show();
                Intent i1=new Intent(Main4Activity.this,Main2Activity.class);
                i1.putExtra("type",obj.getType());
                startActivity(i1);
            }
        });
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String name1=(String)postSnapshot.child("name").getValue().toString();
                    String img1=(String)postSnapshot.child("image").getValue().toString();
                    String origin1=(String)postSnapshot.child("origin").getValue().toString();
                    String brief1=(String)postSnapshot.child("brief").getValue().toString();
                    String price1=(String)postSnapshot.child("price").getValue().toString();
                    String type1=(String)postSnapshot.child("type").getValue().toString();
                    String qr1=(String)postSnapshot.child("qr").getValue().toString();
                    int id1=Integer.parseInt(postSnapshot.child("id").getValue().toString());
                    if(id1==value)
                    {
                        detail_value=cart.getInstance();
                        for(y y1:detail_value)
                        {
                            if(y1.getId()==id1)
                            {
                                String s=Integer.toString(y1.getQuantity());
                                count.setText(s);
                            }
                        }
                        obj=new x(img1,name1,type1,origin1,price1,brief1,qr1,id1);
                        Uri u=Uri.parse(img1);
                        Picasso.get().load(u).into(i1);
                        name.setText(name1);
                        price.setText(price1+" Rs");
                        desc.setText(brief1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void p(View v)
    {
        String s=count.getText().toString();
        int k=Integer.parseInt(s);
        k++;
        count.setText(Integer.toString(k));
    }
    void q(View v1)
    {
        String s=count.getText().toString();
        int k=Integer.parseInt(s);
        if(k!=0)
        k--;
        else
            Toast.makeText(getApplicationContext(),"Quantity can't be negative",Toast.LENGTH_LONG).show();
        count.setText(Integer.toString(k));
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
    public void alert(View v){

        AlertDialog.Builder alertadd = new AlertDialog.Builder(
                Main4Activity.this);
        alertadd.setTitle("QR");

        LayoutInflater factory = LayoutInflater.from(Main4Activity.this);
        final View view = factory.inflate(R.layout.qr, null);

        ImageView image= (ImageView) view.findViewById(R.id.image);
        Uri u=Uri.parse(obj.getQr());
        Picasso.get().load(u).into(image);
        alertadd.setView(view);
        alertadd.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {

            }
        });

        alertadd.show();

    }
    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

}
