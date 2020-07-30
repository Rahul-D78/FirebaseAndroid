package com.sih.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    ListView listView;
    FirebaseUser firebaseUser;
    ArrayList<String> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.item_row,
                R.id.itemLv,
                notes);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        listView.setAdapter(arrayAdapter);

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = editText.getText().toString();

             //   Read r  = new Read("Hello", "World");

                //upload notes to firebase
                dbRef.child("note").push().setValue(notes);
                //dbRef.child("root").push().setValue(notes);
            }
        });

        dbRef.child("note").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //when new data is inserted into the node note
                String data = dataSnapshot.getValue(String.class);
                notes.add(data);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //when existing data is updated
                String data =  dataSnapshot.getValue(String.class);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                //when data is removed
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             //when the positiin is changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //when the read operation failed
            }
        });
    }
}