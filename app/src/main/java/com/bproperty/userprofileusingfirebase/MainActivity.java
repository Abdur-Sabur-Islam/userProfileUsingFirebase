package com.bproperty.userprofileusingfirebase;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText userNameEt,userEmailEt;
    private TextView showTextTv;
    private Button saveBtn,showValueBtn;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReferene;
    private  String userId;
    String showtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEt = (EditText) findViewById(R.id.userName);
        userEmailEt = (EditText) findViewById(R.id.userEmail);
        showTextTv = (TextView) findViewById(R.id.showTextTv);
        saveBtn = (Button) findViewById(R.id.save);
        showValueBtn = (Button) findViewById(R.id.showData);

        showtext = showTextTv.getText().toString();

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mFirebaseDatabase =  FirebaseDatabase.getInstance();
        mDatabaseReferene = mFirebaseDatabase.getReference("User");
        mDatabaseReferene.setValue("sagar");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userNameEt.getText().toString();
                String email = userEmailEt.getText().toString();
                User user = new User(name,email);
                String userId = mDatabaseReferene.push().getKey();
                mDatabaseReferene.child(userId).setValue(user);
            }
        });
        showValueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final String userId = mDatabaseReferene.push().getKey();*/
                mDatabaseReferene.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                         long count = dataSnapshot.getChildrenCount();
                        /*showTextTv.setText(""+count);*/
                        for (DataSnapshot userData : dataSnapshot.getChildren()){

                            User users = userData.getValue(User.class);
                            showTextTv.setText(users.getUserName()+" "+users.getUserEmail());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }


}
