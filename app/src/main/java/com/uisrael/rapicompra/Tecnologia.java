package com.uisrael.rapicompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Tecnologia extends AppCompatActivity {
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    PhotoViewAttacher pA1,pA2,pA3,pA4,pA5,pA6,pA7,pA8,pA9;
    TextView  txtPre1, txtPre2, txtPre3,preModa1,preModa2,preModa3,pre1,pre2,pre3;
    CheckBox  chBoP1,chBoC2,chBoT3,moda1,moda2,moda3,art1,art2,art3;


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
        preModa1=findViewById(R.id.txtPreModa);
        preModa2=findViewById(R.id.txtPreModa2);
        preModa3=findViewById(R.id.txtPreModa3);
        moda1=findViewById(R.id.chkModa1);
        moda2=findViewById(R.id.checkModa2);
        moda3=findViewById(R.id.checkModa3);
        pre1=findViewById(R.id.txtVar1);
        pre2=findViewById(R.id.txtV2);
        pre3=findViewById(R.id.txtV3);
        art1=findViewById(R.id.chkBoxV1);
        art2=findViewById(R.id.chkBoxV2);
        art3=findViewById(R.id.chkBoxV3);
        img1=findViewById(R.id.imgT1);
        img2=findViewById(R.id.imgT2);
        img3=findViewById(R.id.imgT3);
        img4=findViewById(R.id.imgM1);
        img5=findViewById(R.id.imgM2);
        img6=findViewById(R.id.imgM3);
        img7=findViewById(R.id.imgArt1);
        img8=findViewById(R.id.imgArt2);
        img9=findViewById(R.id.imgArt3);
        pA1=new PhotoViewAttacher(img1);
        pA2=new PhotoViewAttacher(img2);
        pA3=new PhotoViewAttacher(img3);
        pA4=new PhotoViewAttacher(img4);
        pA5=new PhotoViewAttacher(img5);
        pA6=new PhotoViewAttacher(img6);
        pA7=new PhotoViewAttacher(img7);
        pA8=new PhotoViewAttacher(img8);
        pA9=new PhotoViewAttacher(img9);
    }

    public void pasarDatos_Tec(View v){
        Intent intAgregarPedidoT=new Intent(Tecnologia.this,Pedido.class);

        //Listado de productos de Tecnologia
        if(chBoP1.isChecked()==true){
            intAgregarPedidoT.putExtra("tecnologia",chBoP1.getText().toString());
            intAgregarPedidoT.putExtra("pretecnologia",txtPre1.getText().toString());
        }else {
            if (chBoC2.isChecked() == true) {
                intAgregarPedidoT.putExtra("tecnologia", chBoC2.getText().toString());
                intAgregarPedidoT.putExtra("pretecnologia", txtPre2.getText().toString());
            } else {
                if (chBoT3.isChecked()== true) {

                    intAgregarPedidoT.putExtra("tecnologia", chBoT3.getText().toString());
                    intAgregarPedidoT.putExtra("pretecnologia", txtPre3.getText().toString());
                }else {
                    intAgregarPedidoT.putExtra("tecnologia","S/N");
                    intAgregarPedidoT.putExtra("pretecnologia","0");
                }

            }
        }

        //Listado de productos Moda
        if(moda1.isChecked()==true){
            intAgregarPedidoT.putExtra("prenda",moda1.getText().toString());
            intAgregarPedidoT.putExtra("preModa",preModa1.getText().toString());
        }else
        {
            if(moda2.isChecked()==true){
                intAgregarPedidoT.putExtra("prenda",moda2.getText().toString());
                intAgregarPedidoT.putExtra("preModa",preModa2.getText().toString());
            }
            else{
                if(moda3.isChecked()==true) {
                    intAgregarPedidoT.putExtra("prenda", moda3.getText().toString());
                    intAgregarPedidoT.putExtra("preModa", preModa3.getText().toString());
                }else {
                    intAgregarPedidoT.putExtra("prenda","S/N");
                    intAgregarPedidoT.putExtra("preModa","0");
                }

            }
        }

        //Listado de productos varios
        if(art1.isChecked()==true){
            intAgregarPedidoT.putExtra("varios",art1.getText().toString());
            intAgregarPedidoT.putExtra("prevar",pre1.getText().toString());
        }
        else{
            if(art2.isChecked()==true){
                intAgregarPedidoT.putExtra("varios",art2.getText().toString());
                intAgregarPedidoT.putExtra("prevar",pre2.getText().toString());
            }
            else{
                if(art3.isChecked()==true) {
                    intAgregarPedidoT.putExtra("varios", art3.getText().toString());
                    intAgregarPedidoT.putExtra("prevar", pre3.getText().toString());
                }else {
                    intAgregarPedidoT.putExtra("varios","S/N");
                    intAgregarPedidoT.putExtra("prevar","0");
                }
            }
        }

        startActivity(intAgregarPedidoT);

    }

    public void Return(View v){
        Intent intent=new Intent(this,Productos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}