package poo.grupo5.Modelo;

import poo.grupo5.Enumerates.EstadoPedido;

public class Pedido {
    private String descripcion;
    private double peso;
    private String origen;
    private String destino;
    private EstadoPedido estadoPedido;
    private int distanciaRecorrida;

    public Pedido(String descripcion, double peso, String origen, String destino) {
        this.descripcion = descripcion;
        this.peso = peso;
        this.origen = origen;
        this.destino = destino;
        this.estadoPedido = EstadoPedido.RECEPCION;
        this.distanciaRecorrida = 0;
    }

    public void asignarEstado(EstadoPedido nuevoEstado) {
        this.estadoPedido = nuevoEstado;
    }

    public void actualizarDistancia(int distancia) {
        if (distancia >= 0) {
            this.distanciaRecorrida += distancia;
        } else {
            throw new IllegalArgumentException("La distancia no puede ser negativa.");
        }
    }
    public String getDescripcion() {
        return 
    }
}