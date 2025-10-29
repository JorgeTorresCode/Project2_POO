package poo.jorgecarlosanthony.modelo;

import poo.jorgecarlosanthony.Interfaces.*;

public class Vehiculo implements Trackable, Chargable {
    protected String id;
    protected int energia;
    protected boolean disponible;
    protected int velocidad;
    protected double pesoMinimo;
    protected double pesoMaximo;

    public Vehiculo() {

    }
}