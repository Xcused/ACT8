package com.example.EXC2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.EXC2.database.DBControl;

import java.util.HashMap;

public class EditFriend extends AppCompatActivity {

    EditText edtNama, edtNoTelepon;
    Button btnUpdate;
    DBControl dbControl = new DBControl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        edtNama = findViewById(R.id.edName);
        edtNoTelepon = findViewById(R.id.edContact);
        btnUpdate = findViewById(R.id.btnSimpan);

        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("id");
        String nama = bundle.getString("nama");
        String noTelepon = bundle.getString("telpon");

        edtNama.setText(nama);
        edtNoTelepon.setText(noTelepon);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = edtNama.getText().toString();
                String telpon = edtNoTelepon.getText().toString();
                if (nama.isEmpty() || telpon.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Harap lengkapi data!", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditFriend.this);
                    builder.setTitle("Edit Data");
                    builder.setMessage("Apakah Anda yakin?");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                HashMap<String, String> values = new HashMap<>();
                                values.put("id", id);
                                values.put("nama", nama);
                                values.put("telpon", telpon);
                                dbControl.updateData(values);

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    );
                    builder.setNegativeButton("Batal",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }
                    );
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}