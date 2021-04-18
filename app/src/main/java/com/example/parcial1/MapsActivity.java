package com.example.parcial1;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng Cucuta = new LatLng(7.8971458, -72.5080387);
        mMap.addMarker(new MarkerOptions().position(Cucuta).title("Marcador en cucuta"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Cucuta));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Cucuta, 13));

        LatLng nuevom = new LatLng(7.897188308363383, -72.50505608357815);
        mMap.addMarker(new MarkerOptions().position(nuevom).title("otro marcador"));
        /*mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.ic_marcadorsito)).position(nuevom).title("otro marcador"));*/

        LatLng cristorey = new LatLng(7.89391, -72.50782);
        mMap.addMarker(new MarkerOptions().position(cristorey).title("Biblioteca publica cristo rey"));

        LatLng BibliotecaPublicaJulioPérezFerrero = new LatLng(7.8850636, -72.4998604);
        mMap.addMarker(new MarkerOptions().position(BibliotecaPublicaJulioPérezFerrero).title("Biblioteca Publica Julio Pérez Ferrero"));

        LatLng eduardocotelamus = new LatLng(7.89391, -72.50782);
        mMap.addMarker(new MarkerOptions().position(nuevom).title("Biblioteca Eduardo Cote Lamus"));

        LatLng simonbolivar = new LatLng(7.89391, -72.50782);
        mMap.addMarker(new MarkerOptions().position(eduardocotelamus).title("Biblioteca Pública Simón Bolívar"));

        LatLng dos = new LatLng(7.8838226, -72.4986375);
        mMap.addMarker(new MarkerOptions().position(dos).title("Biblioteca Luis Ángel Arango"));
        LatLng tes = new LatLng(7.889861 , -72.475787);
        mMap.addMarker(new MarkerOptions().position(tes).title("Sala de lectura Pablo Correa León"));
        LatLng cuato = new LatLng(7.8838353, -72.4853996);
        mMap.addMarker(new MarkerOptions().position(cuato).title("Sala de Lectura Misael Pastrana Borrero"));

        LatLng cinco = new LatLng(7.8813452, -72.4785634);
        mMap.addMarker(new MarkerOptions().position(cinco).title("Sala de Lectura Luis Carlos Galán Sarmiento"));

        LatLng asss = new LatLng(7.9492919, -72.5238687);
        mMap.addMarker(new MarkerOptions().position(asss).title("Sala de Lectura Nuestra Señora de Belén"));

        LatLng sds = new LatLng(7.8981533, -72.4872177);
        mMap.addMarker(new MarkerOptions().position(sds).title("Auditorio Biblioteca UFPS"));

        LatLng SENA = new LatLng(7.9006226, -72.5035949);
        mMap.addMarker(new MarkerOptions().position(SENA).title("SENA"));
    }
}