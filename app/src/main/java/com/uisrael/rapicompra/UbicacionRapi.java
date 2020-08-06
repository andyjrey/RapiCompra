package com.uisrael.rapicompra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


public class UbicacionRapi extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView Lat, Lon;
    private LocationManager coordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_rapi);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ObtenerCoordenadas();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            //Mensaje para activar los permisos de GPS - Sino no funciona
            ObtenerPermisos();
        }else{

        }

        mMap = googleMap;
        //Método geolocalización
        mMap.setMyLocationEnabled(true);
    }

    //Obtener coordenadas y sobrescribir los textVIew
    public void ObtenerCoordenadas() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        coordenadas = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = coordenadas.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Iniciliza los TextView con las variables locales TextView
        Lat =(TextView) findViewById(R.id.txtLA);
        Lon =(TextView) findViewById(R.id.txtLO);
        Log.d("Latitud", String.valueOf(loc.getLatitude()));
        Log.d("Longitud", String.valueOf(loc.getLongitude()));

        //Asignar el tecto a las varibles TextView y transforma a loc a String
        Lat.setText(String.valueOf(loc.getLatitude()));
        Lon.setText(String.valueOf(loc.getLongitude()));
    }

    // Metodo para pasar los datos y cambiar de actividad
    public void CambiarActividad(View v){

        //Acción para cambiar de actividad
        Intent intentUbicacion = new Intent(UbicacionRapi.this,Pedido.class);
        startActivity(intentUbicacion);
    }

    //Mensaje de notificación finalizar compra
    public void Mensaje(View v){

        Toast.makeText(getApplicationContext(),"Su compra fue confirmada y será enviada a la ubicación ingresada. Gracias por preferirnos ",
                Toast.LENGTH_LONG).show();
    }

    //Activar sensor gps cuando este apagado
    public void ObtenerPermisos(){
        LocationManager locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnable= locmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsEnable){

            Intent settingsIntenet= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntenet);

        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},500);

        }
    }




}