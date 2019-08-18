package com.example.crudbbdd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_inertar, btn_buscar, btn_borrar, btn_actualizar;
    EditText et_id, et_nombre, et_apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_inertar = findViewById(R.id.btn_insertar);
        btn_buscar = findViewById(R.id.btn_buscar);
        btn_actualizar = findViewById(R.id.btn_actualizar);
        btn_borrar = findViewById(R.id.btn_borrar);

        et_id = findViewById(R.id.et_id);
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);

        final UsuariosDbHelper helper = new UsuariosDbHelper(getApplicationContext());

        btn_inertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(EstructuraBBDD.COLUMN_NAME_ID, et_id.getText().toString());
                values.put(EstructuraBBDD.COLUMN_NAME_NAME, et_nombre.getText().toString());
                values.put(EstructuraBBDD.COLUMN_NAME_LASTNAME, et_apellido.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(EstructuraBBDD.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(), "Se guardo el registro con " + newRowId, Toast.LENGTH_SHORT).show();
            }
        });

        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
