package com.example.t1chambasolution;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class PerfilActivity extends AppCompatActivity {
    //DIRECCION EN LOCALHOST
//    private static final String base = "http://192.168.18.101:3000/api/";
    //DIRECCION EN PRODUCCION
    private static final String base = "http://54.158.60.171:3000/api/";
    EditText nombres, correo, telefono;
    CustomerService customerService = ClienteClient.getClient(base).create(CustomerService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //espera a que se cargue la vista


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombres = findViewById(R.id.logTxtNombre);
        correo = findViewById(R.id.logTxtCorreo);
        telefono = findViewById(R.id.logTxtTelefono);

        nombres.setInputType(InputType.TYPE_NULL);
        correo.setInputType(InputType.TYPE_NULL);
        telefono.setInputType(InputType.TYPE_NULL);

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


                    Log.d("CORRECTO","ENTRO A VENTANA DE MI PERFIL");

                    Log.d("RESULTADO",response.toString());

                    Gson gson = new Gson();

                    Cliente nuevo = gson.fromJson(response.message(), Cliente.class);

                    nombres.setText(nuevo.getNombresCliente());
                    correo.setText(nuevo.getCorreoCliente());
                    telefono.setText(nuevo.getCelularCliente());


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

    public void irEditarPerfil(View vista) {
        Intent miIntent = new Intent (this, EditarPerfilActivity.class);
        //Mandamos el correo
        miIntent.putExtra("correo", correo.getText().toString());
        startActivity(miIntent);
    }
    public void irOpcionesDeAplicaciones(View vista) {
        finish();
    }
}