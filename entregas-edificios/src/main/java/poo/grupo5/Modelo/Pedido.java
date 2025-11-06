package poo.grupo5.Modelo;

import poo.grupo5.Enumerates.EstadoPedido;
import poo.grupo5.Interfaces.*;
import poo.grupo5.Modelo.Estructuras.Edificio;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pedido implements Serializable, Trackable {
    private String descripcion;
    private double peso;
    private Edificio origen;
    private Edificio destino;
    private EstadoPedido estadoPedido;
    private int distanciaTotal;
    private String vehiculoID;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Pedido(String descripcion, double peso, Edificio origen, Edificio destino,  String vehiculoID, int distancia, LocalDateTime fechaInicio) {
        this.descripcion = descripcion;
        this.peso = peso;
        this.origen = origen;
        this.destino = destino;
        this.estadoPedido = EstadoPedido.RECEPCION;
        this.distanciaTotal = distancia;
        this.vehiculoID = vehiculoID;
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setFechaFin(LocalDateTime fechaFin) {this.fechaFin = fechaFin;}
    public void setEstadoPedido(EstadoPedido estadoPedido){this.estadoPedido = estadoPedido;}

    public void asignarEstado(EstadoPedido nuevoEstado) { this.estadoPedido = nuevoEstado; }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getVehiculoID() {return vehiculoID;}
    public LocalDateTime getFechaInicio() {return fechaInicio;}
    public LocalDateTime getFechaFin() {return fechaFin;}
    public int getDistanciaTotal() {return distanciaTotal;}

    @Override
    public EstadoPedido getState() { return estadoPedido; }

    @Override
    public String toString() {
        return "Pedido{descripcion=" + descripcion + ", peso=" + peso + ", origen=" + origen.getId() + ", destino=" +
                destino.getId() + ", estado=" + estadoPedido + ", distancia=" + distanciaTotal + ", vehiculo=" +
                vehiculoID + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "}";
    }
}