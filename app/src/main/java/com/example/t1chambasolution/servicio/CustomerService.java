package com.example.t1chambasolution.servicio;

import com.example.t1chambasolution.modelo.Cliente;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface CustomerService {

    @POST("Cliente")
    Call<Cliente> createCustomer(
            @Query("nombre") String nombre,
            @Query("apellido") String apellido,
            @Query("celular") String celular,
            @Query("correo") String correo,
            @Query("contrasena") String contrasena
    );

    @GET("Cliente")
    Call<Cliente> iniciarSesion(
            @Query("correo") String correo,
            @Query("contrasena") String contrasena
    );

    @GET("Cliente/id")
    Call<Cliente> getCliente(
            @Query("token") String token
    );

    @PUT("Cliente/id")
    Call<Cliente> updateCliente(
            @Query("token") String token,
            @Query("nombre") String nombre,
            @Query("apellido") String apellido,
            @Query("celular") String celular,
            @Query("correo") String correo,
            @Query("contrasena") String contrasena
    );
}
