package com.example.t1chambasolution;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MiTarjeta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_tarjeta);
    }

    public void irOpcionesDeAplicaciones(View vista) {
        finish();
    }

    public void irAñadirTarjeta(View vista) {
        Intent miIntent = new Intent (this, AnadirTarjetaActivity.class);
        startActivity(miIntent);
    }

}