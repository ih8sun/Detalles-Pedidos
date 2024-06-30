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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfilActivity extends AppCompatActivity {

    //DIRECCION EN LOCALHOST
//    private static final String base = "http://192.168.18.101:3000/api/";
    //DIRECCION EN PRODUCCION
    private static final String base = "http://54.158.60.171:3000/api/";
    CustomerService customerService = ClienteClient.getClient(base).create(CustomerService.class);
    EditText correoET, nombres, apellidos, telefono,clave;
    String correo;


    public boolean camposVacios() {

        if (correoET.getEditableText().toString().isEmpty()) {
            System.out.println("Campo nombre vacio");
            return true;
        }
        if (nombres.getEditableText().toString().isEmpty()) {
            System.out.println("Campo apellido vacio");
            return true;
        }
        if (apellidos.getEditableText().toString().isEmpty()) {
            System.out.println("Campo celular vacio");
            return true;
        }
        if (telefono.getEditableText().toString().isEmpty()) {
            System.out.println("Campo correo vacio");
            return true;
        }
        if (clave.getEditableText().toString().isEmpty()) {
            System.out.println("Campo clave vacio");
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        correo = getIntent().getStringExtra("correo");

        correoET = findViewById(R.id.logTxtCorreo);
        correoET.setInputType(InputType.TYPE_NULL);
        correoET.setText(correo);

        nombres = findViewById(R.id.logTxtNombre);
        telefono = findViewById(R.id.logTxtTelefono);
        clave = findViewById(R.id.logTxtClave);
        apellidos = findViewById(R.id.logTxtApellido);

    }

    public void confirmar(View vista) {

        String nombreCliente = nombres.getEditableText().toString();
        String telefonoCliente = telefono.getEditableText().toString();
        String apellidoCliente = apellidos.getEditableText().toString();
        String claveCliente = clave.getEditableText().toString();
        String token = getSharedPreferences("datos", MODE_PRIVATE).getString("token", "");

        if(camposVacios()){
            Toast.makeText(EditarPerfilActivity.this, "Algunos campos estan vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente nuevoCliente = new Cliente(nombreCliente,apellidoCliente,correo,telefonoCliente,claveCliente);

        Call<Cliente> call = customerService.updateCliente(token,nombreCliente,apellidoCliente,telefonoCliente,correo,claveCliente);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente clienteResponse = response.body();
                    // Manejar la respuesta
                    Log.d("API", "Cliente actualizado correctamente: ");
                    Log.e("ACCESO:", "SE REGISTRO Y PROCEDO A SALIR");
                    Toast.makeText(EditarPerfilActivity.this, "Campos actualizados correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Manejar errores
                    Toast.makeText(EditarPerfilActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejar errores de la llamada
                Log.e("API", "Failure: " + t.getMessage());
                Toast.makeText(EditarPerfilActivity.this, "BASE DE DATOS NO CONECTADA", Toast.LENGTH_SHORT).show();
            }
        });


        //Creamos ventana de opciones de aplicacion
        Intent opcionesDeAplicacion = new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(opcionesDeAplicacion);
        finish();
    }

    public void irEditarPerfil(View vista) {

        //Creamos ventana de opciones de aplicacion
        Intent opcionesDeAplicacion = new Intent(this, OpcionesDeAplicacionActivity.class);
        startActivity(opcionesDeAplicacion);
        finish();
    }

}