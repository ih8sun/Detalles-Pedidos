package com.example.t1chambasolution.modelo;

public class Pedido {

    private String numeroOrden;
    private String fechaPedido;
    private double totalPagado;
    private String estadoEnvio;

    public Pedido(String numeroOrden, String fechaPedido, double totalPagado, String estadoEnvio) {
        this.numeroOrden = numeroOrden;
        this.fechaPedido = fechaPedido;
        this.totalPagado = totalPagado;
        this.estadoEnvio = estadoEnvio;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }
}
