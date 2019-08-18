package com.example.crudbbdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
                cleanComponets();
                Toast.makeText(getApplicationContext(), "Se guardo el registro con " + newRowId, Toast.LENGTH_SHORT).show();
            }
        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //BaseColumns._ID,
                        EstructuraBBDD.COLUMN_NAME_NAME,
                        EstructuraBBDD.COLUMN_NAME_LASTNAME
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = EstructuraBBDD.COLUMN_NAME_ID + " = ?";
                String[] selectionArgs = {et_id.getText().toString()};

                // How you want the results sorted in the resulting Cursor
                /*String sortOrder =
                        EstructuraBBDD.COLUMN_NAME_ID + " DESC";*/

                try {
                    Cursor cursor = db.query(
                            EstructuraBBDD.TABLE_NAME,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );
                    cursor.moveToFirst();
                    et_nombre.setText(cursor.getString(0));
                    et_apellido.setText(cursor.getString(1));
                    cursor.close();

                   /* List itemIds = new ArrayList<>();
                    while(cursor.moveToNext()) {
                        long itemId = cursor.getLong(
                                cursor.getColumnIndexOrThrow(EstructuraBBDD.));
                        itemIds.add(itemId);
                    }
                    cursor.close();*/

                } catch (Exception e) {
                    Log.i("Error", "que error");
                    Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
                }

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
                SQLiteDatabase db = helper.getReadableDatabase();
                // Define 'where' part of query.
                String selection = EstructuraBBDD.COLUMN_NAME_ID + " LIKE ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = {et_id.getText().toString()};
                try {
                    // Issue SQL statement.
                    int deletedRows = db.delete(EstructuraBBDD.TABLE_NAME, selection, selectionArgs);
                    Toast.makeText(getApplicationContext(), "Se elimino el ID " + et_id.getText().
                            toString(), Toast.LENGTH_SHORT).show();
                    cleanComponets();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "No se eliminó el registro. " + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cleanComponets() {
        et_id.setText("");
        et_nombre.setText("");
        et_apellido.setText("");
    }
}
