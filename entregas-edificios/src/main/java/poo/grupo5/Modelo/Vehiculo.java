package poo.grupo5.Modelo;

import poo.grupo5.Interfaces.*;

public abstract class Vehiculo implements Trackable, Chargable {
    protected String id;
    protected int energia;
    protected boolean disponible;
    protected Pedido tareaActual;

    public Vehiculo() {
        energia = 100;
        disponible = true;
        tareaActual = null;
    }
    public void setId(String id) { this.id = id; }
    public void setEnergia(int energia) { this.energia = energia; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setTareaActual(Pedido tareaActual) { this.tareaActual = tareaActual; }

    public String getId() { return id; }
    public int getEnergia() { return energia; }
    public boolean isDisponible() { return disponible; }
    public Pedido getTareaActual() { return tareaActual; }

    public abstract double getVolumenMin();
    public abstract double getVolumenMax();

    public int estimatedEnergyCost(int distancia){return distancia;}
}