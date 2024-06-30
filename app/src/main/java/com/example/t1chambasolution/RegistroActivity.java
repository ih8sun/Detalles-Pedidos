package com.example.t1chambasolution;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.t1chambasolution.cliente.ClienteClient;
import com.example.t1chambasolution.modelo.Cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.t1chambasolution.servicio.CustomerService;

public class RegistroActivity extends AppCompatActivity {

    //DIRECCION EN LOCALHOST
//    private static final String base = "http://192.168.18.101:3000/api/";
    //DIRECCION EN PRODUCCION
    private static final String base = "http://54.158.60.171:3000/api/";

    EditText logTxtNombre, logTxtApellido, logTxtCelular, logTxtCorreo, logTxtclave, logTxtConfirmarClave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        logTxtNombre = findViewById(R.id.txtNombre);
        logTxtApellido = findViewById(R.id.txtApellido);
        logTxtCelular = findViewById(R.id.txtCelular);
        logTxtCorreo = findViewById(R.id.txtCorreo);
        logTxtclave = findViewById(R.id.txtClave);
        logTxtConfirmarClave = findViewById(R.id.txtConfirmarClave);
    }

    public boolean camposVacios() {

        if (logTxtNombre.getEditableText().toString().isEmpty()) {
            System.out.println("Campo nombre vacio");
            return true;
        }
        if (logTxtApellido.getEditableText().toString().isEmpty()) {
            System.out.println("Campo apellido vacio");
            return true;
        }
        if (logTxtCelular.getEditableText().toString().isEmpty()) {
            System.out.println("Campo celular vacio");
            return true;
        }
        if (logTxtCorreo.getEditableText().toString().isEmpty()) {
            System.out.println("Campo correo vacio");
            return true;
        }
        if (logTxtclave.getEditableText().toString().isEmpty()) {
            System.out.println("Campo clave vacio");
            return true;
        }
        if (logTxtConfirmarClave.getEditableText().toString().isEmpty()) {
            System.out.println("Campo confirmar clave vacio");
            return true;
        }
        return false;
    }

    public void retroceder(View v) {
        finish();
    }

    public void registrar(View v) {

        CustomerService customerService = ClienteClient.getClient(base).create(CustomerService.class);


        if(!logTxtclave.getEditableText().toString().equals(logTxtConfirmarClave.getEditableText().toString())){
            Toast.makeText(this, "Contraseña no coincide", Toast.LENGTH_SHORT).show();
            return;
        }

        if (camposVacios()) {
            Toast.makeText(this, "Algunos campos estan vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = logTxtCorreo.getEditableText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "EMAIL NO VALIDO", Toast.LENGTH_SHORT).show();
            return;
        }
        Cliente cliente = new Cliente(logTxtNombre.getEditableText().toString(), logTxtApellido.getEditableText().toString(), logTxtCorreo.getEditableText().toString(), logTxtCelular.getEditableText().toString(), logTxtConfirmarClave.getEditableText().toString());

        Call<Cliente> call = customerService.createCustomer(cliente.getNombresCliente(), cliente.getApellidosCliente(), cliente.getCelularCliente(), cliente.getCorreoCliente(), cliente.getContrasenaCliente());

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente clienteResponse = response.body();
                    // Manejar la respuesta
                    Log.d("API", "Cliente creado correctamente: ");

                    Log.e("ACCESO:", "SE REGISTRO Y PROCEDO A SALIR");
                    Toast.makeText(RegistroActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Manejar errores
                    Toast.makeText(RegistroActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // Manejar errores de la llamada
                Log.e("API", "Failure: " + t.getMessage());
                Toast.makeText(RegistroActivity.this, "BASE DE DATOS NO CONECTADA", Toast.LENGTH_SHORT).show();
            }
        });

    }

}