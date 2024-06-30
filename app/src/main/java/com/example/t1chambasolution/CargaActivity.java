package com.example.t1chambasolution;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String token = getIntent().getStringExtra("token");

        //Imprimimos el token
        Toast.makeText(CargaActivity.this, token, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_carga);
        Thread tCarga = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    Intent iSesion = new Intent(getApplicationContext(), OpcionesDeAplicacionActivity.class);
                    //obtenemos el token de la sesion anterior


//                    iSesion.putExtra("codigo",13);
                    startActivity(iSesion);
                    finish();
                }
            }
        };
        tCarga.start();
    }
}