package com.example.enviarmensagem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    private DatabaseReference myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = FirebaseDatabase.getInstance().getReference("Mensagem");

        final TextView mensagem = findViewById(R.id.mensagem);
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mensagem.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mensagem.setText("CANCELADO");

            }
        });


    }
    public void EnviarMensagem(View view){
        EditText editMensagem = findViewById(R.id.editMensagem);

        myDatabase.push().setValue(editMensagem.getText().toString());
        editMensagem.setText("");
    }




}
