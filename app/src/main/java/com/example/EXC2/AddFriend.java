package com.example.EXC2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EXC2.database.DBControl;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class AddFriend extends AppCompatActivity {

    private TextInputEditText tiNama, tiNoTelfon;
    private Button btnSave;
    String name, contact;
    DBControl dbControl = new DBControl(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teman);
        tiNama = findViewById(R.id.addName);
        tiNoTelfon = findViewById(R.id.addContact);
        btnSave = findViewById(R.id.buttSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = tiNama.getText().toString();
                if (name.isEmpty() || contact.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Harus Lengkap!", Toast.LENGTH_SHORT).show();
                contact = tiNoTelfon.getText().toString();
                } else {
                    HashMap<String, String> values = new HashMap<>();
                    values.put("nama", name);
                    values.put("telpon", contact);
                    dbControl.insertData(values);
                    openMainActivity();
                }
            }
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}