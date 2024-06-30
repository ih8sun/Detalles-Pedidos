package com.example.t1chambasolution.modelo;

import com.google.gson.annotations.SerializedName;

public class Cliente {
    @SerializedName("clienteid")
    int CustomerID;
    @SerializedName("direccionid")
    Integer DireccionID;
    @SerializedName("nombrescliente")
    String NombresCliente;
    @SerializedName("apellidoscliente")
    String ApellidosCliente;
    @SerializedName("correocliente")
    String CorreoCliente;
    @SerializedName("celularcliente")
    String CelularCliente;
    @SerializedName("contrasenacliente")
    String ContrasenaCliente;

    public Cliente(int customerID, Integer direccionID, String nombresCliente, String apellidosCliente, String correoCliente, String celularCliente, String contrasenaCliente) {
        CustomerID = customerID;
        DireccionID = direccionID;
        NombresCliente = nombresCliente;
        ApellidosCliente = apellidosCliente;
        CorreoCliente = correoCliente;
        CelularCliente = celularCliente;
        ContrasenaCliente = contrasenaCliente;
    }


    public Cliente(String nombresCliente, String apellidosCliente, String correoCliente, String celularCliente, String contrasenaCliente) {
        NombresCliente = nombresCliente;
        ApellidosCliente = apellidosCliente;
        CorreoCliente = correoCliente;
        CelularCliente = celularCliente;
        ContrasenaCliente = contrasenaCliente;
    }

    public Cliente(String correoCliente, String contrasenaCliente) {
        CorreoCliente = correoCliente;
        ContrasenaCliente = contrasenaCliente;
    }
    public Cliente(int customerID, String nombresCliente, String apellidosCliente, String correoCliente, String celularCliente, String contrasenaCliente) {
        CustomerID = customerID;
        NombresCliente = nombresCliente;
        ApellidosCliente = apellidosCliente;
        CorreoCliente = correoCliente;
        CelularCliente = celularCliente;
        ContrasenaCliente = contrasenaCliente;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public Integer getDireccionID() {
        return DireccionID;
    }

    public void setDireccionID(Integer direccionID) {
        DireccionID = direccionID;
    }

    public String getNombresCliente() {
        return NombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        NombresCliente = nombresCliente;
    }

    public String getApellidosCliente() {
        return ApellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        ApellidosCliente = apellidosCliente;
    }

    public String getCorreoCliente() {
        return CorreoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        CorreoCliente = correoCliente;
    }

    public String getCelularCliente() {
        return CelularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        CelularCliente = celularCliente;
    }

    public String getContrasenaCliente() {
        return ContrasenaCliente;
    }

    public void setContrasenaCliente(String contrasenaCliente) {
        ContrasenaCliente = contrasenaCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "CustomerID=" + CustomerID +
                ", DireccionID=" + DireccionID +
                ", NombresCliente='" + NombresCliente + '\'' +
                ", ApellidosCliente='" + ApellidosCliente + '\'' +
                ", CorreoCliente='" + CorreoCliente + '\'' +
                ", CelularCliente='" + CelularCliente + '\'' +
                ", ContrasenaCliente='" + ContrasenaCliente + '\'' +
                '}';
    }
}
