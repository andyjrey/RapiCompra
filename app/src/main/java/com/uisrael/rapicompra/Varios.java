package com.uisrael.rapicompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Varios extends AppCompatActivity {
    TextView pre1,pre2,pre3;
    CheckBox art1,art2,art3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varios);
        pre1=findViewById(R.id.txtVar1);
        pre2=findViewById(R.id.txtV2);
        pre3=findViewById(R.id.txtV3);
        art1=findViewById(R.id.chkBoxV1);
        art2=findViewById(R.id.chkBoxV2);
        art3=findViewById(R.id.chkBoxV3);
    }

    public void agregarDatosVarios(View v){
        Intent intAgregarPedidoV=new Intent(Varios.this,Pedido.class);
        if(art1.isChecked()==true){
            intAgregarPedidoV.putExtra("mascarillas",art1.getText().toString());
            intAgregarPedidoV.putExtra("premas",pre1.getText().toString());
        }
        else{
            if(art2.isChecked()==true){
                intAgregarPedidoV.putExtra("ollas",art2.getText().toString());
                intAgregarPedidoV.putExtra("preOllas",pre2.getText().toString());
            }
            else{
                intAgregarPedidoV.putExtra("maleta",art3.getText().toString());
                intAgregarPedidoV.putExtra("preMaleta",pre3.getText().toString());
            }
        }
        startActivity(intAgregarPedidoV);
    }
    public void Return(View v){
        Intent intent=new Intent(this,Lista_Productos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}