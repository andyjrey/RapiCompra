package com.uisrael.rapicompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Prendas_Vestir extends AppCompatActivity {
    TextView preModa1,preModa2,preModa3;
    CheckBox moda1,moda2,moda3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendas__vestir);
        preModa1=findViewById(R.id.txtPreModa);
        preModa2=findViewById(R.id.txtPreModa2);
        preModa3=findViewById(R.id.txtPreModa3);
        moda1=findViewById(R.id.chkModa1);
        moda2=findViewById(R.id.checkModa2);
        moda3=findViewById(R.id.checkModa3);
    }

    public void agregarPrendas(View v){
        Intent intAgregarPedidoM=new Intent(Prendas_Vestir.this,Pedido.class);
        if(moda1.isChecked()==true){
            intAgregarPedidoM.putExtra("prenda1",moda1.getText().toString());
            intAgregarPedidoM.putExtra("preModa1",preModa1.getText().toString());
        }else
        {
            if(moda2.isChecked()==true){
                intAgregarPedidoM.putExtra("prenda2",moda2.getText().toString());
                intAgregarPedidoM.putExtra("preModa2",preModa2.getText().toString());
            }
            else{
                intAgregarPedidoM.putExtra("prenda3",moda3.getText().toString());
                intAgregarPedidoM.putExtra("preModa3",preModa3.getText().toString());
            }
        }

        startActivity(intAgregarPedidoM);
    }

    public void Return(View v){
        Intent intent=new Intent(this,Lista_Productos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}