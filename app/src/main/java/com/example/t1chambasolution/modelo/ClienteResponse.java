package com.example.t1chambasolution.modelo;

public class ClienteResponse {
    String NombreCliente;
    String ApellidoCliente;

    String CelularCliente;
    String CorreoCliente;
    String ContrasenaCliente;

    public ClienteResponse(String nombreCliente, String apellidoCliente, String celularCliente, String correoCliente, String contrasenaCliente) {
        NombreCliente = nombreCliente;
        ApellidoCliente = apellidoCliente;
        CelularCliente = celularCliente;
        CorreoCliente = correoCliente;
        ContrasenaCliente = contrasenaCliente;
    }

    public String getMessage() {
        return "Cliente creado correctamente";
    }
}
