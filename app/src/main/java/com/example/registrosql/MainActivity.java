package com.example.registrosql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button guardar, actualizar, eliminar, listar;
    DbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        guardar = findViewById(R.id.btnGuardar);
        actualizar = findViewById(R.id.btnActualizar);
        eliminar = findViewById(R.id.btnEliminar);
        listar = findViewById(R.id.btnListar);

        db = new DbHelper(this);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                boolean checkguardardata = db.Guardar(nameTXT, contactTXT, dobTXT);
                if (checkguardardata == true)
                    Toast.makeText(MainActivity.this, "Guardado :)", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "No se pudo Guardar :/", Toast.LENGTH_SHORT).show();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                boolean checkActualizardata = db.Actualizar(nameTXT, contactTXT, dobTXT);
                if (checkActualizardata == true)
                    Toast.makeText(MainActivity.this, "Actulizado Correctamente :)", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "No se pudo Actualizarr :/", Toast.LENGTH_SHORT).show();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                boolean checkEliminardata = db.Eliminar(nameTXT);
                if (checkEliminardata == true)
                    Toast.makeText(MainActivity.this, "Eliminado Correctamente :)", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "No se pudo Eliminar :/", Toast.LENGTH_SHORT).show();
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.Getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Existe :/", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("Contact: "+res.getString(1)+"\n");
                    buffer.append("Fecha de Nacimiento: "+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Usuarios");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




    }
}