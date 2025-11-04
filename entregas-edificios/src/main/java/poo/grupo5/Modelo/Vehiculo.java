package poo.grupo5.Modelo;

import poo.grupo5.Enumerates.EstadoVehiculo;
import poo.grupo5.Interfaces.*;

public abstract class Vehiculo implements Trackable, Chargable {
    protected String id;
    protected int energia;
    protected Pedido tareaActual;
    protected EstadoVehiculo estado;

    public Vehiculo() {
        energia = 100;
        estado = EstadoVehiculo.DISPONIBLE;
        tareaActual = null;
    }
    public void setId(String id) { this.id = id; }
    public void setEnergia(int energia) { this.energia = energia; }
    public void setEstado(EstadoVehiculo estado) {this.estado = estado;}
    public void setTareaActual(Pedido tareaActual) { this.tareaActual = tareaActual; }

    public String getId() { return id; }
    public int getEnergia() { return energia; }
    public EstadoVehiculo getEstado() {return estado;}
    public Pedido getTareaActual() { return tareaActual; }

    public abstract double getVolumenMin();
    public abstract double getVolumenMax();

    public static int estimatedEnergyCost(int distancia){return distancia;}
}