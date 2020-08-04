package com.uisrael.rapicompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText user,pass;
   // public Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.etUser);
        pass=findViewById(R.id.etPass);
    }

    public void verificar (View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(getBaseContext());
        SQLiteDatabase db=admin.getReadableDatabase();
        String usu=user.getText().toString();
        String passw=pass.getText().toString();
        if(!usu.isEmpty()) {
            //fila = db.rawQuery("select email, contrasena from registro where email='" +usu+"'and contrasena='"+passw+"'", null);
            Cursor d = db.rawQuery("select email, contrasena from registro where email='" +usu+"'and contrasena='"+passw+"'", null);
            //preguntamos si el cursor tiene algun valor almacenado

            if( d.moveToFirst()){
                //almacenamos los valores capturados
                String usua = d.getString(0);
                String password = d.getString(1);
                //db.close();

                //comparamos valores en edit text con los de la base de datos
                if (usu.equals(usua) && passw.equals(password)) {

                    Intent intent = new Intent(this, Productos.class);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(this, "Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                user.setText("");
                pass.setText("");
            }
            // d.close();
          //  db.close();
        }else {
            Toast.makeText(this, "Debes introducir los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
