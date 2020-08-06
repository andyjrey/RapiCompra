package com.uisrael.rapicompra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class Registro extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private  GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public  static final int SIGN_IN_CODE=777;
    EditText nom,ape,correo,pass,r_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nom=findViewById(R.id.etNombre);
        ape=findViewById(R.id.etApellido);
        correo=findViewById(R.id.etEmail);
        pass=findViewById(R.id.etPassword);
        r_pass=findViewById(R.id.etRepPassword);

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        signInButton=(SignInButton)findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });

            }
    public void Registrar(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db=admin.getWritableDatabase();
        String nomb=nom.getText().toString();
        String apellid=ape.getText().toString();
        String corre=correo.getText().toString();
        String passw=pass.getText().toString();
        String rpass=r_pass.getText().toString();
        if (!(pass.getText().toString().equals(r_pass.getText().toString()))){
            Toast.makeText(this,"Las Contraseñas no Coinciden",Toast.LENGTH_LONG).show();
        }else if (!nomb.isEmpty() && !apellid.isEmpty() && !corre.isEmpty() && !passw.isEmpty() && !rpass.isEmpty()) {
                ContentValues registro = new ContentValues();
                registro.put("nombre", nomb);
                registro.put("apellido", apellid);
                registro.put("email", corre);
                registro.put("contrasena", passw);
                registro.put("rep_contrasena", rpass);

                db.insert("registro", null, registro);
                db.close();
                nom.setText("");
                ape.setText("");
                correo.setText("");
                pass.setText("");
                r_pass.setText("");

                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                goProductsScreen();


            } else {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
            }
            // ver ubicación de base de datos
            // String pathDatabase=getDatabasePath("mybasededatos.db").getAbsolutePath();
            // Toast.makeText(getApplicationContext(),pathDatabase,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"No se puede conectar",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_CODE){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handlesSignInResult(result);

        }
    }

    private void handlesSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            goProductsScreen();

        }else{
            Toast.makeText(this,"No se pudo iniciar sesión",Toast.LENGTH_LONG).show();
        }
    }

    private void goProductsScreen() {
        Intent intent=new Intent(this,Productos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
