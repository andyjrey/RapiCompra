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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.etUser);
        pass=findViewById(R.id.etPass);
    }

    public void verificar (View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String usu=user.getText().toString();
        String passw=pass.getText().toString();

            Cursor fila =db.rawQuery("select email,contraseña from registro where email='"+usu+"'and contraseña='"+passw+"'",null);
        if(fila.moveToFirst()== true){
            //almacenamos los valores capturados
            String usua=fila.getString(0);
            String password=fila.getString(1);

           //comparamos valores en edit text con los de la base de datos
            if(usu.equals(usua)&& passw.equals(password)) {

                Intent intent = new Intent(this, Lista_Productos.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Usuario o Contraseña Incorrectos",Toast.LENGTH_SHORT).show();
                user.setText("");
                pass.setText("");

            }

            }

    }
}
