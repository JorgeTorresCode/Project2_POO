package poo.grupo5.Modelo;

import poo.grupo5.Enumerates.EstadoPedido;

import java.time.LocalDateTime;

public class Pedido {
    private String descripcion;
    private double peso;
    private Edificio origen;
    private Edificio destino;
    private EstadoPedido estadoPedido;
    private int distanciaTotal;
    private String vehiculoID;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Pedido(String descripcion, double peso, Edificio origen, Edificio destino,  String vehiculoID, int distancia) {
        this.descripcion = descripcion;
        this.peso = peso;
        this.origen = origen;
        this.destino = destino;
        this.estadoPedido = EstadoPedido.RECEPCION;
        this.distanciaTotal = distancia;
        this.vehiculoID = vehiculoID;
    }

    public void CambiarEtapa(){
        switch (this.estadoPedido){
            case RECEPCION: estadoPedido = EstadoPedido.PREPARACION; break;
            case PREPARACION: estadoPedido = EstadoPedido.EN_CAMINO; break;
            case EN_CAMINO: estadoPedido = EstadoPedido.ENTREGADO; break;
            default: estadoPedido = EstadoPedido.RECEPCION; break;
        }
    }

    public void asignarEstado(EstadoPedido nuevoEstado) {
        this.estadoPedido = nuevoEstado;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setFechaFin(LocalDateTime fechaFin) {this.fechaFin = fechaFin;}


    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }
    public String getVehiculoID() {return vehiculoID;}
    public LocalDateTime getFechaInicio() {return fechaInicio;}
    public LocalDateTime getFechaFin() {return fechaFin;}
    public int getDistanciaTotal() {return distanciaTotal;}
}