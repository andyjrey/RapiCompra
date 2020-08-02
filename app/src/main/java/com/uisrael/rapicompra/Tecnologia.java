package com.uisrael.rapicompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Tecnologia extends AppCompatActivity {
TextView  txtPre1, txtPre2, txtPre3;
CheckBox  chBoP1,chBoC2,chBoT3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnologia);
        txtPre1=findViewById(R.id.txtPreP);
        txtPre2=findViewById(R.id.txtPreTel);
        txtPre3=findViewById(R.id.txtPreTe);
        chBoP1=findViewById(R.id.ckBoxPortatil);
        chBoC2=findViewById(R.id.chkCell);
        chBoT3=findViewById(R.id.chkBoxTe);
    }

    public void pasarDatos_Tec(View v){
        Intent intAgregarPedidoT=new Intent(Tecnologia.this,Pedido.class);
        if(chBoP1.isChecked()==true){
            intAgregarPedidoT.putExtra("portatil",chBoP1.getText().toString());
            intAgregarPedidoT.putExtra("preportatil",txtPre1.getText().toString());
        }else {
            if (chBoC2.isChecked() == true) {
                intAgregarPedidoT.putExtra("telefono", chBoC2.getText().toString());
                intAgregarPedidoT.putExtra("PreTel", txtPre2.getText().toString());
            } else {
                intAgregarPedidoT.putExtra("teclado", chBoT3.getText().toString());
                intAgregarPedidoT.putExtra("preTeclado", txtPre3.getText().toString());
            }
        }
        startActivity(intAgregarPedidoT);

    }

    public void Return(View v){
        Intent intent=new Intent(this,Lista_Productos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}