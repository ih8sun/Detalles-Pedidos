package com.example.t1chambasolution;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.t1chambasolution.cliente.ClienteClient;
import com.example.t1chambasolution.modelo.Cliente;
import com.example.t1chambasolution.servicio.CustomerService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpcionesDeAplicacionActivity extends AppCompatActivity {

    private static final String base = "http://3.84.45.234:3000/api/";
    CustomerService customerService = ClienteClient.getClient(base).create(CustomerService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_de_aplicacion);

        String token = getSharedPreferences("datos", MODE_PRIVATE).getString("token", "");
        Log.d("token Shared Memory", token);

        if (token.isEmpty()) {
            Toast.makeText(this, "No se ha iniciado sesión", Toast.LENGTH_SHORT).show();
            Intent miIntent = new Intent(this, InicioSesionActivity.class);
            startActivity(miIntent);
        }

        Log.d("API", "Entrando a la peticion de cliente");


        Call<Cliente> call = customerService.getCliente(token);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente clienteResponse = response.body();


                    Log.d("CORRECTO","ENTRO A VENTANA DE DETALLES CREATIVOS");

                    Log.d("RESULTADO",response.toString());

                    Gson gson = new Gson();

                    Cliente nuevo = gson.fromJson(response.message(), Cliente.class);

                    Log.d("Cliente", nuevo.toString());


                } else {
                    // Manejar errores
                    Log.d("ERROR","NO ENTRO A VENTANA DE DETALLES CREATIVOS");
                    Toast.makeText(getApplicationContext(), "Error al conectarse con el cliente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejar errores de la llamada
                Log.e("API", "Fallando Llamada: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "BASE DE DATOS NO CONECTADA", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void irPerfilUsuario(View vista) {
        Intent miIntent = new Intent (this, PerfilActivity.class );

        startActivity(miIntent);

    }

    public void irPersonalizado(View vista) {
        Intent miIntent = new Intent (this, PersonalizadoActivity.class );
        startActivity(miIntent);
    }

    public void irProfesion(View vista) {
        Intent miIntent = new Intent (this, ProfecionesActivity.class );
        startActivity(miIntent);
    }

    public void irMisTarjetas(View vista) {
        Intent miIntent1 = new Intent (this, MiTarjeta.class );
        startActivity(miIntent1);
    }

    public void irMisPedidos(View vista){
        Intent miIntent1 = new Intent (this, MisPedidos.class );
        startActivity(miIntent1);
    }

    public void Contactenos(View vista){
        Intent miIntent1 = new Intent (this, NuestraUbicacion.class );
        startActivity(miIntent1);
    }




}