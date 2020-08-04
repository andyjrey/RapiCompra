package com.uisrael.rapicompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pedido extends AppCompatActivity {
     Double subtotal,total,impuesto,preen,preTe=0.0,preMod=0.0,preva=0.0;
     TextView det1,det2,det3,pre1,pre2,pre3,sub,imp,entrega,tot;
     EditText nomb,tel;
     String pedTe,pedMo,varios;
     Bundle tecno,preTecno,moda,preModa,art,preArt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        det1=findViewById(R.id.txtP1);
        det2=findViewById(R.id.txtP2);
        det3=findViewById(R.id.txtP3);
        pre1=findViewById(R.id.txtPreP1);
        pre2=findViewById(R.id.txtPreP2);
        pre3=findViewById(R.id.txtPreP3);
        sub=findViewById(R.id.txtPreSub);
        imp=findViewById(R.id.txtPreIva);
        entrega=findViewById(R.id.txtPreEn);
        tot=findViewById(R.id.txtPreTotal);
        nomb=findViewById(R.id.etName);
        tel=findViewById(R.id.etTelefono);

        tecno=getIntent().getExtras();
        preTecno=getIntent().getExtras();
        moda=getIntent().getExtras();
        preModa=getIntent().getExtras();
        art=getIntent().getExtras();
        preArt=getIntent().getExtras();
        if(tecno!=null) {
            //double qu1=Double.parseDouble(etq1.getText().toString());
            pedTe = tecno.getString("tecnologia");
            preTe = Double.parseDouble(preTecno.getString("pretecnologia"));
            det1.setText(pedTe);
            pre1.setText("USD"+preTe);

        }
        if(moda!=null) {
            pedMo = moda.getString("prenda");
            preMod = Double.parseDouble(preModa.getString("preModa"));
            det2.setText(pedMo);
            pre2.setText("USD"+preMod);
        }

        if(art!=null) {
            varios = art.getString("varios");
            preva = Double.parseDouble(preArt.getString("prevar"));
            det3.setText(varios);
            pre3.setText("USD" + preva);

        }
        subtotal=preTe+preMod+preva;
        preen=0.02*subtotal;
        impuesto=subtotal*0.12;
        total=subtotal+preen+impuesto;
        sub.setText("USD"+subtotal);
        imp.setText("USD"+impuesto);
        entrega.setText("USD"+preen);
        tot.setText("USD"+total);


    }


    public void confirmarPedido(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db=admin.getWritableDatabase();
        String name=nomb.getText().toString();
        String tele=tel.getText().toString();
        if(!tele.isEmpty()&&!name.isEmpty()){
            ContentValues registrar = new ContentValues();
            registrar.put("nombre_cliente",name);
            registrar.put("telefono",tele);
            db.insert("datos_entrega",null,registrar);
            db.close();
            nomb.setText("");
            tel.setText("");
            Toast.makeText(this,"Pedido ingresado con exito, Recuerda que el pago es en efectivo",Toast.LENGTH_LONG).show();
            goToUbicacionScreen();

        }else{
            Toast.makeText(this,"Por favor ingresa los datos de contacto",Toast.LENGTH_SHORT).show();
        }

    }
    public void goToUbicacionScreen (){
        Intent intent=new Intent(Pedido.this,Ubicacion.class);
        startActivity(intent);
    }
    public void goListaProductosScreen(View v) {
        Intent intent=new Intent(this,Tecnologia.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}