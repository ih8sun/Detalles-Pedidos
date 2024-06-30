package com.example.t1chambasolution;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.t1chambasolution.cliente.ClienteClient;
import com.example.t1chambasolution.modelo.Cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.t1chambasolution.servicio.CustomerService;

public class InicioSesionActivity extends AppCompatActivity implements View.OnClickListener {

    // DIRECCION LOCAL
    //private static final String base = "http://192.168.18.101:3000/api/";
    //DIRECCION EN PRODUCCION
    private static final String base = "http://3.84.45.234:3000/api/";
    EditText txtCorreo, txtClave;
    Button btnIniciar, btnSalir;
    TextView lblRegistro;

    CustomerService customerService = ClienteClient.getClient(base).create(CustomerService.class);
    List<Cliente> clientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);

        txtCorreo = findViewById(R.id.logTxtCorreo);
        txtClave = findViewById(R.id.logTxtClave);
        btnIniciar = findViewById(R.id.LogBtnIniciar);
        btnSalir = findViewById(R.id.logBtnSalir);
        lblRegistro = findViewById(R.id.logLblRegistro);

        btnIniciar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        lblRegistro.setOnClickListener(this);

        getSharedPreferences("datos",MODE_PRIVATE).edit().clear().apply();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logBtnSalir) {
            salir();
        } else if (v.getId() == R.id.LogBtnIniciar) {
            iniciarSesion(txtCorreo.getText().toString().trim().toLowerCase(), txtClave.getText().toString());
        } else if (v.getId() == R.id.logLblRegistro) {
            registrar();
        }
    }

    private void salir() {
        System.exit(0);
    }

    private void iniciarSesion(String correo, String clave) {


        Cliente cliente = new Cliente(txtCorreo.getEditableText().toString(), txtClave.getEditableText().toString());

        Call<Cliente> call = customerService.iniciarSesion(txtCorreo.getEditableText().toString(),txtClave.getEditableText().toString());

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente clienteResponse = response.body();

                    Log.d("CORRECTO","ENTRO A VERCEL CORRECTAMENTE");

                    Log.d("RESULTADO",response.toString());

                    String usuario = response.message().split("§")[0];
                    String token = response.message().split("§")[1];

                    Log.d("Usuario",usuario);
                    Log.d("Token",token);


                    // Guardamos el token en las preferencias
                    getSharedPreferences("datos",MODE_PRIVATE).edit().putString("token",token).apply();


                    Toast.makeText(InicioSesionActivity.this, "Bienvenido "+ usuario + " !!", Toast.LENGTH_SHORT).show();

//                    Intent iBienvenida = new Intent(InicioSesionActivity.this, CargaActivity.class);

                    Intent iBienvenida = new Intent(InicioSesionActivity.this, OpcionesDeAplicacionActivity.class);
                    //Cargamos el token en el intent
                    iBienvenida.putExtra("token",token);

                    startActivity(iBienvenida);
                    finish();

                } else {
                    // Manejar errores
//                    Toast.makeText(InicioSesionActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(InicioSesionActivity.this,response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejar errores de la llamada
                Log.e("API", "Failure: " + t.getMessage());
                Toast.makeText(InicioSesionActivity.this, "BASE DE DATOS NO CONECTADA", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void registrar() {
        Intent iRegistro = new Intent(this, RegistroActivity.class);
        startActivity(iRegistro);
    }

}
