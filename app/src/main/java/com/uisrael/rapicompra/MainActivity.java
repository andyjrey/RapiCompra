package com.uisrael.rapicompra;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login (View v){
        Intent intent= new Intent(this, Login.class);
        startActivity(intent);

    }

    public void Registrar(View v){
        Intent intent= new Intent(this, Registro.class);
        startActivity(intent);
    }
}
